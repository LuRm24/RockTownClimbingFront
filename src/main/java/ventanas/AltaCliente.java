/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Color;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import models.Cliente;
import models.TipoEntrada;
import utils.LocalDateAdapter;
import utils.Utils;

/**
 * Clase que representa la ventana de alta de nuevos clientes en el sistema.
 *
 * Esta interfaz gráfica permite al usuario introducir y validar los datos de un
 * cliente, incluyendo su DNI, nombre, apellidos, teléfono, fecha del bono, tipo
 * de entrada, si necesita pies de gato y su edad. Una vez validados, los datos
 * se envían al servidor mediante una petición HTTP POST para su inserción en la
 * base de datos.
 *
 * Funcionalidades principales:
 * <ul>
 * <li>Formulario de entrada de datos con validación de campos
 * obligatorios.</li>
 * <li>Selección del tipo de bono desde una lista cargada dinámicamente desde el
 * backend.</li>
 * <li>Botón "Añadir" que realiza la inserción del cliente si los datos son
 * correctos.</li>
 * <li>Botón "Cancelar" que cierra la ventana actual y vuelve a la vista general
 * de clientes.</li>
 * </ul>
 *
 * Tecnologías utilizadas:
 * <ul>
 * <li>Java Swing para la interfaz gráfica.</li>
 * <li>Gson para la serialización de objetos a JSON.</li>
 * <li>HttpURLConnection para la comunicación con el servidor.</li>
 * </ul>
 *
 * Requiere:
 * <ul>
 * <li>El modelo {@link models.Cliente} con sus setters y getters correctamente
 * definidos.</li>
 * <li>El modelo {@link models.TipoEntrada} para el JComboBox.</li>
 * <li>La clase utilitaria {@link utils.Utils} para validación de DNI y carga de
 * tipos de entrada.</li>
 * <li>Un endpoint disponible en
 * <code>http://localhost:8080/cliente/insert</code> que acepte peticiones POST
 * con datos de cliente en formato JSON.</li>
 * </ul>
 *
 * @author Lucía Rodríguez Martín
 * @version 1.0
 */
public class AltaCliente extends javax.swing.JFrame {

    public AltaCliente() {
        initComponents();
        Utils.cargarTiposEntrada(tipoBono);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        dni = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        apellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        tipoBono = new javax.swing.JComboBox<>();
        pieGato = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        cancelar = new javax.swing.JButton();
        insertarCliente = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        edad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Didot", 1, 36)); // NOI18N
        jLabel2.setText("Alta de Cliente");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(jLabel3.getFont());
        jLabel3.setText("DNI");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        dni.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 170, -1));

        jLabel4.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        jLabel4.setText("Nombre");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        nombre.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 170, -1));

        jLabel5.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        jLabel5.setText("Pies de gato");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        telefono.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 170, -1));

        apellido.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 320, -1));

        jLabel6.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        jLabel6.setText("Apellido");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel7.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        jLabel7.setText("Teléfono");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));
        jPanel1.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 170, 32));

        jLabel8.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        jLabel8.setText("Fecha");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        tipoBono.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(tipoBono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 320, -1));
        jPanel1.add(pieGato, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, -1, -1));

        jLabel9.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        jLabel9.setText("Tipo de bono");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        cancelar.setFont(new java.awt.Font("Kohinoor Bangla", 0, 13)); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, -1, -1));

        insertarCliente.setFont(new java.awt.Font("Kohinoor Bangla", 0, 13)); // NOI18N
        insertarCliente.setText("Añadir");
        insertarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(insertarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, -1, -1));

        jLabel10.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        jLabel10.setText("Edad");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, -1, -1));

        edad.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 80, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 500, 370));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoPrincipal.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(500, 600));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Acción ejecutada al pulsar el botón "Cancelar".
     *
     * Cierra la ventana actual y vuelve a la ventana principal de gestión de
     * clientes.
     *
     * @param evt el evento de acción generado por el botón
     */
    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        Clientes c = new Clientes();
        c.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed
    /**
     * Acción ejecutada al pulsar el botón "Añadir".
     *
     * Valida los campos del formulario. Si todos son correctos, crea un objeto
     * {@link models.Cliente}, lo convierte a JSON y lo envía mediante una
     * petición POST al backend para insertarlo en la base de datos. Si la
     * operación es exitosa, muestra un mensaje de éxito y redirige a la ventana
     * principal. Si hay errores de validación o de conexión, muestra un mensaje
     * de error.
     *
     * @param evt el evento de acción generado por el botón
     */
    private void insertarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarClienteActionPerformed
        // TODO add your handling code here:
        try {
            int error = 0;
            Border bordeRojo = BorderFactory.createLineBorder(Color.red, 1);
            Border bordeNormal = BorderFactory.createLineBorder(Color.gray, 1);
            //Comprobar los datos que sean correctos
            if (dni.getText().equals("") || !Utils.validarDNI(dni.getText())) {
                dni.setBorder(bordeRojo);
                error++;
            } else {
                dni.setBorder(bordeNormal);
            }

            if (nombre.getText().equals("")) {
                nombre.setBorder(bordeRojo);
                error++;
            } else {
                nombre.setBorder(bordeNormal);
            }

            if (apellido.getText().equals("")) {
                apellido.setBorder(bordeRojo);
                error++;
            } else {
                apellido.setBorder(bordeNormal);
            }

            if (telefono.getText().equals("")) {
                telefono.setBorder(bordeRojo);
                error++;
            } else {
                telefono.setBorder(bordeNormal);
            }

            if (fecha.getDate() == null) {
                fecha.setBorder(bordeRojo);
                error++;
            } else {
                fecha.setBorder(bordeNormal);
            }

            if (edad.getText().equals("")) {
                edad.setBorder(bordeRojo);
                error++;
            } else {
                edad.setBorder(bordeNormal);
            }

            if (error == 0) {
                // Construir objeto Cliente
                Cliente nuevoCliente = new Cliente();
                nuevoCliente.setDni(dni.getText());
                nuevoCliente.setNombre(nombre.getText());
                nuevoCliente.setApellidos(apellido.getText());
                nuevoCliente.setTelefono(telefono.getText());
                nuevoCliente.setTipo_entrada((TipoEntrada) tipoBono.getSelectedItem());
                // Fecha
                if (fecha.getDate() != null) {
                    nuevoCliente.setFechaBono(fecha.getDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate());
                }

                // Pie de gato (checkbox)
                nuevoCliente.setPieGato(pieGato.isSelected());

                // Edad
                nuevoCliente.setEdad(Integer.parseInt(edad.getText().trim()));

                // Crear Gson con soporte para LocalDate
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                        .create();

                // Serializar a JSON
                String json = gson.toJson(nuevoCliente);

                // Crear conexión HTTP
                URL url = new URL("http://localhost:8080/cliente/insert");
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
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    JOptionPane.showMessageDialog(this, "Cliente insertado correctamente");
                    Clientes client = new Clientes();
                    client.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al insertar el cliente");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_insertarClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellido;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField dni;
    private javax.swing.JTextField edad;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JButton insertarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombre;
    private javax.swing.JCheckBox pieGato;
    private javax.swing.JTextField telefono;
    private javax.swing.JComboBox<TipoEntrada> tipoBono;
    // End of variables declaration//GEN-END:variables
}
