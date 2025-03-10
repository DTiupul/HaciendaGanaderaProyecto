
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.FacadeDAO;
import modelo.Ganado;
import modelo.GanadoDAO;
import modelo.Observador;
import modelo.ReportesDao;
import vista.AgregarGanado;
import vista.FrmReportes;


public class ControladorReportes implements ActionListener,Observador{

    
    FrmReportes objetoVista=new FrmReportes();
    FacadeDAO objetoDAO= new FacadeDAO(); 
    
  public ControladorReportes( FrmReportes vista, FacadeDAO dao)
  {
      objetoVista=vista;
      objetoDAO= dao;
      objetoVista.btnPDF.addActionListener(this);
  }

    
   public void mostrarAnimalesEnTabla(List<Ganado> listaGanados) {
    DefaultTableModel modeloT = new DefaultTableModel();
    
    //VERIFICAR BIEN EL NOMBRE DE LA TABLA A USAR
    objetoVista.TablaPdf.setModel(modeloT); // Asegúrate de usar el nombre correcto de la tabla en la vista
    
    // Agregar columnas a la tabla
    modeloT.addColumn("Id");
    modeloT.addColumn("fechadeNacimiento");
    modeloT.addColumn("NATAL");
    modeloT.addColumn("ESTADO");
    modeloT.addColumn("LOTE");
    
  
    for (Ganado ganado : listaGanados) {
        Object[] fila = new Object[5];
        fila[0] = ganado.getId();
        fila[1] = ganado.getFechaNacimiento();
        fila[2] = ganado.getNatal();
        fila[3] = ganado.getEstadoV();
        fila[4] = ganado.getLote();
        
        
        modeloT.addRow(fila);
    }

    // Actualizar la tabla
    objetoVista.TablaPdf.repaint();
    objetoVista.TablaPdf.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
       
        if (e.getSource()==objetoVista.btnPDF)
       {
         List<Ganado> listaganado = objetoDAO.obtenerGanadoT();
           mostrarAnimalesEnTabla(listaganado);  
       }
    }

    @Override
    public void actualizar() {
        List<Ganado> listaganado = objetoDAO.obtenerGanadoT();
        mostrarAnimalesEnTabla(listaganado);
    }
}

