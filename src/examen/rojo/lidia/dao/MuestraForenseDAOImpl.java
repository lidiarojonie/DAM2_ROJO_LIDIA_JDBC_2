package examen.rojo.lidia.dao;

import examen.rojo.lidia.beans.MuestraForense;
import examen.rojo.lidia.motores.MotorSQL;
import java.sql.ResultSet; 
import java.util.ArrayList; 
 
public class MuestraForenseDAOImpl extends AbstractDAO<MuestraForense> { 
 
    private static final String SQL_FIND_ALL = "SELECT * FROM MUESTRAS_FORENSES ORDER BY ID";
    private static final String SQL_FIND = "SELECT * FROM MUESTRAS_FORENSES WHERE ID = ?"; 
    private static final String SQL_INSERT = "INSERT INTO MUESTRAS_FORENSES (CODIGO_CASO, TIPO_MUESTRA, FECHA_RECOGIDA, ESTADO_CUSTODIA, CENTRO_ID, AUTOR_EXAMEN) VALUES (?, ?, ?, ?, ?, 'LIDIA_ROJO_DAM2')";
    private static final String SQL_DELETE = "DELETE FROM MUESTRAS_FORENSES WHERE ID = ?"; 
    private static final String SQL_MUESTRAS_BY_CENTRO = 
    "SELECT * FROM MUESTRAS_FORENSES M INNER JOIN CENTROS_FORENSES C ON M.CENTRO_ID = C.ID WHERE C.NOMBRE = ? ";
     
    // TRAMO 5-6: BONUS_QUERY_ADVANCED 
    private static final String SQL_FIND_MUESTRAS_PELIGROSAS =  
            "SELECT M.ID, M.CODIGO_CASO, M.TIPO_MUESTRA, M.FECHA_RECOGIDA, M.ESTADO_CUSTODIA, " +
            "C.ID AS CEN_ID, C.NOMBRE AS CEN_NOMBRE, C.PAIS, " + 
            "I.ID AS INF_ID, I.ADN_POSITIVO, I.NIVEL_RIESGO, I.CONCLUSION " + 
            "FROM MUESTRAS_FORENSES M " + 
            "INNER JOIN CENTROS_FORENSES C ON M.CENTRO_ID = C.ID " + 
            "INNER JOIN INFORMES_FORENSES I ON M.ID = I.MUESTRA_ID " + 
            "WHERE I.ADN_POSITIVO = TRUE AND I.NIVEL_RIESGO > 90 AND C.PAIS = 'ESPAÑA'"; 
 
    public MuestraForenseDAOImpl(MotorSQL motorSQL) { 
        super(motorSQL); 
    } 
 
    @Override 
    public void add(MuestraForense m_f) { 
        try { 
            motorSQL.connect(); 
            motorSQL.prepare(SQL_INSERT); 
            motorSQL.getPs().setInt(1, m_f.getCodigoCaso()); 
            motorSQL.getPs().setString(2, m_f.getTipoMuestra()); 
            motorSQL.getPs().setString(3, m_f.getFechaRecogida()); 
            motorSQL.getPs().setString(4, m_f.getEstadoCustodia()); 
            motorSQL.getPs().setInt(5, m_f.getCentro().getId()); 
            
            int rows = motorSQL.executeUpdate(); 
            System.out.println("MUESTRAS FORENSES INSERTADAS: " + rows); 
        } catch (Exception e) { 
            printError(e); 
        } finally { 
            motorSQL.close(); 
        } 
    } 
 
    @Override 
    public void update(int id, MuestraForense m_f) { 
       
    } 
 
    @Override 
    public void delete(int id) { 
        try { 
            motorSQL.connect(); 
            motorSQL.prepare(SQL_DELETE); 
            motorSQL.getPs().setInt(1, id); 
            int rows = motorSQL.executeUpdate(); 
            System.out.println("BORRADOS: " + rows); 
        } catch (Exception e) { 
            printError(e); 
        } finally { 
            motorSQL.close(); 
        } 
    } 
 
    @Override 
    public MuestraForense find(int id) { 
        MuestraForense s = null; 
        try { 
            motorSQL.connect(); 
            motorSQL.prepare(SQL_FIND); 
            motorSQL.getPs().setInt(1, id); 
            ResultSet rs = motorSQL.executeQuery(); 
            if (rs.next()) { 
                s = mapMuestraForenseBasico(rs); 
            } 
        } catch (Exception e) { 
            printError(e); 
        } finally { 
            motorSQL.close(); 
        } 
        return s; 
    } 
 
    @Override 
    public ArrayList<MuestraForense> findAll() { 
        ArrayList<MuestraForense> lst = new ArrayList<>(); 
        try { 
            motorSQL.connect(); 
            motorSQL.prepare(SQL_FIND_ALL); 
            ResultSet rs = motorSQL.executeQuery(); 
            while (rs.next()) { 
                lst.add(mapMuestraForenseBasico(rs)); 
            } 
        } catch (Exception e) { 
            printError(e); 
        } finally { 
            motorSQL.close(); 
        } 
        return lst; 
    } 

    public ArrayList<MuestraForense> findMuestraByCentro() { 
        ArrayList<MuestraForense> lst = new ArrayList<>(); 
        try { 
            motorSQL.connect(); 
            motorSQL.prepare(SQL_MUESTRAS_BY_CENTRO); 
            ResultSet rs = motorSQL.executeQuery(); 
            while (rs.next()) { 
                lst.add(mapMuestraForenseBasico(rs)); 
            } 
        } catch (Exception e) { 
            printError(e); 
        } finally { 
            motorSQL.close(); 
        } 
        return lst; 
    } 

 
    // BONUS TRAMO 5-6: INNER JOIN MULTIPLE 
    public ArrayList<MuestraForense> findMuestrasPeligrosas() { 
        ArrayList<MuestraForense> lst = new ArrayList<>(); 
        try { 
            motorSQL.connect(); 
            motorSQL.prepare(SQL_FIND_MUESTRAS_PELIGROSAS); 
            ResultSet rs = motorSQL.executeQuery(); 
            while(rs.next()){ 
                MuestraForense muestra = mapMuestraForenseBasico(rs); 
                // Mapear Centro
                muestra.getCentro().setId(rs.getInt("CEN_ID")); 
                muestra.getCentro().setNombre(rs.getString("CEN_NOMBRE")); 
                muestra.getCentro().setPais(rs.getString("PAIS")); 
                // Mapear INFORME
                muestra.getInforme().setId(rs.getInt("DET_ID")); 
                muestra.getInforme().setAdnPositivo(rs.getBoolean("ADN_POSITIVO"));
                muestra.getInforme().setNivelRiesgo(rs.getInt("NIVEL_RIESGO")); 
                 
                lst.add(muestra); 
            } 
        } catch (Exception e) { 
            printError(e); 
        } finally { 
            motorSQL.close(); 
        } 
        return lst; 
    } 
 
    private MuestraForense mapMuestraForenseBasico(ResultSet rs) throws Exception { 
        MuestraForense m = new MuestraForense(); 
        m.setId(rs.getInt("ID")); 
        m.setCodigoCaso(rs.getInt("CODIGO_CASO")); 
        m.setTipoMuestra(rs.getString("TIPO_MUESTRA")); 
        m.setFechaRecogida(rs.getString("FECHA_RECOGIDA")); 
        m.setEstadoCustodia(rs.getString("ESTADO_CUSTODIA")); 
        return m; 
    } 
}