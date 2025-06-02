/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Actividad;
import utils.LocalDateAdapter;
import utils.LocalTimeAdapter;

/**
 * Ventana de visualización y gestión de actividades registradas.
 *
 * Esta clase permite al usuario consultar, insertar, modificar o eliminar
 * actividades asociadas a la base de datos de la aplicación RockTown Climbing.
 * Las actividades se cargan desde el backend mediante peticiones HTTP y se
 * muestran en una tabla.
 *
 * También permite navegar hacia el formulario principal o abrir formularios de
 * edición o creación de actividades.
 *
 * @author Lucía Rodríguez Martín
 * @version 1.0
 */
public class VerActividades extends javax.swing.JFrame {

    /**
     * Creates new form GestionActividades
     */
    public VerActividades() {
        initComponents();
        cargarActividades();
    }

    /**
     * Carga todas las actividades desde el backend y las muestra en la tabla de
     * la interfaz.
     */
    private void cargarActividades() {
        try {
            URL url = new URL("http://localhost:8080/actividad/select-all");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));

            // Aquí usamos el método que ya tienes hecho para cargar datos en la tabla
            cargarDatosTabla(br);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Procesa la respuesta JSON del backend, construye el modelo de tabla y lo
     * asigna a la tabla de actividades.
     *
     * @param entrada BufferedReader con la respuesta JSON de la API
     */
    private void cargarDatosTabla(BufferedReader entrada) {
        try {
            //Definir las columnas de la tabla
            String[] columnas = {"Nombre", "Descripción", "Empleado", "Id"};
            DefaultTableModel model = new DefaultTableModel(columnas, 0);

            // Leer todo el contenido del BufferedReader
            String datosLeidos = "";
            String linea;
            while ((linea = entrada.readLine()) != null) {
                datosLeidos += linea;
            }
            //Si la entrada tiene datos
            if (datosLeidos.equals("Datos vacios") == false) {

                if (datosLeidos.startsWith("[")) {
                    // Parsear como JsonArray
                    JsonArray jsonArray = JsonParser.parseString(datosLeidos).getAsJsonArray();

                    //Recorrer el json e ir añadiendo las filas
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonObject obj = jsonArray.get(i).getAsJsonObject();

                        //Añadimos los datos de la fila en un array
                        String[] datos = {
                            obj.get("nombre").getAsString(),
                            obj.get("descripcion").getAsString(),
                            obj.getAsJsonObject("empleado").get("nombre").getAsString() + " "
                            + obj.getAsJsonObject("empleado").get("apellidos").getAsString(),
                            obj.get("id").getAsString()
                        };
                        //Al modelo le añadimos los datos como una fila
                        model.addRow(datos);
                    }
                } else {
                    JsonObject obj = JsonParser.parseString(datosLeidos).getAsJsonObject();

                    //Añadimos los datos de la fila en un array
                    String[] datos = {
                        obj.get("nombre").getAsString(),
                        obj.get("descripcion").getAsString(),
                        obj.getAsJsonObject("empleado").get("nombre").getAsString() + " "
                        + obj.getAsJsonObject("empleado").get("apellidos").getAsString(),
                        obj.get("id").getAsString()
                    };
                    model.addRow(datos);
                }
            }

            //A la tabla se le asigna el modelo
            tablaActividades.setModel(model);
        } catch (IOException io) {
            io.printStackTrace();
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
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaActividades = new javax.swing.JTable();
        verActividad = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        insertarActividad = new javax.swing.JButton();
        eliminarActividad1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Didot", 1, 36)); // NOI18N
        jLabel2.setText("Actividades");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        tablaActividades.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        tablaActividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Descripcion", "Empleado"
            }
        ));
        jScrollPane2.setViewportView(tablaActividades);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 630, 190));

        verActividad.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        verActividad.setText("Ver actividad");
        verActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verActividadActionPerformed(evt);
            }
        });
        jPanel1.add(verActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, -1, -1));

        cancelar.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        cancelar.setText("Menú Principal");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, -1, -1));

        insertarActividad.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        insertarActividad.setText("Añadir actividad");
        insertarActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarActividadActionPerformed(evt);
            }
        });
        jPanel1.add(insertarActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        eliminarActividad1.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        eliminarActividad1.setText("Eliminar actividad");
        eliminarActividad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActividad1ActionPerformed(evt);
            }
        });
        jPanel1.add(eliminarActividad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 660, 350));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoPrincipal.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Acción del botón "Ver actividad". Abre la ventana de modificación con los
     * datos de la actividad seleccionada.
     *
     * @param evt Evento de acción generado por el usuario
     */
    private void verActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verActividadActionPerformed

        // TODO add your handling code here:
        int fila = tablaActividades.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una actividad para modificar");
            return;
        }

        try {
            // Suponiendo que la columna 8 es el ID
            Long id = Long.parseLong(tablaActividades.getValueAt(fila, 3).toString());

            // Petición GET al backend para obtener los datos del empleado
            URL url = new URL("http://localhost:8080/actividad/find?id=" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }

                    // Crear Gson con soporte para LocalDate
                    Gson gson = new GsonBuilder()
                            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                            .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                            .create();

                    // Convertir JSON a objeto Empleado
                    Actividad actividad = gson.fromJson(response.toString(), Actividad.class);

                    // Abrir la ventana de modificación con los datos del empleado
                    GestionActividades act = new GestionActividades(false, actividad);
                    act.setVisible(true);
                    this.dispose();

                }
            } else {
                JOptionPane.showMessageDialog(this, "Error al obtener datos de la actividad. Código: " + responseCode);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en la operación: " + ex.getMessage());
        }
    }//GEN-LAST:event_verActividadActionPerformed

    /**
     * Acción del botón "Menú Principal". Regresa a la ventana principal de la
     * aplicación.
     *
     * @param evt Evento de acción generado por el usuario
     */
    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        Principal p = new Principal();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed
    /**
     * Acción del botón "Añadir actividad". Abre la ventana de inserción de una
     * nueva actividad.
     *
     * @param evt Evento de acción generado por el usuario
     */
    private void insertarActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarActividadActionPerformed
        // TODO add your handling code here:
        GestionActividades act = new GestionActividades(true, null);
        act.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_insertarActividadActionPerformed
    /**
     * Acción del botón "Eliminar actividad". Elimina la actividad seleccionada
     * tras confirmación del usuario.
     *
     * @param evt Evento de acción generado por el usuario
     */
    private void eliminarActividad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActividad1ActionPerformed
        int fila = tablaActividades.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccionar actividad a eliminar");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Deseas borrar la actividad?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            Long id = Long.parseLong((String) tablaActividades.getValueAt(fila, 3));
            URL url = new URL("http://localhost:8080/actividad/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.connect();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(this, "Actividad eliminada correctamente");
                cargarActividades();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la actividad. Código: " + responseCode);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en la operación: " + ex.getMessage());
        }
    }//GEN-LAST:event_eliminarActividad1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JButton eliminarActividad1;
    private javax.swing.JButton insertarActividad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaActividades;
    private javax.swing.JButton verActividad;
    // End of variables declaration//GEN-END:variables
}
