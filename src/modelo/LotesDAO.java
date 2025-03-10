
package modelo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import java.util.ArrayList;
import java.util.List;


    public class LotesDAO {
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
    
    public boolean validarLote(String lote)
    {
        Conexion objCon = new Conexion();
        BasicDBObject queryColeccion3 = new BasicDBObject();
        queryColeccion3.put("nombre", lote);
        BasicDBObject loteEnColeccion3 = (BasicDBObject) objCon.coleccion3.findOne(queryColeccion3);
        return loteEnColeccion3 != null;
        
    }
}

