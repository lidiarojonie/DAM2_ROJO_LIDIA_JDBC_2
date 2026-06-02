package examen.rojo.lidia.dao;

import java.util.Map;

import examen.rojo.lidia.motores.MotorSQL;
 
public abstract class AbstractDAO<T> implements DAO<T> { 
    protected MotorSQL motorSQL; 
 
    public AbstractDAO(MotorSQL motorSQL) { 
        this.motorSQL = motorSQL; 
    } 
 
    protected void printError(Exception e){ 
        System.out.println("[ERROR] " + e.getMessage()); 
    } 

    public int updateDynamic(String tableName, int id, Map<String, Object> fieldsToUpdate) { 
        if (fieldsToUpdate == null || fieldsToUpdate.isEmpty()) return 0; 
 
        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET "); 
        int i = 0; 
         
        // 1. Construir SQL dinámico 
        for (String column : fieldsToUpdate.keySet()) { 
            sql.append(column).append(" = ?"); 
            if (i < fieldsToUpdate.size() - 1) sql.append(", "); 
            i++; 
        } 
        sql.append(" WHERE ID = ?"); 
 
        int rows = 0; 
        try { 
            motorSQL.connect(); 
            motorSQL.prepare(sql.toString()); 
 
            // 2. Inyectar valores dinámicos 
            int paramIndex = 1; 
            for (Object value : fieldsToUpdate.values()) { 
                motorSQL.getPs().setObject(paramIndex++, value); 
            } 
            motorSQL.getPs().setInt(paramIndex, id); // El ID para el WHERE 
 
            rows = motorSQL.executeUpdate(); 
            System.out.println("UPDATE DINÁMICO EJECUTADO. FILAS AFECTADAS: " + rows); 
        } catch (Exception e) { 
            printError(e); 
        } finally { 
            motorSQL.close(); 
        } 
        return rows; 
    } 
} 
