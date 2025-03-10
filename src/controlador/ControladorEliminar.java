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
import modelo.FacadeDAO;
import modelo.Ganado;
import modelo.GanadoDAO;
import vista.FrmEliminar;
import vista.Vender;

/**
 *
 * @author Roberto
 */
public class ControladorEliminar implements ActionListener{
    
   FacadeDAO objetoDAO= new FacadeDAO();
    FrmEliminar objetoVista= new FrmEliminar();
    
    
    public ControladorEliminar(FrmEliminar vista,FacadeDAO dao)
    {
        objetoVista=vista;
        objetoDAO=dao;
        objetoVista.btnMostrar.addActionListener(this);
        objetoVista.btnEliminar.addActionListener(this);
    }

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
    
    public void eliminarVaca()
    {
        int filaSeleccionada = objetoVista.tablaMostrar.getSelectedRow();
        objetoVista.tablaMostrar.getSelectedRow();
        
        
        if (filaSeleccionada != -1) 
        {
            
            String id = (String) objetoVista.tablaMostrar.getValueAt(filaSeleccionada, 0);

            
                boolean rentado = objetoDAO.eliminarVaca(id);
                 
                //REMUEVE LA FILA DONDE SE ENCONTRABA EL OBJETO, UNA VEZ USADO EL ELEMENTO QUE SELECCIONAMOS
                if (rentado) {
                DefaultTableModel model = (DefaultTableModel) objetoVista.tablaMostrar.getModel();
                model.removeRow(filaSeleccionada);
                
                //VERIFICAR SI SE REALIZO CORRECTAMENTE O NO
                JOptionPane.showMessageDialog(null, "Vaca eliminada exitosamente");
                } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar vaca");
                }

        } else {    
        //POR SI NO SE SELECCIONO UNA OPCION
        JOptionPane.showMessageDialog(null, "Seleccione una vaca para eliminar");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==objetoVista.btnMostrar)
        {
            mostrarVacas();    
        }
        if(e.getSource()==objetoVista.btnEliminar)
        {
            eliminarVaca();
        }
    }
    
    
    
}
