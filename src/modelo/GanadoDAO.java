/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import modelo.Conexion;

/**
 *
 * @author josed
 */
public class GanadoDAO {
    
    public void insertarGanado(String id, Date fechaNacimiento,String natal, String estado, String lote)
    {
        Conexion objCon= new Conexion();
        BasicDBObject documento= new BasicDBObject();
        documento.put("id", id);
        documento.put("fechaNacimiento", fechaNacimiento);
        documento.put("natal",natal);
        documento.put("estado",estado);
        documento.put("lote", lote);
        objCon.coleccion2.insert(documento);
        JOptionPane.showMessageDialog(null, "Funciona Insertar");
    }
    
    public List<Lotes> obtenerLotes()
    {
        //PARA OBTENER AUTOS DE UNA COLECCION,EN ESTE CASO COLECCION 1
        List<Lotes> listaLotes= new ArrayList<>(); //declaracion array list
        
        Conexion objCon= new Conexion();
        DBCursor cursor=objCon.coleccion3.find();
        while (cursor.hasNext()){
            BasicDBObject documento = (BasicDBObject) cursor.next();
            
            
            String nombre = documento.getString("nombre");
            
            String tamaño1 = documento.getString("tamaño");
            float tamaño = Float.parseFloat(tamaño1); 
            
            String estado = documento.getString("estado");
            
            
            
        
         Lotes lote = new Lotes(nombre, tamaño, estado);
         listaLotes.add(lote);
        }
        
        return listaLotes;
       
    }
    
    
    
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
    
    public boolean validarLote(String lote)
    {
        Conexion objCon = new Conexion();
        BasicDBObject queryColeccion3 = new BasicDBObject();
        queryColeccion3.put("nombre", lote);
        BasicDBObject loteEnColeccion3 = (BasicDBObject) objCon.coleccion3.findOne(queryColeccion3);
        return loteEnColeccion3 != null;
        
    }
    
