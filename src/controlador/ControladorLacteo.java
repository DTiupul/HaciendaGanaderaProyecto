
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Ganado;
import modelo.LacteosDao;
import vista.FrmLacteos;



public class ControladorLacteo implements ActionListener{
    FrmLacteos objetoVista=new FrmLacteos();
    LacteosDao objetoDao= new LacteosDao(); 
    
    public ControladorLacteo( FrmLacteos vista, LacteosDao dao)
  {
   objetoVista=vista;
   objetoDao=dao;
   
   objetoVista.btnPDF1.addActionListener(this);
      
  }

    
   public void mostrarAnimalesEnTabla(List<Ganado> listaGanados) {
    DefaultTableModel modeloT = new DefaultTableModel();
    
    //VERIFICAR BIEN EL NOMBRE DE LA TABLA A USAR
    objetoVista.TablaPdf1.setModel(modeloT); // Asegúrate de usar el nombre correcto de la tabla en la vista
    
    // Agregar columnas a la tabla
    modeloT.addColumn("Id");
    modeloT.addColumn("fechadeNacimiento");
    modeloT.addColumn("NATAL");
    modeloT.addColumn("ESTADO");
    modeloT.addColumn("LOTE");
    modeloT.addColumn("LitrosProducidos");
    
  
    for (Ganado ganado : listaGanados) {
        Object[] fila = new Object[6];
        fila[0] = ganado.getId();
        fila[1] = ganado.getFechaNacimiento();
        fila[2] = ganado.getNatal();
        fila[3] = ganado.getEstadoV();
        fila[4] = ganado.getLote();
        fila[5]= ganado.getLitrosProducidos();
        
        
        modeloT.addRow(fila);
    }

    // Actualizar la tabla
    objetoVista.TablaPdf1.repaint();
    objetoVista.TablaPdf1.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
       
        if (e.getSource()==objetoVista.btnPDF1)
       {
         List<Ganado> listaganado = objetoDao.obtenerGanado();
           mostrarAnimalesEnTabla(listaganado);  
       }
    }
    
}

