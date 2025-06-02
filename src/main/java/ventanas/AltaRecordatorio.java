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

/**
 * Clase que representa la ventana de gestión de recordatorios para empleados.
 *
 * Permite visualizar, crear y eliminar recordatorios asociados a empleados del
 * sistema. Carga los empleados desde el backend, muestra sus recordatorios y
 * permite gestionar los datos mediante una tabla y un formulario de texto.
 *
 * Funcionalidades principales:
 * <ul>
 * <li>Mostrar recordatorios existentes de un empleado seleccionado.</li>
 * <li>Crear nuevos recordatorios y asignarlos a empleados.</li>
 * <li>Eliminar recordatorios seleccionados.</li>
 * </ul>
 *
 * Requiere:
 * <ul>
 * <li>Modelo {@link models.Recordatorio} con relación al modelo
 * {@link models.Empleado}.</li>
 * <li>Backend REST que expone los endpoints:
 * <ul>
 * <li><code>GET /empleado/select-all</code></li>
 * <li><code>GET /recordatorio/find-by-emp?empleadoId=</code></li>
 * <li><code>POST /recordatorio/insert</code></li>
 * <li><code>DELETE /recordatorio/{id}</code></li>
 * </ul>
 * </li>
 * <li>Método utilitario {@link utils.Utils#cargarDatosComboEmpleado} para
 * rellenar el JComboBox.</li>
 * </ul>
 *
 * @author Lucía Rodríguez Martín
 * @version 1.0
 */
public class AltaRecordatorio extends javax.swing.JFrame {

    private List<Recordatorio> recordatorios;

    public AltaRecordatorio() {
        initComponents();
        cargarEmpleados();
    }

    /**
     * Carga la lista de empleados desde el backend y la muestra en el combo
     * desplegable.
     *
     * Realiza una petición HTTP GET a <code>/empleado/select-all</code> y
     * utiliza el método utilitario {@link utils.Utils#cargarDatosComboEmpleado}
     * para rellenar el JComboBox.
     */
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

    /**
     * Muestra en la tabla los recordatorios almacenados en la lista
     * {@code recordatorios}.
     *
     * Crea un modelo de tabla con columnas para nombre del empleado, texto del
     * recordatorio y su ID, y lo asigna a la tabla {@code tablaRecordatorios}.
     */
    private void cargarRecordatoriosTabla() {
        String[] columnas = {"Empleado", "Recordatorio", "Id"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        //Bucle que recorre la lista de recordatorios
        for (Recordatorio elemento : recordatorios) {
            String[] fila = {elemento.getEmpleado().getNombre() + " " + elemento.getEmpleado().getApellidos(),
                elemento.getTexto(), String.valueOf(elemento.getId())};
            model.addRow(fila);
        }

        tablaRecordatorios.setModel(model);
    }

    /**
     * Carga los recordatorios asociados al empleado seleccionado en el combo.
     *
     * Realiza una petición HTTP GET al endpoint
     * <code>/recordatorio/find-by-emp</code> y actualiza la lista interna y la
     * tabla visible con los datos obtenidos.
     */
    private void cargarRecordatorios() {
        try {
            //Recoger el id del empleado seleccionado en el combo
            Long idEmpleado = ((Empleado) comboEmpleado.getSelectedItem()).getId();

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
/**
     * Acción que se ejecuta al pulsar el botón "Guardar".
     *
     * Si el campo de texto del recordatorio no está vacío, se crea un nuevo
     * objeto {@link Recordatorio}, se asigna al empleado seleccionado, se
     * convierte a JSON y se envía mediante una petición POST al backend para su
     * inserción.
     *
     * Si la operación tiene éxito, se recargan los datos de la tabla. Si falla,
     * se muestra un mensaje de error.
     *
     * @param evt el evento generado al hacer clic en el botón
     */
    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if (descripcion.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "El texto del recordatorio no puede ser vacío");
        } else {
            Recordatorio nuevoRecordatorio = new Recordatorio();
            nuevoRecordatorio.setTexto(descripcion.getText());
            nuevoRecordatorio.setEmpleado(((Empleado) comboEmpleado.getSelectedItem()));

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
                } else {
                    JOptionPane.showMessageDialog(this, "Error al insertar el recordatorio");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed
    /**
     * Acción que se ejecuta al pulsar el botón "Cancelar".
     *
     * Cierra la ventana actual y abre la pantalla principal de la aplicación.
     *
     * @param evt el evento generado al hacer clic en el botón
     */
    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        Principal princ = new Principal();
        princ.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed
    /**
     * Acción que se ejecuta al seleccionar un empleado en el combo.
     *
     * Llama al método {@code cargarRecordatorios()} para recuperar los
     * recordatorios del empleado seleccionado y mostrarlos en la tabla.
     *
     * @param evt el evento generado al cambiar la selección del JComboBox
     */
    private void comboEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEmpleadoActionPerformed
        cargarRecordatorios();
    }//GEN-LAST:event_comboEmpleadoActionPerformed
    /**
     * Acción que se ejecuta al pulsar el botón "Eliminar".
     *
     * Elimina el recordatorio seleccionado en la tabla después de una
     * confirmación del usuario. Si la operación es exitosa, se recargan los
     * datos de la tabla.
     *
     * @param evt el evento generado al hacer clic en el botón
     */
    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        int fila = tablaRecordatorios.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccionar recordatorio a eliminar");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Deseas borrar el recordatorio?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            Long id = Long.parseLong((String) tablaRecordatorios.getModel().getValueAt(fila, 2));
            URL url = new URL("http://localhost:8080/recordatorio/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.connect();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(this, "Recordatorio eliminado correctamente");
                cargarRecordatorios();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el recordatorio. Código: " + responseCode);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en la operación: " + ex.getMessage());
        }
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
