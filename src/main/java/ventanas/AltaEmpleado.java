/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import br.com.thiaguten.umbrella.support.security.BCryptPasswordEncoder;
import models.Empleado;
import com.google.gson.Gson;
import java.awt.Color;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import utils.Utils;

/**
 * Clase que representa la interfaz gráfica para dar de alta un nuevo empleado.
 *
 * Permite introducir todos los datos necesarios para registrar un nuevo
 * empleado, incluyendo validaciones de formato para campos como DNI, email y
 * contraseña. La contraseña se cifra con BCrypt antes de enviarse al backend.
 *
 * Funcionalidades principales:
 * <ul>
 * <li>Formulario de entrada de datos con validación.</li>
 * <li>Cifrado seguro de contraseña con BCrypt.</li>
 * <li>Envío de los datos al servidor mediante una petición HTTP POST.</li>
 * <li>Botones para añadir el empleado o cancelar la operación.</li>
 * </ul>
 *
 * Requiere:
 * <ul>
 * <li>El modelo {@link models.Empleado} con los métodos de acceso
 * definidos.</li>
 * <li>El backend debe exponer el endpoint
 * <code>http://localhost:8080/empleado/insert</code> para insertar nuevos
 * empleados.</li>
 * <li>Las funciones utilitarias {@link utils.Utils} para validación de DNI,
 * email y contraseña.</li>
 * </ul>
 *
 * @author Lucía Rodríguez Martín
 * @version 1.0
 */
public class AltaEmpleado extends javax.swing.JFrame {

    /**
     * Creates new form AltaCliente
     */
    public AltaEmpleado() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        dni = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        LbContrasena = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        apellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        agregar = new javax.swing.JButton();
        telefono = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        usuario = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        LbContrasena1 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        CBRol = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Didot", 1, 36)); // NOI18N
        jLabel2.setText("Alta Empleado");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        jLabel3.setText("DNI");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        dni.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 170, -1));

        jLabel4.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        jLabel4.setText("Nombre");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        LbContrasena.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        LbContrasena.setText("Contraseña");
        jPanel1.add(LbContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, -1, -1));

        direccion.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 300, -1));

        apellido.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 170, -1));

        jLabel6.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        jLabel6.setText("Apellido");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        jLabel7.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        jLabel7.setText("Dirección");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        jLabel8.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        jLabel8.setText("Rol");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, -1, -1));

        jLabel9.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        jLabel9.setText("Nombre usuario");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));

        jButton1.setFont(new java.awt.Font("Kohinoor Bangla", 0, 13)); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, -1, -1));

        agregar.setFont(new java.awt.Font("Kohinoor Bangla", 0, 13)); // NOI18N
        agregar.setText("Añadir");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        jPanel1.add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, -1, -1));

        telefono.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 170, -1));

        email.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 300, -1));

        usuario.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 190, -1));

        jLabel10.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        jLabel10.setText("Email");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, -1));

        password.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 190, -1));

        LbContrasena1.setFont(new java.awt.Font("Myanmar MN", 0, 13)); // NOI18N
        LbContrasena1.setText("Teléfono");
        jPanel1.add(LbContrasena1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));

        nombre.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 170, -1));

        CBRol.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        CBRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMINISTRADOR", "USUARIO" }));
        jPanel1.add(CBRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 190, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 253, 430, 10));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 480, 480));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoPrincipal.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(500, 600));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Acción que se ejecuta al pulsar el botón "Cancelar".
     *
     * Cierra la ventana actual y abre la ventana principal de empleados.
     *
     * @param evt el evento generado al hacer clic en el botón
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Empleados e = new Empleados();
        e.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * Acción que se ejecuta al pulsar el botón "Añadir".
     *
     * Valida los campos del formulario. Si todos los datos son válidos:
     * <ul>
     * <li>Se construye un objeto {@link models.Empleado} con los datos
     * introducidos.</li>
     * <li>Se cifra la contraseña utilizando
     * {@link br.com.thiaguten.umbrella.support.security.BCryptPasswordEncoder}.</li>
     * <li>Se convierte el objeto a JSON y se envía mediante una petición POST
     * al backend.</li>
     * <li>Se informa al usuario del resultado (éxito o error).</li>
     * </ul>
     *
     * Si se detectan errores de validación, se resaltan los campos incorrectos.
     *
     * @param evt el evento generado al hacer clic en el botón
     */
    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed

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

            if (direccion.getText().equals("")) {
                direccion.setBorder(bordeRojo);
                error++;
            } else {
                direccion.setBorder(bordeNormal);
            }

            if (usuario.getText().equals("")) {
                usuario.setBorder(bordeRojo);
                error++;
            } else {
                usuario.setBorder(bordeNormal);
            }

            if (email.getText().equals("") || !Utils.validarEmail(email.getText())) {
                email.setBorder(bordeRojo);
                error++;
            } else {
                email.setBorder(bordeNormal);
            }

            if (String.valueOf(password.getPassword()).equals("")) {
                password.setBorder(bordeRojo);
                error++;
            } else {
                if (String.valueOf(password.getPassword()).length() >= 6
                        && String.valueOf(password.getPassword()).length() <= 10
                        && Utils.validarContrasena(String.valueOf(password.getPassword()))) {
                    password.setBorder(bordeNormal);

                } else {
                    JOptionPane.showMessageDialog(this, "La contraseña debe tener entre 6 y 10 carácteres y contener carácteres especiales y mayúsculas");
                    error++;
                }
            }

            if (telefono.getText().equals("")) {
                telefono.setBorder(bordeRojo);
                error++;
            } else {
                telefono.setBorder(bordeNormal);
            }

            if (error == 0) {

                // Construir objeto Empleado
                Empleado nuevoEmpleado = new Empleado();
                nuevoEmpleado.setDni(dni.getText());
                nuevoEmpleado.setNombre(nombre.getText());
                nuevoEmpleado.setApellidos(apellido.getText());
                nuevoEmpleado.setDireccion(direccion.getText());
                nuevoEmpleado.setRol(String.valueOf(CBRol.getSelectedItem()));
                nuevoEmpleado.setNombreUsuario(usuario.getText());
                nuevoEmpleado.setEmail(email.getText());
                nuevoEmpleado.setTelefono(telefono.getText());
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String contrasena = String.valueOf(password.getPassword());

                // Codificar la contraseña
                String encodedPassword = passwordEncoder.encode(contrasena);
                // Guardarla en el empleado
                nuevoEmpleado.setContrasenaHash(encodedPassword);

                // Serializar a JSON
                String json = new Gson().toJson(nuevoEmpleado);

                // Crear conexión HTTP
                URL url = new URL("http://localhost:8080/empleado/insert");
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
                    JOptionPane.showMessageDialog(this, "Empleado insertado correctamente");
                    Empleados emp = new Empleados();
                    emp.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al insertar el empleado");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_agregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBRol;
    private javax.swing.JLabel LbContrasena;
    private javax.swing.JLabel LbContrasena1;
    private javax.swing.JButton agregar;
    private javax.swing.JTextField apellido;
    private javax.swing.JTextField direccion;
    private javax.swing.JTextField dni;
    private javax.swing.JTextField email;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nombre;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField telefono;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
