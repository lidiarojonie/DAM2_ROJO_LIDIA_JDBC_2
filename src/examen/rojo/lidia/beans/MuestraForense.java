package examen.rojo.lidia.beans;

public class MuestraForense {
    // Atributos
    private int id; 
    private int codigoCaso; 
    private String tipoMuestra; 
    private String fechaRecogida; 
    private String estadoCustodia; 
     
    // Relaciones Orientadas a Objetos 
    private CentroForense centro; 
    private InformeForense informe; 
 
    // Constructor
    public MuestraForense() {} 
 
    // Getters and Setters
    // ID
    public int getId() { return id; } 
    public void setId(int id) { this.id = id; } 

    // CODIGO CASO
    public int getCodigoCaso() { return codigoCaso; } 
    public void setCodigoCaso(int codigoCaso) { this.codigoCaso = codigoCaso; } 

    // TIPO MUESTRA
    public String getTipoMuestra() { return tipoMuestra; } 
    public void setTipoMuestra(String tipoMuestra) { this.tipoMuestra = tipoMuestra; } 

    // FECHA RECOGIDA
    public String getFechaRecogida() { return fechaRecogida; } 
    public void setFechaRecogida(String fechaRecogida) { this.fechaRecogida = fechaRecogida; } 

    // ESTADO CUSTODIA
    public String getEstadoCustodia() { return estadoCustodia; } 
    public void setEstadoCustodia(String estadoCustodia) { this.estadoCustodia = estadoCustodia; } 

    // CENTRO FORENSE
    public CentroForense getCentro() { 
        if(centro == null) centro = new CentroForense(); 
        return centro; 
    } 
    public void setCentro(CentroForense centro) { this.centro = centro; } 
 
    // INFORME FORENSE
    public InformeForense getInforme() { 
        if(informe == null) informe = new InformeForense(); 
        return informe; 
    } 
    public void setInformeForense(InformeForense informe) { this.informe = informe; } 
 

    // To String
    @Override 
    public String toString() { 
        return "MuestraForense{" + "id=" + id + ", codigoCaso='" + codigoCaso + '\'' +  
               ", centroForense=" + (centro != null ? centro.getNombre() : "null") +  
               ", informeForense=" + (informe != null ? informe.getAdnPositivo() : "null") + '}'; 
    } 
}