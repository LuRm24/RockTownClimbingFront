 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Empleado;
import models.Recordatorio;
import utils.Utils;

public class AltaRecordatorio extends javax.swing.JFrame {

    private List<Recordatorio> recordatorios;
    /**
     * Creates new form AltaRecordatorio
     */
    public AltaRecordatorio() {
        initComponents();
        cargarEmpleados();
    }

    private void cargarEmpleados() {
        try {
            URL url = new URL("http://localhost:8080/empleado/select-all");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));

            // Aquí usamos el método que ya tienes hecho para cargar datos en la tabla
            Utils.cargarDatosComboEmpleado(br, comboEmpleado);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    
    private void cargarRecordatoriosTabla() {
        String[] columnas = {"Empleado", "Recordatorio"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        
        //Bucle que recorre la lista de recordatorios
        for (Recordatorio elemento: recordatorios) {
            String[] fila = {elemento.getEmpleado().getNombre() + " " + elemento.getEmpleado().getApellidos(), 
                            elemento.getTexto(), String.valueOf(elemento.getId())};
            model.addRow(fila);
        }
    
        tablaRecordatorios.setModel(model);
    }
    
    private void cargarRecordatorios() {
        try {
            //Recoger el id del empleado seleccionado en el combo
            Long idEmpleado = ((Empleado)comboEmpleado.getSelectedItem()).getId();

            URL url = new URL("http://localhost:8080/recordatorio/find-by-emp?empleadoId=" + idEmpleado);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line);
            }
            String json = jsonBuilder.toString();

            java.lang.reflect.Type listType = new com.google.gson.reflect.TypeToken<List<Recordatorio>>() {
            }.getType();
            recordatorios = new Gson().fromJson(json, listType);

            // Mostrar los recordatorios en la tabla
            cargarRecordatoriosTabla();

        } catch (Exception e) {
            e.printStackTrace();
            descripcion.setText("Error al cargar los recordatorios.");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonGuardar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        empleado = new javax.swing.JLabel();
        comboEmpleado = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaRecordatorios = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        recordatorio = new javax.swing.JLabel();
        eliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonGuardar.setFont(new java.awt.Font("Kohinoor Bangla", 0, 13)); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 120, 30));

        cancelar.setFont(new java.awt.Font("Kohinoor Bangla", 0, 13)); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 410, 120, 30));

        empleado.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        empleado.setText("Empleado");
        jPanel1.add(empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        comboEmpleado.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        comboEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEmpleadoActionPerformed(evt);
            }
        });
        jPanel1.add(comboEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 302, -1));

        tablaRecordatorios.setFont(new java.awt.Font("Kohinoor Bangla", 0, 13)); // NOI18N
        tablaRecordatorios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Empleado", "Recordatorio"
            }
        ));
        jScrollPane2.setViewportView(tablaRecordatorios);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 545, 140));

        jLabel2.setFont(new java.awt.Font("Didot", 1, 36)); // NOI18N
        jLabel2.setText("Recordatorios");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        descripcion.setColumns(20);
        descripcion.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        descripcion.setRows(5);
        jScrollPane1.setViewportView(descripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 420, 100));

        recordatorio.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        recordatorio.setText("Recordatorio");
        jPanel1.add(recordatorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, -1, -1));

        eliminar.setFont(new java.awt.Font("Kohinoor Bangla", 0, 13)); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 120, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 470));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoPrincipal.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setMaximumSize(new java.awt.Dimension(1854, 628));
        jLabel1.setMinimumSize(new java.awt.Dimension(1854, 628));
        jLabel1.setPreferredSize(new java.awt.Dimension(1854, 628));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (descripcion.getText().equals("")) {
             JOptionPane.showMessageDialog(this, "El texto del recordatorio no puede ser vacío");
        }
        else {
            Recordatorio nuevoRecordatorio = new Recordatorio();
            nuevoRecordatorio.setTexto(descripcion.getText());
            nuevoRecordatorio.setEmpleado(((Empleado)comboEmpleado.getSelectedItem()));

            //Serializar a JSON
            String json = new Gson().toJson(nuevoRecordatorio);

            try {
                // Crear conexión HTTP
                URL url = new URL("http://localhost:8080/recordatorio/insert");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setRequestProperty("Content-Type", "application/json");

                // Enviar datos al servidor
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = json.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int responseCode = con.getResponseCode();
                //Si la respuesta ha ido bien, indicamos que ha habido un error
                if (responseCode == HttpURLConnection.HTTP_OK) {
                   JOptionPane.showMessageDialog(this, "Recordatorio insertado correctamente");
                   cargarRecordatorios();
                }
                else {
                  JOptionPane.showMessageDialog(this, "Error al insertar el recordatorio");  
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
       Principal princ = new Principal();
       princ.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void comboEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEmpleadoActionPerformed
        cargarRecordatorios();
    }//GEN-LAST:event_comboEmpleadoActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JComboBox<Empleado> comboEmpleado;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel empleado;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel recordatorio;
    private javax.swing.JTable tablaRecordatorios;
    // End of variables declaration//GEN-END:variables
}
