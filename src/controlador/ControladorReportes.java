
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Ganado;
import modelo.GanadoDAO;
import modelo.Observador;
import modelo.ReportesDao;
import vista.AgregarGanado;
import vista.FrmReportes;


public class ControladorReportes implements ActionListener,Observador{

   
    FrmReportes objetoVista;
    ReportesDao objetoDao;

    public ControladorReportes(FrmReportes vista, ReportesDao dao) {
        objetoVista = vista;
        objetoDao = dao;

        // Registrar el controlador como observador del DAO
        objetoDao.agregarObservador(this);

        objetoVista.btnPDF.addActionListener(this);
    }

    // Método para mostrar animales en la tabla
    public void mostrarAnimalesEnTabla(List<Ganado> listaGanados) {
        DefaultTableModel modeloT = new DefaultTableModel();
        objetoVista.TablaPdf.setModel(modeloT);

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

        objetoVista.TablaPdf.repaint();
        objetoVista.TablaPdf.revalidate();
    }

    // Método de la interfaz Observador
    @Override
    public void actualizar() {
        List<Ganado> listaganado = objetoDao.obtenerGanado();
        mostrarAnimalesEnTabla(listaganado);
    }

    // Método de la interfaz ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == objetoVista.btnPDF) {
            List<Ganado> listaganado = objetoDao.obtenerGanado();
            mostrarAnimalesEnTabla(listaganado);
        }
    }
}
