package examen.rojo.lidia.beans;

public class CentroForense {
    // Atributos
    private int id; 
    private String nombre; 
    private String pais; 
    private String nivelSeguridad; 
    
    // Constructor
    public CentroForense() {} 
    
    // Getters y Setters
    // ID
    public int getId() { return id; } 
    public void setId(int id) { this.id = id; } 

    // NOMBRE
    public String getNombre() { return nombre; } 
    public void setNombre(String nombre) { this.nombre = nombre; } 

    //PAIS
    public String getPais() { return pais; } 
    public void setPais(String pais) { this.pais = pais; } 

    public String getNivelSeguridad() { return nivelSeguridad; } 
    public void setNivelSeguridad(String nivelSeguridad) { this.nivelSeguridad = nivelSeguridad; } 
     

    // To String
    @Override 
    public String toString() { 
        return "Agencia{id=" + id + ", nombre='" + nombre + "'}"; 
    } 
} 