
package modelo;

import java.util.Date;
import java.util.List;


    
    public class FacadeDAO {
    private GanadoDAO ganadoDAO;
    private LacteosDao lacteosDAO;
    private ReportesDao reportesDAO;
    private ConsultasDAO consultasDAO;
    private LotesDAO lotesDAO;
    
    Conexion objCon = new Conexion();

    public FacadeDAO() {
        this.ganadoDAO = new GanadoDAO();
        this.lacteosDAO = new LacteosDao();
        this.reportesDAO = new ReportesDao();
        this.consultasDAO = ConsultasDAO.getInstancia();
        this.lotesDAO= new LotesDAO();
    }
    

    // Métodos GanadoDAO
    //revisar
    public void insertarGanado(String id, Date fechaNacimiento, String natal, String estado, String lote) {
        ganadoDAO.insertarGanado(id, fechaNacimiento, natal, estado, lote);
    }
    //
    
    public boolean eliminarVaca(String id){
        return ganadoDAO.eliminarVaca(id);
    }
    public boolean verificarVacaS(String id){
        return ganadoDAO.verificarVacaS(id);
    }
    
    public boolean agregarAGanadoAPL(String id){
        return ganadoDAO.agregarAGanadoAPL(id);
    }
    
    public List<Ganado> obtenerGanadoPL(){
        return ganadoDAO.obtenerGanadoPL();
    }
    
    public List<Ganado> obtenerGanadoPPL(){
        return ganadoDAO.obtenerGanadoPPL();
    }
    
    public void verificarVentaOLactea(String id, String natal, String estado){
        ganadoDAO.verificarVentaOLactea(id, natal, estado);
    }
    
    public boolean verificarVacaS2(String id){
        return ganadoDAO.verificarVacaS2(id);
    }
    public boolean agregarAGanadoVendido(String id){
        return ganadoDAO.agregarAGanadoVendido(id);
    }
    public List<Ganado>obtenerGanadoV(){
        return ganadoDAO.obtenerGanadoV();
    }
    
    // Métodos LacteosDao
    public void insertarLacteo(String id, Date fechaNacimiento, String natal, String estado, String lote, int litrosProducidos) {
        lacteosDAO.insertarGanado(id, fechaNacimiento, natal, estado, lote, litrosProducidos);
    }
    
    public List<Ganado> obtenerGanado(){
        return lacteosDAO.obtenerGanado();
    }

    // Métodos ReportesDao
   public List<Ganado> obtenerGanadoT(){
       return reportesDAO.obtenerGanadoT();
   }

    // Métodos ConsultasDAO
    public void guardarUsuario(String usuario, String clave) {
        consultasDAO.guardarUsuario(usuario, clave);
    }
    
    //Métodos LotesDAO
    public boolean validarLote(String lote)
    {
        return lotesDAO.validarLote(lote);
    }
    public List<Lotes> obtenerLotes(){
        return lotesDAO.obtenerLotes();
    }
}