    public void verificarVentaOLactea(String id, String natal, String estado) 
    {
        Conexion objCon = new Conexion();
        Random random = new Random();

        // Verificar el valor del argumento 'natal'
        if ("Macho".equalsIgnoreCase(natal) || "Gemelo".equalsIgnoreCase(natal) || "Hembra".equalsIgnoreCase(natal)) {
            // Crear la consulta para buscar en coleccion2 por ID
            BasicDBObject queryColeccion2 = new BasicDBObject();
            queryColeccion2.put("id", id); // Buscar por el ID proporcionado

            // Buscar el documento en coleccion2
            BasicDBObject loteEnColeccion2 = (BasicDBObject) objCon.coleccion2.findOne(queryColeccion2);

            if (loteEnColeccion2 != null) {
                // Extraer atributos del documento de coleccion2
                String idDoc = loteEnColeccion2.getString("id");
                Date fechaNacimiento = loteEnColeccion2.getDate("fechaNacimiento"); // Obtener como Date
                String natalDoc = loteEnColeccion2.getString("natal");
                String lote = loteEnColeccion2.getString("lote");

                // Crear un documento para insertar en la colección correspondiente
                BasicDBObject documento = new BasicDBObject();
                documento.put("id", idDoc);
                documento.put("fechaNacimiento", fechaNacimiento); // Guardar como Date
                documento.put("natal", natalDoc);
                documento.put("estado", estado); // Estado ahora proviene del argumento
                documento.put("lote", lote);

                if ("Macho".equalsIgnoreCase(natalDoc)) {
                    // Asignar precio entre 60.0 y 90.0 para "Macho"
                    float precio = 60 + random.nextFloat() * (90 - 60);
                    documento.put("precio", precio);
                    objCon.coleccion4.insert(documento); // Insertar en coleccion4
                    JOptionPane.showMessageDialog(null, "Se añadió el documento a colección 4 con el precio asignado (Macho).");
                } else if ("Gemelo".equalsIgnoreCase(natalDoc)) {
                    // Asignar precio entre 40.0 y 60.0 para "Gemelo"
                    float precio = 40 + random.nextFloat() * (60 - 40);
                    documento.put("precio", precio);
                    objCon.coleccion4.insert(documento); // Insertar en coleccion4
                    JOptionPane.showMessageDialog(null, "Se añadió el documento a colección 4 con el precio asignado (Gemelo).");
                } else if ("Hembra".equalsIgnoreCase(natalDoc)) {
                    // Verificar si es apta para láctea (mayor de 2 años) y si está sana
                    boolean esAptoLactea = verificarAptoLactea(fechaNacimiento);
                    boolean esSano = estado != null && "sano".equalsIgnoreCase(estado);

                    if (esAptoLactea && esSano) {
                        objCon.coleccion5.insert(documento);
                        JOptionPane.showMessageDialog(null, "Se añadió el documento a colección 5 (Hembra apta y sana).");
                    } else if (!esAptoLactea) {
                        JOptionPane.showMessageDialog(null, "El animal no es apto para la categoría láctea (menor de 2 años).");
                    } else if (!esSano) {
                        JOptionPane.showMessageDialog(null, "El animal no está sano y no puede ser añadido a colección 5.");
                    }
                }

                System.out.println("Documento asignado: " + documento);
            } else {
                System.out.println("No se encontró un documento en coleccion2 con 'id' = " + id);
                JOptionPane.showMessageDialog(null, "No se encontró un documento con el ID proporcionado.");
            }
        } else {
            System.out.println("El valor de 'natal' no es válido.");
            JOptionPane.showMessageDialog(null, "El valor de 'natal' debe ser 'Macho', 'Gemelo' o 'Hembra'.");
        }
    }
    
    
    public boolean verificarAptoLactea(Date fechaNacimiento) {
        if (fechaNacimiento == null) {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento es nula.");
            return false;
        }

        // Obtener la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaNacimientoCal = Calendar.getInstance();
        fechaNacimientoCal.setTime(fechaNacimiento);

        // Calcular la diferencia en años
        int añosDiferencia = fechaActual.get(Calendar.YEAR) - fechaNacimientoCal.get(Calendar.YEAR);

        // Ajustar si aún no ha pasado el cumpleaños en el año actual
        if (fechaActual.get(Calendar.DAY_OF_YEAR) < fechaNacimientoCal.get(Calendar.DAY_OF_YEAR)) {
            añosDiferencia--;
        }

        // Verificar si la diferencia en años es mayor o igual a 2
        if (añosDiferencia >= 2) {
            JOptionPane.showMessageDialog(null, "El animal es apto para la categoría láctea.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El animal NO es apto para la categoría láctea.");
            return false;
        }
    }
    
    public boolean agregarAGanadoAPL(String id) {
    Conexion objCon = new Conexion(); 
    BasicDBObject query = new BasicDBObject(); 
    query.put("id", id);  // Buscar ganado por el ID proporcionado
    
    // Buscar ganado en la colección 5
    BasicDBObject ganado = (BasicDBObject) objCon.coleccion5.findOne(query);
    
        if (ganado != null) {
            // Crear un nuevo objeto para moverlo a la colección 6
            BasicDBObject ganadoEnColeccion6 = new BasicDBObject();

            // Agregar atributos del ganado al nuevo objeto
            ganadoEnColeccion6.put("id", ganado.getString("id"));
            ganadoEnColeccion6.put("fechaNacimiento", ganado.get("fechaNacimiento"));
            ganadoEnColeccion6.put("natal", ganado.getString("natal"));
            ganadoEnColeccion6.put("estado", ganado.getString("estado"));
            ganadoEnColeccion6.put("lote", ganado.getString("lote"));

            // Generar el valor aleatorio de litrosProducidos entre 800 y 1200
            Random random = new Random();
            int litrosProducidos = 800 + random.nextInt(401); // Genera un número entre 800 y 1200
            ganadoEnColeccion6.put("litrosProducidos", litrosProducidos);

            // Insertar el ganado en la colección 6
            objCon.coleccion6.insert(ganadoEnColeccion6);
            objCon.coleccion5.remove(ganadoEnColeccion6);

            return true;  // Indicar que la operación fue exitosa
        }
        return false;  // No se encontró el ganado en la colección 5
    }
    
