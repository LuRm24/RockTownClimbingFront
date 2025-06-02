/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import models.Recordatorio;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;
import models.Empleado;

/**
 * Ventana principal de la aplicación RockTown Climbing.
 *
 * Esta clase representa el panel de control al que accede un empleado tras
 * iniciar sesión. Permite navegar a diferentes secciones de la aplicación
 * (clientes, empleados, caja, actividades) y gestionar recordatorios personales
 * del usuario autenticado.
 *
 * Los botones y funcionalidades disponibles dependen del rol del empleado
 * (administrador o usuario). Se cargan los datos del empleado autenticado y sus
 * recordatorios desde el backend.
 *
 * @author Lucía Rodríguez Martín
 * @version 1.0
 */
public class Principal extends javax.swing.JFrame {

    private List<Recordatorio> recordatorios;
    private int lineasInicio;
    private int lineasFinal;
    private Empleado emp;

    /**
     * Creates new form Principal
     */
    public Principal() {

        initComponents();
        this.setLocationRelativeTo(null);
        cargarUsuarioLogueado();
        cargarRecordatorios();

    }

    /**
     * Habilita o deshabilita el acceso a la sección de empleados dependiendo
     * del rol del usuario.
     */
    private void habilitarPermisos() {

        if (emp != null) {
            System.out.println("Rol del empleado logueado: " + emp.getRol());
            boolean esAdmin = "ADMINISTRADOR".equalsIgnoreCase(emp.getRol());
            empleados.setEnabled(esAdmin); // Si es admin, lo habilitas, si no, lo desactivas
        } else {
            empleados.setEnabled(false); // Si no hay empleado, no dejamos usar el botón
        }
    }

    /**
     * Carga los datos del empleado actualmente logueado y habilita o
     * deshabilita ciertas funcionalidades en función de su rol.
     */
    private void cargarUsuarioLogueado() {

        try {
            //Buscar el empleado Logueado para asignarle los recordatorios nuevos
            URL url = new URL("http://localhost:8080/empleado/find?id=" + Interfaz.ID_EMP);
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
                    Gson gson = new Gson();
                    emp = gson.fromJson(json, Empleado.class);

                    habilitarPermisos();

                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Carga desde el backend los recordatorios asociados al empleado
     * autenticado y los muestra en el área de texto.
     */
    private void cargarRecordatorios() {
        try {
            // Suponiendo que tienes acceso al ID del empleado conectado
            Long idEmpleado = Interfaz.ID_EMP; // o usa tu variable local

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

            // Mostrar los recordatorios en el jTextArea
            jTextArea1.setText("");

            for (Recordatorio r : recordatorios) {
                jTextArea1.append("• " + r.getTexto() + "\n");
            }

            lineasInicio = jTextArea1.getLineCount() - 1;

        } catch (Exception e) {
            e.printStackTrace();
            jTextArea1.setText("Error al cargar los recordatorios.");
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

        jRadioButton1 = new javax.swing.JRadioButton();
        jMenu3 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        actividades = new javax.swing.JLabel();
        empleados = new javax.swing.JLabel();
        clientes = new javax.swing.JLabel();
        caja = new javax.swing.JLabel();
        enviarRecodat = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btonCerrar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButtonGuardar = new javax.swing.JButton();
        jButtonBorrar = new javax.swing.JButton();
        jLabel1Fondo = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Malayalam MN", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Panel de Control");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        actividades.setFont(new java.awt.Font("Malayalam MN", 0, 14)); // NOI18N
        actividades.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actividades.setText("Actividades");
        actividades.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        actividades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actividadesMouseClicked(evt);
            }
        });

