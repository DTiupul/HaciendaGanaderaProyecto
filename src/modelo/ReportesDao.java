
package modelo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class ReportesDao {
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
    

public List<Ganado> obtenerGanado() {
    List<Ganado> listaganado1 = new ArrayList<>(); // declaración array list
    Conexion objCon = new Conexion();
    DBCursor cursor = objCon.coleccion2.find();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Formato de fecha

    while (cursor.hasNext()) {
        BasicDBObject documento = (BasicDBObject) cursor.next();

        String id = documento.getString("id");
        Date fechaNacimientoDate = documento.getDate("fechaNacimiento");
        String natal = documento.getString("natal");
        String estado = documento.getString("estado");
        String lote = documento.getString("lote");

        Ganado ganado = new Ganado(id, fechaNacimientoDate, natal, estado, lote);
        listaganado1.add(ganado);
    }

    return listaganado1;
}
}
