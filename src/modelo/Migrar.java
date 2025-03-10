
package modelo;

import com.mongodb.BasicDBObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Migrar {
     public void migrarDesdeArchivoPlano(String rutaArchivo) {
        Conexion objCon = new Conexion(); // Conexión a MongoDB
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" "); // Divide la línea en partes
                
                if (partes.length == 3) { // Verifica que tenga los 5 campos
                    // Extraer valores del archivo
                    String nombre = partes[0];
                    float tamaño = Float.parseFloat(partes[1]);
                    String estado = partes[2];
                    
                    // Crear documento para MongoDB
                    BasicDBObject documento = new BasicDBObject();
                    documento.put("nombre", nombre);
                    documento.put("tamaño", tamaño);
                    documento.put("estado", estado);
                    

                    // Insertar en la colección
                    objCon.coleccion3.insert(documento);
                    JOptionPane.showMessageDialog(null, "Migracion Hecha");
                } else {
                    JOptionPane.showMessageDialog(null, "Migracion Erronea");
                }
            }
            System.out.println("Migración completada exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir valores numéricos: " + e.getMessage());
        }
    }
}
