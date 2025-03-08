
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Ganado;
import modelo.GanadoDAO;
import modelo.Lotes;
import vista.AgregarGPL;
import vista.AgregarGanado;

public class ControladorAgregarGPL implements ActionListener {
    Ganado vaca = new Ganado();
    GanadoDAO objetoDAO= new GanadoDAO();
    AgregarGPL objetoVista= new AgregarGPL();
    Lotes lote= new Lotes();
    
    public ControladorAgregarGPL(AgregarGPL vista, GanadoDAO dao)
    {
        objetoVista=vista;
        objetoDAO= dao;
        objetoVista.btnAgregarGPL.addActionListener(this);
        objetoVista.btnMostrar.addActionListener(this);
        objetoVista.btnMostrarAG.addActionListener(this);
    }
    
    // Método para mostrar todos los ganados en una tabla
    public void mostrarGanadosEnTabla(List<Ganado> listaGanados) {
        DefaultTableModel modeloT = new DefaultTableModel();
        
        // Verificar que el nombre de la tabla a usar en la vista sea correcto
        objetoVista.tablaDisponibles.setModel(modeloT); // Asegúrate de usar el nombre correcto de la tabla en la vista
        
        // Agregar columnas a la tabla
        modeloT.addColumn("ID");
        modeloT.addColumn("FECHA DE NACIMIENTO");
        modeloT.addColumn("NATAL");
        modeloT.addColumn("ESTADO");
        modeloT.addColumn("LOTE");

        // Recorrer la lista de ganados y agregarlos a la tabla
        for (Ganado ganado : listaGanados) {
            Object[] fila = new Object[5];
            fila[0] = ganado.getId();
            fila[1] = ganado.getFechaNacimiento();  // Puedes formatear la fecha si lo necesitas
            fila[2] = ganado.getNatal();
            fila[3] = ganado.getEstadoV();
            fila[4] = ganado.getLote();
            modeloT.addRow(fila);
        }

        // Actualizar la tabla
        objetoVista.tablaDisponibles.repaint();
        objetoVista.tablaDisponibles.revalidate();
    }
    public void mostrarGanadosEnTabla2(List<Ganado> listaGanados) {
        DefaultTableModel modeloT = new DefaultTableModel();
        
        // Verificar que el nombre de la tabla a usar en la vista sea correcto
        objetoVista.tablaMostrarAgregados.setModel(modeloT); // Asegúrate de usar el nombre correcto de la tabla en la vista
        
        // Agregar columnas a la tabla
        modeloT.addColumn("ID");
        modeloT.addColumn("FECHA DE NACIMIENTO");
        modeloT.addColumn("NATAL");
        modeloT.addColumn("ESTADO");
        modeloT.addColumn("LOTE");
        modeloT.addColumn("LITROS");

        // Recorrer la lista de ganados y agregarlos a la tabla
        for (Ganado ganado : listaGanados) {
            Object[] fila = new Object[6];
            fila[0] = ganado.getId();
            fila[1] = ganado.getFechaNacimiento();  // Puedes formatear la fecha si lo necesitas
            fila[2] = ganado.getNatal();
            fila[3] = ganado.getEstadoV();
            fila[4] = ganado.getLote();
            fila[5] = ganado.getLitrosProducidos();
            modeloT.addRow(fila);
        }

        // Actualizar la tabla
        objetoVista.tablaMostrarAgregados.repaint();
        objetoVista.tablaMostrarAgregados.revalidate();
    }
    public void agregarAPL(){
        
        //PARA SELECCIONAR UN ELMENTO EN LA 1ERA TABLA USANDO LAS FILAS
        //PARA SELECCIONAR UNA FILA DE LA TABLA CON EL MOUSE
        int filaSeleccionada = objetoVista.tablaDisponibles.getSelectedRow();
        objetoVista.tablaDisponibles.getSelectedRow();
        
        
        if (filaSeleccionada != -1) 
        {
            
            String id = (String) objetoVista.tablaDisponibles.getValueAt(filaSeleccionada, 0);

            if (objetoDAO.verificarVacaS(id)) {
                    JOptionPane.showMessageDialog(null, "Esta vaca ya está agregada.");
                    return;
            }
            
                boolean rentado = objetoDAO.agregarAGanadoAPL(id);
                 
                //REMUEVE LA FILA DONDE SE ENCONTRABA EL OBJETO, UNA VEZ USADO EL ELEMENTO QUE SELECCIONAMOS
                if (rentado) {
                DefaultTableModel model = (DefaultTableModel) objetoVista.tablaDisponibles.getModel();
                model.removeRow(filaSeleccionada);
                
                //VERIFICAR SI SE REALIZO CORRECTAMENTE O NO
                JOptionPane.showMessageDialog(null, "Vaca agregada exitosamente");
                } else {
                JOptionPane.showMessageDialog(null, "Error al añadir vaca");
                }

        } else {    
        //POR SI NO SE SELECCIONO UNA OPCION
        JOptionPane.showMessageDialog(null, "Seleccione una vaca para agregar");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource()==objetoVista.btnAgregarGPL)
       {
           agregarAPL();
       }
        if (e.getSource()==objetoVista.btnMostrar)
       {
           List<Ganado> listaGanados1 = objetoDAO.obtenerGanadoPL();
           mostrarGanadosEnTabla(listaGanados1);
           
       }
       if(e.getSource()==objetoVista.btnMostrarAG)
       {
           List<Ganado> listaGanados2 = objetoDAO.obtenerGanadoPPL();
           mostrarGanadosEnTabla2(listaGanados2);
       }
    }
    
    
    
}
