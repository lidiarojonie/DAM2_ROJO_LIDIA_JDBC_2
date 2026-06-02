package examen.rojo.lidia;

import examen.rojo.lidia.beans.CentroForense;
import examen.rojo.lidia.beans.MuestraForense;
import examen.rojo.lidia.dao.MuestraForenseDAOImpl;
import examen.rojo.lidia.motores.MotorFactory;
import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.Map; 
 
public class Main { 
    public static void main(String[] args) { 
        MuestraForenseDAOImpl MuestraForenseDAO = new MuestraForenseDAOImpl( 
                MotorFactory.create(MotorFactory.POSTGRE) 
        ); 
 
        System.out.println("=== INICIANDO TESTS DE MUESTRA FORENSE ==="); 
 
        // TEST 1: ADD MUESTRA 
        MuestraForense nuevo = new MuestraForense(); 
        nuevo.setCodigoCaso(00000006); 
        nuevo.setTipoMuestra("Riñon"); 
        nuevo.setFechaRecogida("02/06/2026"); 
        nuevo.setEstadoCustodia("GUARDADO"); 
        CentroForense centro = new CentroForense(); 
        centro.setId(1); // Suponiendo que centro es ID 1 
        nuevo.setCentro(centro); 
         
        System.out.println("\n[TEST 1] AÑADIR MUESTRA FORENSE"); 
        MuestraForenseDAO.add(nuevo); 

        // TEST 2: UPDATE MUESTRA FORENSE
 
        // TEST 3: FIND MUESTRA FORENSE
        System.out.println("\n[TEST 3] BUSCAR MUESTRA FORENSE ID 1"); 
        MuestraForense s1 = MuestraForenseDAO.find(1); 
        if(s1 != null) System.out.println("Encontrado: " + s1.getCodigoCaso()); 

        // TEST 4: FIND ALL MUESTRAS FORENSES
        System.out.println("\n[TEST 4] LISTAR TODOS"); 
        ArrayList<MuestraForense> todos = MuestraForenseDAO.findAll(); 
        for (MuestraForense m : todos) { 
            System.out.println(m.toString()); 
        } 

        // TEST 5: FIND MUESTRAS FORENSES BY CENTRO
        System.out.println("\n[TEST 5] LISTAR MUESTRAS BY CENTRO"); 
        ArrayList<MuestraForense> muestraByCentro = MuestraForenseDAO.findMuestraByCentro(); 
        for (MuestraForense m : muestraByCentro) { 
            System.out.println(m.toString()); 
        } 
        
        // BLOQUE 6
        System.out.println("\n[BLOQUE 6] LISTAR MUESTRAS PELIGROSAS"); 
        ArrayList<MuestraForense> muestraPeligrosa = MuestraForenseDAO.findMuestrasPeligrosas(); 
        for (MuestraForense m : muestraPeligrosa) { 
            System.out.println(m.toString()); 
        } 

        // BLOQUE 7 - TEST: UPDATE DINÁMICO 
        System.out.println("\n[TEST BLOQUE 7] UPDATE DINÁMICO (Cambiar Codigo y tipo muestra de ID 1)");
        Map<String, Object> campos = new HashMap<>(); 
        campos.put("CODIGO_CASO", "00000010"); 
        campos.put("TIPO_MUESTRA", "HUESO"); 
        MuestraForenseDAO.updateDynamic("MUESTRAS_FORENSES", 1, campos);

    } 
}