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
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import models.Cliente;
import utils.LocalDateAdapter;

/**
 * Clase Clientes que representa la interfaz gráfica para la gestión de clientes
 * del sistema. Permite visualizar, buscar, modificar, eliminar y registrar
 * clientes, accediendo a los datos desde un backend a través de peticiones
 * HTTP.
 */
public class Clientes extends javax.swing.JFrame {

    /**
     * Creates new form Clientes
     */
    public Clientes() {
        initComponents();
        cargarClientes();
    }

    /**
     * Carga los datos de clientes desde un BufferedReader y los inserta en la
     * tabla de la interfaz.
     *
     * @param entrada BufferedReader con el contenido JSON de los clientes.
     */
    private void cargarDatosTabla(BufferedReader entrada) {
        try {
            //Definir las columnas de la tabla
            String[] columnas = {"Nombre", "Apellidos", "Telefono", "DNI", "Fecha Bono", "Pies de Gato", "Edad", "Tipo entrada", "Id"};
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
                            obj.get("apellidos").getAsString(),
                            obj.get("telefono").getAsString(),
                            obj.get("dni").getAsString(),
                            obj.get("fechaBono").getAsString(),
                            obj.get("pieGato").getAsBoolean() ? "Si" : "No",
                            obj.get("edad").getAsString(),
                            obj.getAsJsonObject("tipo_entrada").get("descripcion").getAsString(),
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
                        obj.get("apellidos").getAsString(),
                        obj.get("telefono").getAsString(),
                        obj.get("dni").getAsString(),
                        obj.get("fechaBono").getAsString(),
                        obj.get("pieGato").getAsBoolean() ? "Si" : "No",
                        obj.get("edad").getAsString(),
                        obj.getAsJsonObject("tipo_entrada").get("descripcion").getAsString(),
                        obj.get("id").getAsString()
                    };
                    model.addRow(datos);
                }
            }

            //A la tabla se le asigna el modelo
            tablaClientes.setModel(model);
            TableColumnModel columnModel = tablaClientes.getColumnModel();
            if (columnModel.getColumnCount() > 8) {
                TableColumn columnaId = columnModel.getColumn(8);
                columnModel.removeColumn(columnaId);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Realiza una petición HTTP GET para obtener todos los clientes del backend
     * y cargarlos en la tabla.
     */
    private void cargarClientes() {
        try {
            URL url = new URL("http://localhost:8080/cliente/select-all");
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
     * Limpia los campos de búsqueda de la interfaz.
     */
    private void limpiarBusqueda() {
        tdni.setText("");
        apellido.setText("");
        Ttelefono.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButtonVer = new javax.swing.JButton();
        buscar = new javax.swing.JButton();
        jButtonAlta = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        Ttelefono = new javax.swing.JTextField();
        tdni = new javax.swing.JTextField();
        eliminar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButtonRecargar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaClientes.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Telefono", "DNI", "Fecha bono", "Sesiones gastadas", "Pies de gato", "Menor de edad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaClientes.setOpaque(false);
        jScrollPane1.setViewportView(tablaClientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 870, 190));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonVer.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jButtonVer.setText("Ver Cliente");
        jButtonVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, -1, -1));

        buscar.setFont(new java.awt.Font("Kohinoor Bangla", 0, 13)); // NOI18N
        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        jPanel1.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, -1, -1));

        jButtonAlta.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jButtonAlta.setText("Alta Cliente");
        jButtonAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAltaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAlta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel2.setText("Teléfono");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 60, 20));

        jLabel3.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel3.setText("DNI");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel4.setText("Apellido");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, -1, -1));

        apellido.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        apellido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                apellidoMouseClicked(evt);
            }
        });
        jPanel1.add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 170, 20));

        Ttelefono.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        Ttelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TtelefonoMouseClicked(evt);
            }
        });
        jPanel1.add(Ttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 170, 20));

        tdni.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        tdni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tdniMouseClicked(evt);
            }
        });
        jPanel1.add(tdni, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 170, 20));

        eliminar.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        eliminar.setText("Baja Cliente");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        modificar.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        modificar.setText("Modificar Cliente");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        jPanel1.add(modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        jButton1.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jButton1.setText("Menú principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        jButtonRecargar.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jButtonRecargar.setText("<--");
        jButtonRecargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecargarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonRecargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 70, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 870, 140));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoPrincipal.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(500, 600));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Acción al pulsar el botón "Ver Cliente". Obtiene el cliente seleccionado
     * y abre la ventana VerCliente.
     */
    private void jButtonVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerActionPerformed
        //Comprobar si el usuario ha seleccionado algun cliente
        int fila = tablaClientes.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para visualizar", "Selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Suponiendo que la columna 8 es el ID
            Long id = Long.parseLong(tablaClientes.getModel().getValueAt(fila, 8).toString());

            // Petición GET al backend para obtener los datos del empleado
            URL url = new URL("http://localhost:8080/cliente/find?id=" + id);
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
                            .create();

                    // Convertir JSON a objeto Empleado
                    Cliente cliente = gson.fromJson(response.toString(), Cliente.class);

                    // Abrir la ventana de modificación con los datos del empleado
                    VerCliente ver = new VerCliente(cliente);
                    ver.setVisible(true);
                    this.dispose(); // Cierra la ventana actual (opcional)

                }
            } else {
                JOptionPane.showMessageDialog(this, "Error al obtener datos del empleado. Código: " + responseCode);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en la operación: " + ex.getMessage());
        }

    }//GEN-LAST:event_jButtonVerActionPerformed
    /**
     * Acción al pulsar el botón "Buscar". Construye una URL con los parámetros
     * de búsqueda y actualiza la tabla.
     */
    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
        try {
            String dni = tdni.getText().trim();
            String apellidos = apellido.getText().trim();
            String usuario = Ttelefono.getText().trim();

            StringBuilder queryBuilder = new StringBuilder("http://localhost:8080/cliente/find-client?");
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
                queryBuilder.append("telefono=").append(URLEncoder.encode(usuario, "UTF-8"));
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
                        JOptionPane.showMessageDialog(this, "No se encontraron clientes.");
                        tablaClientes.setModel(new DefaultTableModel(new String[]{"Nombre", "Apellidos", "Telefono", "DNI", "Fecha Bono", "Sesiones Gastadas", "Pies de Gato", "Menor de edad", "Id"}, 0));
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

    }//GEN-LAST:event_buscarActionPerformed
    /**
     * Acción al pulsar el botón "Baja Cliente". Elimina el cliente seleccionado
     * en la tabla.
     */
    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
        int fila = tablaClientes.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccionar cliente a eliminar");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Deseas borrar el cliente?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            Long id = Long.parseLong((String) tablaClientes.getModel().getValueAt(fila, 8));
            URL url = new URL("http://localhost:8080/cliente/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.connect();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente");
                cargarClientes();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el empleado. Código: " + responseCode);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en la operación: " + ex.getMessage());
        }

    }//GEN-LAST:event_eliminarActionPerformed
    /**
     * Acción al pulsar el botón "Modificar Cliente". Obtiene los datos del
     * cliente seleccionado y abre la ventana ModificarCliente.
     */
    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        // TODO add your handling code here:
        int fila = tablaClientes.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente para modificar");
            return;
        }

        try {
            // Suponiendo que la columna 8 es el ID
            Long id = Long.parseLong(tablaClientes.getModel().getValueAt(fila, 8).toString());

            // Petición GET al backend para obtener los datos del empleado
            URL url = new URL("http://localhost:8080/cliente/find?id=" + id);
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
                            .create();

                    // Convertir JSON a objeto Empleado
                    Cliente cliente = gson.fromJson(response.toString(), Cliente.class);

                    // Abrir la ventana de modificación con los datos del empleado
                    ModificarCliente me = new ModificarCliente(cliente);
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


    }//GEN-LAST:event_modificarActionPerformed
    /**
     * Acción al pulsar el botón "Alta Cliente". Abre la ventana AltaCliente.
     */
    private void jButtonAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAltaActionPerformed
        // TODO add your handling code here:
        AltaCliente ac = new AltaCliente();
        ac.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonAltaActionPerformed
    /**
     * Acción al pulsar el botón "Menú principal". Vuelve a la ventana
     * principal.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Principal p = new Principal();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Acción al pulsar el botón "<--". Recarga la lista de clientes.
     */
    private void jButtonRecargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecargarActionPerformed
        // TODO add your handling code here:
        cargarClientes();
    }//GEN-LAST:event_jButtonRecargarActionPerformed
    /**
     * Al hacer clic sobre el campo DNI, activa solo ese campo y limpia el
     * resto.
     */
    private void tdniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tdniMouseClicked
        // TODO add your handling code here:
        tdni.setEnabled(true);
        apellido.setEnabled(false);
        Ttelefono.setEnabled(false);
        limpiarBusqueda();
    }//GEN-LAST:event_tdniMouseClicked
    /**
     * Al hacer clic sobre el campo Apellido, activa solo ese campo y limpia el
     * resto.
     */
    private void apellidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_apellidoMouseClicked
        // TODO add your handling code here:
        tdni.setEnabled(false);
        apellido.setEnabled(true);
        Ttelefono.setEnabled(false);
        limpiarBusqueda();
    }//GEN-LAST:event_apellidoMouseClicked
    /**
     * Al hacer clic sobre el campo Teléfono, activa solo ese campo y limpia el
     * resto.
     */
    private void TtelefonoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TtelefonoMouseClicked
        // TODO add your handling code here:
        tdni.setEnabled(false);
        apellido.setEnabled(false);
        Ttelefono.setEnabled(true);
        limpiarBusqueda();
    }//GEN-LAST:event_TtelefonoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Ttelefono;
    private javax.swing.JTextField apellido;
    private javax.swing.JButton buscar;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAlta;
    private javax.swing.JButton jButtonRecargar;
    private javax.swing.JButton jButtonVer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificar;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField tdni;
    // End of variables declaration//GEN-END:variables
}
