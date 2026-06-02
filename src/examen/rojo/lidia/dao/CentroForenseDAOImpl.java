package examen.rojo.lidia.dao;

import examen.rojo.lidia.beans.CentroForense;
import examen.rojo.lidia.motores.MotorSQL;
import java.sql.ResultSet; 
import java.util.ArrayList; 
 
public class CentroForenseDAOImpl extends AbstractDAO<CentroForense> { 
 
    private static final String SQL_FIND_ALL = "SELECT * FROM CENTROS_FORENSES ORDER BY ID";
     
    private static final String SQL_FIND = "SELECT * FROM CENTROS_FORENSES WHERE ID = ?"; 
     
    private static final String SQL_INSERT =  
            "INSERT INTO CENTROS_FORENSES (NOMBRE, PAIS, NIVEL_SEGURIDAD, AUTOR_EXAMEN) " +
            "VALUES (?, ?, ?, 'LIDIA_ROJO_DAM2')"; 
             
    private static final String SQL_DELETE = "DELETE FROM CENTROS_FORENSES WHERE ID = ?"; 
 
    public CentroForenseDAOImpl(MotorSQL motorSQL) { 
        super(motorSQL); 
    } 
 
    @Override 
    public void add(CentroForense CentroForense) { 
        try { 
            motorSQL.connect(); 
            motorSQL.prepare(SQL_INSERT); 
            motorSQL.getPs().setString(1, CentroForense.getNombre()); 
            motorSQL.getPs().setString(2, CentroForense.getPais());
            motorSQL.getPs().setInt(3, CentroForense.getNivelSeguridad());  
             
            int rows = motorSQL.executeUpdate(); 
            System.out.println("CENTROS_FORENSES INSERTADOS: " + rows); 
        } catch (Exception e) { 
            printError(e); 
        } finally { 
            motorSQL.close(); 
        } 
    } 
 
    @Override 
    public void update(int id, CentroForense CentroForense) { 
        try { 
            motorSQL.connect(); 
            motorSQL.prepare(SQL_INSERT); 
            motorSQL.getPs().setString(1, CentroForense.getNombre()); 
            motorSQL.getPs().setString(2, CentroForense.getPais());
            motorSQL.getPs().setInt(3, CentroForense.getNivelSeguridad());  
             
            int rows = motorSQL.executeUpdate(); 
            System.out.println("CENTROS_FORENSES INSERTADOS: " + rows); 
        } catch (Exception e) { 
            printError(e); 
        } finally { 
            motorSQL.close(); 
        } 
    } 
 
    @Override 
    public void delete(int id) { 
        try { 
            motorSQL.connect(); 
            motorSQL.prepare(SQL_DELETE); 
            motorSQL.getPs().setInt(1, id); 
             
            int rows = motorSQL.executeUpdate(); 
            System.out.println("CENTROS_FORENSES BORRADOS: " + rows); 
        } catch (Exception e) { 
            printError(e); 
        } finally { 
            motorSQL.close(); 
        } 
    } 
 
    @Override 
    public CentroForense find(int id) { 
        CentroForense CentroForense = null; 
        try { 
            motorSQL.connect(); 
            motorSQL.prepare(SQL_FIND); 
            motorSQL.getPs().setInt(1, id); 
             
            ResultSet rs = motorSQL.executeQuery(); 
            if (rs.next()) { 
                CentroForense = mapCentroForense(rs); 
            } 
        } catch (Exception e) { 
            printError(e); 
        } finally { 
            motorSQL.close(); 
        } 
        return CentroForense; 
    } 
 
    @Override 
    public ArrayList<CentroForense> findAll() { 
        ArrayList<CentroForense> CentroForenses = new ArrayList<>(); 
        try { 
            motorSQL.connect(); 
            motorSQL.prepare(SQL_FIND_ALL); 
             
            ResultSet rs = motorSQL.executeQuery(); 
            while (rs.next()) { 
                CentroForenses.add(mapCentroForense(rs)); 
            } 
        } catch (Exception e) { 
            printError(e); 
        } finally { 
            motorSQL.close(); 
        } 
        return CentroForenses; 
    } 
 
    /* 
     * ========================= 
     * MAPPING 
     * ========================= 
     */ 
    private CentroForense mapCentroForense(ResultSet rs) throws Exception { 
        CentroForense CentroForense = new CentroForense(); 
         
        CentroForense.setId(rs.getInt("ID")); 
        CentroForense.setNombre(rs.getString("NOMBRE")); 
        CentroForense.setPais(rs.getString("PAIS")); 
        CentroForense.setNivelSeguridad(rs.getInt("NIVEL_SEGURIDAD")); 
         
        return CentroForense; 
    } 
}