    public boolean agregarAGanadoVendido(String id) {
        Conexion objCon = new Conexion(); 
        BasicDBObject query = new BasicDBObject(); 
        query.put("id", id);  // Buscar ganado por el ID proporcionado

        // Buscar ganado en la colección 4
        BasicDBObject ganado = (BasicDBObject) objCon.coleccion4.findOne(query);

        if (ganado != null) {
            // Crear un nuevo objeto para moverlo a la colección 7
            BasicDBObject ganadoEnColeccion7 = new BasicDBObject();

            // Agregar atributos del ganado al nuevo objeto
            ganadoEnColeccion7.put("id", ganado.getString("id"));
            ganadoEnColeccion7.put("fechaNacimiento", ganado.get("fechaNacimiento"));
            ganadoEnColeccion7.put("natal", ganado.getString("natal"));
            ganadoEnColeccion7.put("estado", ganado.getString("estado"));
            ganadoEnColeccion7.put("lote", ganado.getString("lote"));

            // Usar el precio del ganado directamente
            String precio1 = ganado.getString("precio");
            Float precio= Float.parseFloat(precio1);
            ganadoEnColeccion7.put("precio", precio);

            // Insertar el ganado en la colección 7
            objCon.coleccion7.insert(ganadoEnColeccion7);
            objCon.coleccion4.remove(ganado);

            // Mostrar reporte en una ventana emergente
            JOptionPane.showMessageDialog(null, 
                "Ganado con ID: " + ganado.getString("id") + 
                "\nPrecio de venta: " + precio);

            return true;  // Indicar que la operación fue exitosa
        }
        return false;  // No se encontró el ganado en la colección 4
    }
    
    public List<Ganado>obtenerGanadoV()
    {
        List<Ganado> listaGanado = new ArrayList<>(); // Declaración de ArrayList
        Conexion objCon = new Conexion(); // Objeto de conexión
        DBCursor cursor = objCon.coleccion4.find();
        while (cursor.hasNext()) {
            BasicDBObject documento = (BasicDBObject) cursor.next();

            // Obtener datos del documento
            String id = documento.getString("id");
            String natal = documento.getString("natal");
            String estado = documento.getString("estado");
            String lote = documento.getString("lote");
            Date fechaNacimiento = documento.getDate("fechaNacimiento");
            String precio1= documento.getString("precio");
            Float precio=Float.parseFloat(precio1);
            

            // Crear un objeto Ganado
            Ganado ganado = new Ganado(id, natal, estado, lote,precio);

            // Agregarlo a la lista
            listaGanado.add(ganado);
        }

        return listaGanado;
    }
    
    
    public List<Ganado> obtenerGanadoPL() {
        // PARA OBTENER GANADO DE LA COLECCION 5

        List<Ganado> listaGanado = new ArrayList<>(); // Declaración de ArrayList
        Conexion objCon = new Conexion(); // Objeto de conexión
        DBCursor cursor = objCon.coleccion5.find(); // Obtener documentos de la colección 5

        while (cursor.hasNext()) {
            BasicDBObject documento = (BasicDBObject) cursor.next();

            // Obtener datos del documento
            String id = documento.getString("id");
            String natal = documento.getString("natal");
            String estado = documento.getString("estado");
            String lote = documento.getString("lote");
            Date fechaNacimiento = documento.getDate("fechaNacimiento");

            // Crear un objeto Ganado
            Ganado ganado = new Ganado(id, fechaNacimiento, natal, estado, lote);

            // Agregarlo a la lista
            listaGanado.add(ganado);
        }

        return listaGanado; // Retornar la lista
    }
    
