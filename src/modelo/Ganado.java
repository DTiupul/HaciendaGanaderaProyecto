/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author josed
 */
public class Ganado {
    private String id;
    private Date fechaNacimiento;
    private String natal;
    private String estadoV;
    private String lote;
    private String estadoPL;
    private String estadoGeneral;
    private int litrosProducidos;
    private float precio;
    private Date fechaProduccion;

    public Ganado(String id, String natal, String estadoV, String lote, Float precio) {
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
        this.natal = natal;
        this.estadoV = estadoV;
        this.lote = lote;
        this.precio = precio;
    }
    
    

    public Ganado(String id, Date fechaNacimiento,String natal, String estadoV, String lote, String estadoPL) {
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
        this.natal=natal;
        this.estadoV = estadoV;
        this.lote = lote;
        this.estadoPL = estadoPL;
    }

    public Ganado(String id, Date fechaNacimiento, String natal, String estadoV, String lote, int litrosProducidos) {
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
        this.natal = natal;
        this.estadoV = estadoV;
        this.lote = lote;
        this.litrosProducidos = litrosProducidos;
    }
    
    

    public Ganado(String nombre, Date fechaNacimiento,String natal, String estadoV, String lote) {
        this.id = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.natal=natal;
        this.estadoV = estadoV;
        this.lote = lote;
    }
    
    

    public Ganado(String id, Date fechaNacimiento,String natal, String estadoV, String lote, String estadoPL, String estadoGeneral) {
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
        this.natal=natal;
        this.estadoV = estadoV;
        this.lote = lote;
        this.estadoPL = estadoPL;
        this.estadoGeneral = estadoGeneral;
    }

    public Ganado(String id, int litrosProducidos) {
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
        this.natal = natal;
        this.estadoV = estadoV;
        this.lote = lote;
        this.estadoPL = estadoPL;
        this.estadoGeneral = estadoGeneral;
        this.litrosProducidos = litrosProducidos;
        this.precio = precio;
        this.fechaProduccion = fechaProduccion;
    }
    
    
    
    public Ganado()
    {
        
    }

    public String getEstadoGeneral() {
        return estadoGeneral;
    }

    public Date getFechaProduccion() {
        return fechaProduccion;
    }

    public float getPrecio() {
        return precio;
    }

    
    
    public int getLitrosProducidos() {
        return litrosProducidos;
    }
    
    
    
    public String getNatal() {
        return natal;
    }

    public void setNatal(String natal) {
        this.natal = natal;
    }

    public void setEstadoGeneral(String estadoGeneral) {
        this.estadoGeneral = estadoGeneral;
    }

    public void setLitrosProducidos(int litrosProducidos) {
        this.litrosProducidos = litrosProducidos;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setFechaProduccion(Date fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstadoV() {
        return estadoV;
    }

    public void setEstadoV(String estadoV) {
        this.estadoV = estadoV;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getEstadoPL() {
        return estadoPL;
    }

    public void setEstadoPL(String estadoPL) {
        this.estadoPL = estadoPL;
    }

    @Override
    public String toString() {
        return "Ganado{" + "id=" + id + ", fechaNacimiento=" + fechaNacimiento + ", natal=" + natal + ", estadoV=" + estadoV + ", lote=" + lote + ", estadoPL=" + estadoPL + ", estadoGeneral=" + estadoGeneral + ", litrosProducidos=" + litrosProducidos + ", precio=" + precio + ", fechaProduccion=" + fechaProduccion + '}';
    }

 
    
}
