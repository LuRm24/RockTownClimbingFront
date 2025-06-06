/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import models.Empleado;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que representa la ventana de gestión de empleados. Permite listar,
 * buscar, modificar, eliminar y registrar empleados mediante comunicación con
 * el backend vía HTTP.
 *
 * Esta interfaz está diseñada para facilitar la administración del personal
 * dentro del sistema de gestión del rocódromo.
 *
 * @author Lucia Rodríguez Martín
 * @version 1.0
 */
public class Empleados extends javax.swing.JFrame {

    public Empleados() {
        initComponents();
        cargarEmpleados();
    }

    /**
     * Carga los datos de empleados desde un BufferedReader y los inserta en la
     * tabla.
     *
     * @param entrada BufferedReader con los datos en formato JSON (array o
     * objeto).
     */
    private void cargarDatosTabla(BufferedReader entrada) {
        try {
            //Definir las columnas de la tabla
            String[] columnas = {"Nombre", "Apellidos", "Direccion", "DNI", "Email", "Usuario", "Rol", "Teléfono", "Id"};
            DefaultTableModel model = new DefaultTableModel(columnas, 0);
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
                        String[] datos = {obj.get("nombre").getAsString(), obj.get("apellidos").getAsString(),
                            obj.get("direccion").getAsString(), obj.get("dni").getAsString(),
                            obj.get("email").getAsString(), obj.get("nombreUsuario").getAsString(), obj.get("rol").getAsString(),
                            obj.get("telefono").getAsString(), obj.get("id").getAsString()};
                        //Al modelo le añadimos los datos como una fila
                        model.addRow(datos);
                    }
                } else {
                    JsonObject obj = JsonParser.parseString(datosLeidos).getAsJsonObject();

                    //Añadimos los datos de la fila en un array
                    String[] datos = {obj.get("nombre").getAsString(), obj.get("apellidos").getAsString(),
                        obj.get("direccion").getAsString(), obj.get("dni").getAsString(),
                        obj.get("email").getAsString(), obj.get("nombreUsuario").getAsString(), obj.get("rol").getAsString(),
                        obj.get("telefono").getAsString(), obj.get("id").getAsString()};
                    //Al modelo le añadimos los datos como una fila
                    model.addRow(datos);
                }
            }

            //A la tabla se le asigna el modelo
            tablaEmpleados.setModel(model);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Realiza una petición HTTP GET al backend para obtener todos los empleados
     * y mostrarlos en la tabla de la interfaz.
     */
    private void cargarEmpleados() {
        try {
            URL url = new URL("http://localhost:8080/empleado/select-all");
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
     * Limpia los campos de búsqueda (DNI, apellidos y nombre de usuario).
     * También deshabilita los campos que no estén activos.
     */
    private void limpiarBusqueda() {
        TFDNI.setText("");
        TFApellidos.setText("");
        TFUsuario.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButtonBuscar = new javax.swing.JButton();
        jButtonAlta = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TFApellidos = new javax.swing.JTextField();
        TFUsuario = new javax.swing.JTextField();
        TFDNI = new javax.swing.JTextField();
        jButtonEliminar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButtonRecargar = new javax.swing.JButton();
        jButtonMenuPrinc = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaEmpleados.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Rol", "DNI", "Dirección", "Email", "Nombre Usuario"
            }
        ));
        tablaEmpleados.setOpaque(false);
        jScrollPane1.setViewportView(tablaEmpleados);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 770, 230));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonBuscar.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, -1, -1));

        jButtonAlta.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jButtonAlta.setText("Nuevo empleado");
        jButtonAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAltaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAlta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel2.setText("Nombre Usuario");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel3.setText("DNI");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel4.setText("Apellido");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, -1, -1));

        TFApellidos.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        TFApellidos.setEnabled(false);
        TFApellidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TFApellidosMouseClicked(evt);
            }
        });
        jPanel1.add(TFApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, 170, 20));

        TFUsuario.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        TFUsuario.setEnabled(false);
        TFUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TFUsuarioMouseClicked(evt);
            }
        });
        jPanel1.add(TFUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 170, 20));

        TFDNI.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        TFDNI.setEnabled(false);
        TFDNI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TFDNIMouseClicked(evt);
            }
        });
        jPanel1.add(TFDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 170, 20));

        jButtonEliminar.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jButtonEliminar.setText("Eliminar empleado");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jButtonModificar.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jButtonModificar.setText("Modificar Empleado");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        jButtonRecargar.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jButtonRecargar.setText("<--");
        jButtonRecargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecargarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonRecargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, 70, -1));

        jButtonMenuPrinc.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jButtonMenuPrinc.setText("Menú Principal");
        jButtonMenuPrinc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMenuPrincActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonMenuPrinc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 770, 130));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoPrincipal.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(500, 600));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * Realiza la búsqueda de empleados en función de los filtros introducidos
     * (DNI, apellidos o nombre de usuario). Muestra los resultados en la tabla.
     * Si no encuentra resultados, muestra un mensaje de advertencia.
     *
     * @param evt Evento de acción generado al pulsar el botón "Buscar".
     */
    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        // TODO add your handling code here:
        try {
            String dni = TFDNI.getText().trim();
            String apellidos = TFApellidos.getText().trim();
            String usuario = TFUsuario.getText().trim();

            StringBuilder queryBuilder = new StringBuilder("http://localhost:8080/empleado/find-employee?");
            boolean hasParams = false;

            if (!dni.isEmpty()) {
                queryBuilder.append("dni=").append(URLEncoder.encode(dni, "UTF-8"));
                hasParams = true;
            }

            if (!apellidos.isEmpty()) {
                if (hasParams) {
                    queryBuilder.append("&");
                }
                queryBuilder.append("apellidos=").append(URLEncoder.encode(apellidos, "UTF-8"));
                hasParams = true;
            }

            if (!usuario.isEmpty()) {
                if (hasParams) {
                    queryBuilder.append("&");
                }
                queryBuilder.append("nombreUsuario=").append(URLEncoder.encode(usuario, "UTF-8"));
            }

            URL url = new URL(queryBuilder.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String linea;
                    StringBuilder datosLeidos = new StringBuilder();
                    while ((linea = in.readLine()) != null) {
                        datosLeidos.append(linea);
                    }

                    String json = datosLeidos.toString();
                    if (json.equals("[]")) {
                        JOptionPane.showMessageDialog(this, "No se encontraron empleados.");
                        tablaEmpleados.setModel(new DefaultTableModel(new String[]{"Nombre", "Apellidos", "Direccion", "DNI", "Email", "Usuario", "Rol", "Teléfono", "Id"}, 0));
                    } else {
                        cargarDatosTabla(new BufferedReader(new StringReader(json)));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error al obtener los datos");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en la búsqueda: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed
    /**
     * Elimina el empleado seleccionado en la tabla tras confirmación del
     * usuario. Envía una petición DELETE al backend con el ID correspondiente.
     *
     * @param evt Evento de acción generado al pulsar el botón "Eliminar".
     */
    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        int fila = tablaEmpleados.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccionar empleado a eliminar");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Deseas borrar el empleado?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            Long id = Long.parseLong((String) tablaEmpleados.getValueAt(fila, 8));
            URL url = new URL("http://localhost:8080/empleado/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.connect();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente");
                cargarEmpleados();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el empleado. Código: " + responseCode);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en la operación: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed
    /**
     * Abre la ventana de modificación de un empleado. Obtiene los datos
     * completos del empleado seleccionado a través del backend y los pasa a la
     * ventana ModificarEmpleado.
     *
     * @param evt Evento de acción generado al pulsar el botón "Modificar".
     */
    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        // TODO add your handling code here:
        int fila = tablaEmpleados.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un empleado para modificar");
            return;
        }

        try {
            // Suponiendo que la columna 8 es el ID
            Long id = Long.parseLong(tablaEmpleados.getValueAt(fila, 8).toString());

            // Petición GET al backend para obtener los datos del empleado
            URL url = new URL("http://localhost:8080/empleado/find?id=" + id);
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

                    // Convertir JSON a objeto Empleado
                    Empleado empleado = new Gson().fromJson(response.toString(), Empleado.class);

                    // Abrir la ventana de modificación con los datos del empleado
                    ModificarEmpleado me = new ModificarEmpleado(empleado);
                    me.setVisible(true);
                    this.dispose(); // Cierra la ventana actual (opcional)

                }
            } else {
                JOptionPane.showMessageDialog(this, "Error al obtener datos del empleado. Código: " + responseCode);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en la operación: " + ex.getMessage());
        }

    }//GEN-LAST:event_jButtonModificarActionPerformed
    /**
     * Abre la ventana de alta de nuevo empleado y cierra la ventana actual.
     *
     * @param evt Evento de acción generado al pulsar el botón "Nuevo empleado".
     */
    private void jButtonAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAltaActionPerformed
        AltaEmpleado altaEmp = new AltaEmpleado();
        altaEmp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonAltaActionPerformed
    /**
     * Activa solo el campo DNI y desactiva el resto al hacer clic en el campo.
     *
     * @param evt Evento de clic del ratón sobre el campo TFDNI.
     */
    private void TFDNIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TFDNIMouseClicked
        // TODO add your handling code here:
        TFDNI.setEnabled(true);
        TFApellidos.setEnabled(false);
        TFUsuario.setEnabled(false);
        limpiarBusqueda();
    }//GEN-LAST:event_TFDNIMouseClicked
    /**
     * Activa solo el campo Apellidos y desactiva el resto al hacer clic en el
     * campo.
     *
     * @param evt Evento de clic del ratón sobre el campo TFApellidos.
     */
    private void TFApellidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TFApellidosMouseClicked
        // TODO add your handling code here:
        TFDNI.setEnabled(false);
        TFApellidos.setEnabled(true);
        TFUsuario.setEnabled(false);
        limpiarBusqueda();
    }//GEN-LAST:event_TFApellidosMouseClicked

    /**
     * Recarga todos los empleados desde el backend, restaurando la tabla.
     *
     * @param evt Evento de acción generado al pulsar el botón de recarga "<--".
     */
    private void jButtonRecargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecargarActionPerformed
        // TODO add your handling code here:
        cargarEmpleados();
    }//GEN-LAST:event_jButtonRecargarActionPerformed
    /**
     * Navega al menú principal y cierra la ventana actual.
     *
     * @param evt Evento de acción generado al pulsar el botón "Menú Principal".
     */
    private void jButtonMenuPrincActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuPrincActionPerformed
        // TODO add your handling code here:
        Principal p = new Principal();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonMenuPrincActionPerformed

    /**
     * Activa solo el campo Usuario y desactiva el resto al hacer clic en el
     * campo.
     *
     * @param evt Evento de clic del ratón sobre el campo TFUsuario.
     */
    private void TFUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TFUsuarioMouseClicked
        // TODO add your handling code here:
        TFDNI.setEnabled(false);
        TFApellidos.setEnabled(false);
        TFUsuario.setEnabled(true);
        limpiarBusqueda();

    }//GEN-LAST:event_TFUsuarioMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TFApellidos;
    private javax.swing.JTextField TFDNI;
    private javax.swing.JTextField TFUsuario;
    private javax.swing.JButton jButtonAlta;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonMenuPrinc;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonRecargar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaEmpleados;
    // End of variables declaration//GEN-END:variables
}