    public List<Ganado> obtenerGanadoPPL() {
    // PARA OBTENER GANADO DE LA COLECCION 6

    List<Ganado> listaGanado = new ArrayList<>(); // Declaración de ArrayList
    Conexion objCon = new Conexion(); // Objeto de conexión
    DBCursor cursor = objCon.coleccion6.find(); // Obtener documentos de la colección 6

        while (cursor.hasNext()) {
            BasicDBObject documento = (BasicDBObject) cursor.next();

            // Obtener datos del documento
            String id = documento.getString("id");
            String natal = documento.getString("natal");
            String estado = documento.getString("estado");
            String lote = documento.getString("lote");
            Date fechaNacimiento = documento.getDate("fechaNacimiento");

            // Obtener el nuevo atributo 'litrosProducidos'
            int litrosProducidos = documento.getInt("litrosProducidos");

            // Crear un objeto Ganado con el nuevo atributo
            Ganado ganado = new Ganado(id, fechaNacimiento, natal, estado, lote, litrosProducidos);

            // Agregarlo a la lista
            listaGanado.add(ganado);
            
        }

        return listaGanado; // Retornar la lista con los objetos Ganado
    }
    
    public boolean verificarVacaS(String id) {
    Conexion objCon = new Conexion();
    BasicDBObject queryColeccion6 = new BasicDBObject();
    queryColeccion6.put("id", id);
    BasicDBObject autoEnColeccion3 = (BasicDBObject) objCon.coleccion6.findOne(queryColeccion6);

    return autoEnColeccion3 != null;  // Retorna true si el auto está en la colección 3 (ya rentado)
    }
    
    public boolean verificarVacaS2(String id) {
    Conexion objCon = new Conexion();
    BasicDBObject queryColeccion7 = new BasicDBObject();
    queryColeccion7.put("id", id);
    BasicDBObject autoEnColeccion3 = (BasicDBObject) objCon.coleccion7.findOne(queryColeccion7);

    return autoEnColeccion3 != null;  // Retorna true si el auto está en la colección 3 (ya rentado)
    }

    public List<Ganado> obtenerGanado() {
    // PARA OBTENER GANADO DE LA COLECCION 6

    List<Ganado> listaGanado = new ArrayList<>(); // Declaración de ArrayList
    Conexion objCon = new Conexion(); // Objeto de conexión
    DBCursor cursor = objCon.coleccion2.find(); // Obtener documentos de la colección 6

        while (cursor.hasNext()) {
            BasicDBObject documento = (BasicDBObject) cursor.next();

            // Obtener datos del documento
            String id = documento.getString("id");
            String natal = documento.getString("natal");
            String estado = documento.getString("estado");
            String lote = documento.getString("lote");
            Date fechaNacimiento = documento.getDate("fechaNacimiento");

            // Crear un objeto Ganado con el nuevo atributo
            Ganado ganado = new Ganado(id, fechaNacimiento, natal, estado, lote);

            // Agregarlo a la lista
            listaGanado.add(ganado);
            
        }

        return listaGanado; // Retornar la lista con los objetos Ganado
    }
    
    public boolean eliminarVaca(String id) {
    // Crear un objeto de conexión
    Conexion objCon = new Conexion();

        // Definir las colecciones donde se eliminará la vaca
        boolean eliminada = false;

        try {
            objCon.coleccion2.remove(new BasicDBObject("id", id));
            objCon.coleccion4.remove(new BasicDBObject("id", id));
            objCon.coleccion5.remove(new BasicDBObject("id", id));
            objCon.coleccion6.remove(new BasicDBObject("id", id));
            objCon.coleccion7.remove(new BasicDBObject("id", id));

            eliminada = true;

            // Mostrar mensaje de éxito con JOptionPane
            JOptionPane.showMessageDialog(null, "Vaca con ID " + id + " eliminada exitosamente.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            // Si ocurre un error, mostrar mensaje de error
            JOptionPane.showMessageDialog(null, "Error al eliminar la vaca con ID " + id + ".\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            eliminada = false;
        }

        return eliminada;
    }
}
