/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author josed
 */
public class Lotes {
    private String nombre;
    private float tamaño;
    private String estadoL;

    public Lotes(String nombre, float tamaño, String estadoL) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.estadoL = estadoL;
    }
    
    public Lotes()
    {
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getTamaño() {
        return tamaño;
    }

    public void setTamaño(float tamaño) {
        this.tamaño = tamaño;
    }

    public String getEstadoL() {
        return estadoL;
    }

    public void setEstadoL(String estadoL) {
        this.estadoL = estadoL;
    }
    
    
    
}
