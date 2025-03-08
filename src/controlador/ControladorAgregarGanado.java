/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Ganado;
import modelo.GanadoDAO;
import modelo.Lotes;
import vista.AgregarGanado;

/**
 *
 * @author josed
 */
public class ControladorAgregarGanado implements ActionListener {
    
    Ganado vaca = new Ganado();
    GanadoDAO objetoDAO= new GanadoDAO();
    AgregarGanado objetoVista= new AgregarGanado();
    Lotes lote= new Lotes();
    
    public ControladorAgregarGanado(AgregarGanado vista, GanadoDAO dao)
    {
        objetoVista=vista;
        objetoDAO= dao;
        objetoVista.btnAgregar.addActionListener(this);
        objetoVista.btnMigrar.addActionListener(this);
        objetoVista.btnMostrarLotes.addActionListener(this);
    }
    
    public void mostrarLotesEnTabla(List<Lotes> listaGanados) {
    DefaultTableModel modeloT = new DefaultTableModel();
    
    //VERIFICAR BIEN EL NOMBRE DE LA TABLA A USAR
    objetoVista.tablaMostrarLotes.setModel(modeloT);
    
    // Agregar columnas a la tabla
    modeloT.addColumn("NOMBRE");
    modeloT.addColumn("TAMAÑO");
    modeloT.addColumn("ESTADO");
    
    

    // Recorrer la lista de autos ordenados y agregarlos a la tabla
    for (Lotes lote : listaGanados) {
        Object[] fila = new Object[3];
        fila[0] = lote.getNombre();
        fila[1] = lote.getTamaño();
        fila[2] = lote.getEstadoL();
        
        modeloT.addRow(fila);
    }

    // Actualizar la tabla
    objetoVista.tablaMostrarLotes.repaint();
    objetoVista.tablaMostrarLotes.revalidate();
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource()==objetoVista.btnAgregar)
       {
         String id = objetoVista.txtId.getText().trim();  
         String fN = objetoVista.txtFechaNacimiento.getText().trim(); 

         // Comprobar que la fecha no esté vacía antes de intentar parsearla
         if (fN.isEmpty()) {
             JOptionPane.showMessageDialog(null, "Por favor, ingresa una fecha válida.");
             return;  // Salir si no hay fecha
         }

         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         try {
             // Intentar convertir el texto a fecha
             Date fechaNacimiento = sdf.parse(fN);

             String natal = (String) objetoVista.jComboBox2.getSelectedItem();
             String estado = (String) objetoVista.jComboBox1.getSelectedItem();
             String lote = objetoVista.txtLote.getText().trim();  

             if (objetoDAO.validarLote(lote)) {
                 objetoDAO.insertarGanado(id, fechaNacimiento, natal, estado, lote);
             } else {
                 JOptionPane.showMessageDialog(null, "El Lote Ingresado No Se Encuentra Disponible");
             }

             objetoDAO.verificarVentaOLactea(id, natal,estado);
         } catch (ParseException ex) {
             // Manejar el error si la fecha no tiene el formato esperado
             JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Por favor ingresa una fecha en el formato: yyyy-MM-dd");
             ex.printStackTrace();
         } catch (Exception ex) {
             // Capturar cualquier otra excepción
             ex.printStackTrace();
         }

           
       }
       if (e.getSource()==objetoVista.btnMostrarLotes)
       {
           List<Lotes> listaAutos1 = objetoDAO.obtenerLotes();
           mostrarLotesEnTabla(listaAutos1);
       }
       
       //PARA MIGRAR DATOS
       /*
       if (e.getSource()==objetoVista.btnMigrar)
       {
           String rutaArchivo="lotes.txt";
           objetoDAO.migrarDesdeArchivoPlano(rutaArchivo);
           JOptionPane.showMessageDialog(null, "Archivo Migrado");
       }
*/
       
       
       
    }
    
}
