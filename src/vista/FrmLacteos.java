
package vista;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
public class FrmLacteos extends javax.swing.JFrame {

   
    public FrmLacteos() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private JTable tb2Employee;

    public FrmLacteos(JTable tb2Employee) {
        this.tb2Employee = tb2Employee;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnPDF1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPdf1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPDF1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        btnPDF1.setText("Imprimir Reporte ");
        btnPDF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDF1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnPDF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 155, -1, 56));

        TablaPdf1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaPdf1.setToolTipText("");
        jScrollPane1.setViewportView(TablaPdf1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 107, 540, 280));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Produccion Lactea");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 40, 270, 62));

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel1.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 223, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lecheFD.jpg"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 410));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPDF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDF1ActionPerformed

     // Validar que la tabla exista y tenga datos
        if (TablaPdf1 == null || TablaPdf1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Error: No hay datos en la tabla para exportar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Configurar el JFileChooser para seleccionar directorios
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Mostrar el diálogo para seleccionar la ruta de guardado
        int selection = fileChooser.showSaveDialog(this);
        if (selection != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(this, "No se seleccionó ninguna ruta.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener la ruta del directorio seleccionado
        String directoryPath = fileChooser.getSelectedFile().getAbsolutePath();
        String filePath = directoryPath + File.separator + "ReporteLacteos.pdf";

        // Crear el documento PDF
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Crear una tabla con el número de columnas de la JTable
            int columnCount = TablaPdf1.getColumnCount();
            PdfPTable table = new PdfPTable(columnCount);
            table.setWidthPercentage(100); // Ajustar ancho de la tabla al 100%

            // Agregar encabezados de la tabla
            for (int i = 0; i < columnCount; i++) {
                table.addCell(TablaPdf1.getColumnName(i));
            }

            // Recorrer las filas de la tabla y agregar datos al PDF
            for (int i = 0; i < TablaPdf1.getRowCount(); i++) {
                for (int j = 0; j < columnCount; j++) {
                    Object value = TablaPdf1.getValueAt(i, j);
                    table.addCell(value != null ? value.toString() : ""); // Evita valores nulos
                }
            }

            // Agregar la tabla al documento
            document.add(table);
            JOptionPane.showMessageDialog(this, "Reporte descargado exitosamente en:\n" + filePath, "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error: No se pudo crear el archivo PDF.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DocumentException e) {
            JOptionPane.showMessageDialog(this, "Error: No se pudo generar el PDF.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar el documento si está abierto
            if (document.isOpen()) {
                document.close();
            }
        }



    }//GEN-LAST:event_btnPDF1ActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        MenuPrincipal menu=new MenuPrincipal();
        menu.setVisible(true);
        
    }//GEN-LAST:event_btnVolverActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmLacteos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLacteos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLacteos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLacteos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLacteos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable TablaPdf1;
    public javax.swing.JButton btnPDF1;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
