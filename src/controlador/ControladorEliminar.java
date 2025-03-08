/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Ganado;
import modelo.GanadoDAO;
import vista.Eliminar;
import vista.Vender;

/**
 *
 * @author Roberto
 */
public class ControladorEliminar implements ActionListener{
    
    GanadoDAO objetoDAO= new GanadoDAO();
    Eliminar objetoVista= new Eliminar();
    
    
    public ControladorEliminar(Eliminar vista,GanadoDAO dao)
    {
        objetoVista=vista;
        objetoDAO=dao;
        objetoVista.btnMostrar.addActionListener(this);
        objetoVista.btnEliminar.addActionListener(this);
    }
    
   
//    public void eliminarAuto(){
//        int filaSeleccionada = objetoVista.tablaMostrar.getSelectedRow();
//        objetoVista.tablaMostrar.getSelectedRow();
//        if (filaSeleccionada != -1) {
//            String id = (String) objetoVista.tablaMostrar.getValueAt(filaSeleccionada, 0);
//            boolean eliminado = objetoDAO.eliminarVaca(id);
//        if (eliminado) {
//            DefaultTableModel model = (DefaultTableModel) objetoVista.tablaMostrar.getModel();
//            model.removeRow(filaSeleccionada);
//            JOptionPane.showMessageDialog(null, "Auto eliminado exitosamente");
//        } else {
//            JOptionPane.showMessageDialog(null, "Error al eliminar el auto");
//        }
//    } else {
//        JOptionPane.showMessageDialog(null, "Seleccione un auto para eliminar");
//    }
//    }

    public void mostrarVacas()
    {
        
        DefaultTableModel modeloT = new DefaultTableModel();

        // Asignar el modelo a la tabla directamente
        objetoVista.tablaMostrar.setModel(modeloT);

        // Agregar las columnas correspondientes
        modeloT.addColumn("ID");
        modeloT.addColumn("FECHA DE NACIMIENTO");
        modeloT.addColumn("NATAL");
        modeloT.addColumn("ESTADO");
        modeloT.addColumn("LOTE");

        // Obtener la lista de ganado desde el DAO
        GanadoDAO ganadoDAO = new GanadoDAO();
        List<Ganado> listaGanados = ganadoDAO.obtenerGanado();

        // Recorrer la lista y agregar los datos a la tabla
        for (Ganado ganado : listaGanados) {
            Object[] fila = new Object[5]; // 5 columnas
            fila[0] = ganado.getId();
            fila[1] = ganado.getFechaNacimiento();  // Puedes formatear la fecha si es necesario
            fila[2] = ganado.getNatal();
            fila[3] = ganado.getEstadoV();
            fila[4] = ganado.getLote();

            modeloT.addRow(fila);
        }

        // Forzar la actualización visual de la tabla
        objetoVista.tablaMostrar.repaint();
        objetoVista.tablaMostrar.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==objetoVista.btnMostrar)
        {
            mostrarVacas();
            
            
        }
    }
    
    
    
}
