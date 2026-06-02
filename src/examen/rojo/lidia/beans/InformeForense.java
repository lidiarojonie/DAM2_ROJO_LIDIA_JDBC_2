package examen.rojo.lidia.beans;

public class InformeForense {
    // Atributos
    private int id; 
    private Boolean adnPositivo; 
    private int nivelRiesgo; 
    private String conclusion; 
 
    // Constructor
    public InformeForense() {} 
 
    // Getters and Setters
    // ID
    public int getId() { return id; } 
    public void setId(int id) { this.id = id; } 

    //CODIGO CASO
    public boolean getAdnPositivo() { return adnPositivo; } 
    public void setAdnPositivo(boolean adnPositivo) { this.adnPositivo = adnPositivo; } 
    
    // TIPO MUESTRA
    public int getNivelRiesgo() { return nivelRiesgo; } 
    public void setNivelRiesgo(int nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; } 
    
    // FECHA RECOGIDA
    public String getConclusion() { return conclusion; } 
    public void setConclusion(String conclusion) { this.conclusion = conclusion; } 


    // To String
    @Override 
    public String toString() { 
        return "Informe{adnPositivo=" + adnPositivo + ", nivelRiesgo=" + nivelRiesgo + ", conclusion=" + conclusion + "}"; 
    }
} 
