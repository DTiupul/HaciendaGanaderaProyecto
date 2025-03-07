/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 *
 * @author josed
 */
public class Conexion {
    DB baseDatos1;
    DBCollection coleccion1;
    DBCollection coleccion2;
    DBCollection coleccion3;
    DBCollection coleccion4;
    DBCollection coleccion5;
    DBCollection coleccion6;
    DBCollection coleccion7;
    
    
    public Conexion(){
        MongoClient mongo=new MongoClient("localhost",27017);
        
        baseDatos1= mongo.getDB("hacienda");
        coleccion1= baseDatos1.getCollection("usuarios");
        coleccion2= baseDatos1.getCollection("ganado");
        coleccion3= baseDatos1.getCollection("lotes");
        coleccion4= baseDatos1.getCollection("ganadoVenta");
        coleccion5= baseDatos1.getCollection("ganadoLactea");
        coleccion6= baseDatos1.getCollection("ganadosProduccionLactea");
        coleccion7= baseDatos1.getCollection("ganadoVendido");
        
        //System.out.println("Conexion exitosa");
        
    }
}
