/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import javax.swing.JOptionPane;
import javax.swing.text.Document;
import modelo.Conexion;
import vista.FrmLogin;
import vista.MenuPrincipal;


    
    
   public class ConsultasDAO {
    
    
    private static ConsultasDAO instancia;  // Única instancia de la clase

    // Constructor privado para evitar instanciación directa
    private ConsultasDAO() {}

    // Método estático para obtener la instancia única
    public static ConsultasDAO getInstancia() {
        if (instancia == null) {
            instancia = new ConsultasDAO();
        }
        return instancia;
    }

    public void guardarUsuario(String usuario, String clave) {
        Conexion objCon = new Conexion();
        
        BasicDBObject documento = new BasicDBObject();
        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese Los Datos");
        } else {
            if (verificarUsuario(usuario)) {
                JOptionPane.showMessageDialog(null, "Usuario Ya Agregado");
            } else {
                documento.put("usuario", usuario);
                documento.put("clave", clave);
                objCon.coleccion1.insert(documento);
                JOptionPane.showMessageDialog(null, "Usuario Registrado");
            }
        }
    }

    public void accederUsuario(String id, String clave, FrmLogin frmLogin) {
        Conexion objCon = new Conexion();
        
        BasicDBObject queryColeccion = new BasicDBObject();
        queryColeccion.put("usuario", id);
        
        BasicDBObject usuarioEnColeccion = (BasicDBObject) objCon.coleccion1.findOne(queryColeccion);
        
        if (usuarioEnColeccion != null) {
            String claveGuardada = usuarioEnColeccion.getString("clave");
            if (clave.equals(claveGuardada)) {
                JOptionPane.showMessageDialog(null, "Acceso concedido. Bienvenido, " + id + "!");
                frmLogin.setVisible(false);
                
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
        }
    }

    public boolean verificarUsuario(String id) {
        Conexion objCon = new Conexion();
        BasicDBObject queryColeccion = new BasicDBObject();
        queryColeccion.put("usuario", id);
        
        BasicDBObject usu = (BasicDBObject) objCon.coleccion1.findOne(queryColeccion);
        return usu != null;
    }
}
    

