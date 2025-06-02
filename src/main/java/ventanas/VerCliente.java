/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JTextField;
import models.Cliente;
import models.TipoEntrada;
import utils.Utils;

/**
 * Ventana de visualización de los datos de un cliente registrado.
 *
 * Esta clase permite al usuario consultar todos los datos de un cliente
 * previamente seleccionado desde la vista general de clientes, como su nombre,
 * apellidos, DNI, edad, teléfono, fecha de alta del bono, tipo de bono, si
 * utiliza pies de gato, etc.
 *
 * Todos los campos son de solo lectura, y la vista es meramente informativa.
 *
 * @author Lucía Rodríguez Martín
 * @version 1.0
 */
public class VerCliente extends javax.swing.JFrame {

    private Cliente cliente = null;

    public VerCliente(Cliente cliente) {
        initComponents();
        //Le pasamos el cliente de la ventana de Clientes
        this.cliente = cliente;
        Utils.cargarTiposEntrada(tipoBono);
        cargarDatosCliente();
        cambiarColorTexto();
    }

    /**
     * Cambia los colores de texto de los campos deshabilitados para mejorar la
     * visibilidad.
     *
     * Aplica color negro al texto de campos deshabilitados, tanto en los
     * `JTextField` como en el `JDateChooser` y el `JComboBox`.
     */
    private void cambiarColorTexto() {
        dni.setDisabledTextColor(Color.black);
        nombre.setDisabledTextColor(Color.black);
        apellidos.setDisabledTextColor(Color.black);
        telefono.setDisabledTextColor(Color.black);
        edad.setDisabledTextColor(Color.black);
        fecha.setForeground(Color.black);
        ((JTextField) fecha.getDateEditor().getUiComponent()).setDisabledTextColor(Color.BLACK); // Color del texto cuando está deshabilitado
        tipoBono.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setBackground(Color.WHITE);  // Color de fondo cuando está deshabilitado
                c.setForeground(Color.BLACK);       // Color de texto cuando está deshabilitado
                return c;
            }
        });
    }

    /**
     * Carga los datos del cliente en los campos de la interfaz.
     *
     * Los datos mostrados incluyen nombre, apellidos, DNI, edad, teléfono,
     * fecha del bono, tipo de bono y si utiliza pies de gato.
     */
    private void cargarDatosCliente() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        nombre.setText(cliente.getNombre());
        dni.setText(cliente.getDni());
        apellidos.setText(cliente.getApellidos());
        telefono.setText(cliente.getTelefono());
        fecha.setDate(Date.from(cliente.getFechaBono().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        pieGato.setSelected(cliente.isPieGato());
        tipoBono.setSelectedItem(cliente.getTipo_entrada());
        edad.setText(String.valueOf(cliente.getEdad()));
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
        telefono = new javax.swing.JTextField();
        apellidos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        pieGato = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        cancelar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tipoBono = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        edad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Didot", 1, 36)); // NOI18N
        jLabel2.setText("Ver Cliente");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel3.setText("DNI");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 30, -1));

        dni.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        dni.setEnabled(false);
        jPanel1.add(dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 170, -1));

        jLabel4.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel4.setText("Nombre");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        nombre.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        nombre.setEnabled(false);
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 170, -1));

        telefono.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        telefono.setEnabled(false);
        jPanel1.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 240, -1));

        apellidos.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        apellidos.setEnabled(false);
        jPanel1.add(apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 240, -1));

        jLabel6.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel6.setText("Apellido");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jLabel7.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel7.setText("Teléfono");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        fecha.setBackground(new java.awt.Color(255, 255, 255));
        fecha.setEnabled(false);
        jPanel1.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 170, 32));

        jLabel8.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel8.setText("Fecha");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        pieGato.setEnabled(false);
        jPanel1.add(pieGato, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, -1, -1));

        jLabel9.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel9.setText("Tipo de bono");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        cancelar.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, -1, -1));

        jLabel10.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel10.setText("Pies de gato");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        tipoBono.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        tipoBono.setEnabled(false);
        jPanel1.add(tipoBono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 280, -1));

        jLabel11.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel11.setText("Edad");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, -1, -1));

        edad.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        edad.setEnabled(false);
        jPanel1.add(edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 80, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 430, 320));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoPrincipal.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(500, 600));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Acción del botón "Cancelar". Vuelve a la ventana de listado de clientes
     * sin modificar ningún dato.
     *
     * @param evt Evento generado al pulsar el botón
     */
    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        Clientes c = new Clientes();
        c.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidos;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField dni;
    private javax.swing.JTextField edad;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