        empleados.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        empleados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empleados.setText("Empleados");
        empleados.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empleadosMouseClicked(evt);
            }
        });

        clientes.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        clientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clientes.setText("Clientes");
        clientes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientesMouseClicked(evt);
            }
        });

        caja.setFont(new java.awt.Font("Malayalam MN", 0, 14)); // NOI18N
        caja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        caja.setText("Caja");
        caja.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        caja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cajaMouseClicked(evt);
            }
        });

        enviarRecodat.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        enviarRecodat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enviarRecodat.setText("Enviar Recordatorio");
        enviarRecodat.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        enviarRecodat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enviarRecodatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(enviarRecodat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(caja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(actividades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(empleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(caja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(actividades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(empleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(clientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(enviarRecodat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 190, 260));

        jLabel2.setFont(new java.awt.Font("Didot", 1, 36)); // NOI18N
        jLabel2.setText("¡Bienvenido a RockTown Climbing!");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jPanel2.setOpaque(false);

        btonCerrar.setText("X");
        btonCerrar.setBorderPainted(false);
        btonCerrar.setContentAreaFilled(false);
        btonCerrar.setFocusPainted(false);
        btonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btonCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 70, 70));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Malayalam MN", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Recordatorios");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 240, 30));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 320, 100));

        jButtonGuardar.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 120, 30));

        jButtonBorrar.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jButtonBorrar.setText("Borrar Todos");
        jButtonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 170, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 370, 260));

        jLabel1Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoPrincipal.png"))); // NOI18N
        getContentPane().add(jLabel1Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -2, 710, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * Acción que elimina todos los recordatorios del empleado autenticado.
     *
     * @param evt Evento de clic sobre el botón "Borrar Todos"
     */
    private void jButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarActionPerformed
        // TODO add your handling code here:
        try {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar los recordatorios?",
                    "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            //0 => Si, 1 => No
            if (opcion == 0) {
                // Crear conexión HTTP
                URL url = new URL("http://localhost:8080/recordatorio/delete");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("DELETE");
                con.setDoOutput(true);
                con.setRequestProperty("Content-Type", "application/json");

                int responseCode = con.getResponseCode();
                //Si la respuesta no ha ido bien, indicamos que ha habido un error
                if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                    JOptionPane.showMessageDialog(this, "Recordatorios eliminados correctamente");
                    cargarRecordatorios();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar recordatorios");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonBorrarActionPerformed
    /**
     * Acción que guarda los nuevos recordatorios añadidos por el usuario. Cada
     * línea nueva se guarda como un objeto independiente.
     *
     * @param evt Evento de clic sobre el botón "Guardar"
     */
    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // TODO add your handling code here:    
        lineasFinal = jTextArea1.getLineCount();
        String texto = jTextArea1.getText();
        String[] lineas = texto.split("\n");
        boolean error = false;

        for (int i = lineasInicio; i < lineasFinal && error == false; i++) {

            Recordatorio nuevoRecordatorio = new Recordatorio();
            nuevoRecordatorio.setTexto(lineas[i]);
            nuevoRecordatorio.setEmpleado(emp);

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
                //Si la respuesta no ha ido bien, indicamos que ha habido un error
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    error = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        //Si no ha habido error, mostramos mensaje OK
        if (error == false) {
            JOptionPane.showMessageDialog(this, "Recordatorios guardados correctamente");
        } else {
            //Si ha habido error, mostramos mensaje error
            JOptionPane.showMessageDialog(this, "Error al insertar los recordatorios");
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed
    /**
     * Abre la ventana de la caja.
     *
     * @param evt Evento de clic sobre el botón "Caja"
     */
    private void cajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cajaActionPerformed
        // TODO add your handling code here:
        Caja caja = new Caja();
        caja.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cajaActionPerformed

    /**
     * Abre la ventana de gestión de empleados (solo si el usuario tiene
     * permisos).
     *
     * @param evt Evento de clic sobre el botón "Empleados"
     */
    private void empleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empleadosActionPerformed
        // TODO add your handling code here:
        if (empleados.isEnabled()) {
            Empleados empleadosVentana = new Empleados();
            empleadosVentana.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_empleadosActionPerformed
    /**
     * Abre la ventana de gestión de actividades.
     *
     * @param evt Evento de clic sobre el botón "Actividades"
     */
    private void actividadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actividadesActionPerformed
        // TODO add your handling code here:
        VerActividades vact = new VerActividades();
        vact.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_actividadesActionPerformed
    /**
     * Abre la ventana de gestión de clientes.
     *
     * @param evt Evento de clic sobre el botón "Clientes"
     */
    private void clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesActionPerformed
        // TODO add your handling code here:
        Clientes c = new Clientes();
        c.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_clientesActionPerformed

    /**
     * Abre la ventana para crear un nuevo recordatorio.
     *
     * @param evt Evento de clic sobre el botón "Enviar Recordatorio"
     */
    private void enviarRecodatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enviarRecodatMouseClicked
        AltaRecordatorio alta = new AltaRecordatorio();
        alta.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_enviarRecodatMouseClicked
    /**
     * Acción para cerrar la aplicación desde el botón "X".
     *
     * @param evt Evento de clic sobre el botón
     */
    private void btonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonCerrarActionPerformed

        System.exit(0);
    }//GEN-LAST:event_btonCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actividades;
    private javax.swing.JButton btonCerrar;
    private javax.swing.JLabel caja;
    private javax.swing.JLabel clientes;
    private javax.swing.JLabel empleados;
    private javax.swing.JLabel enviarRecodat;
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel1Fondo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
