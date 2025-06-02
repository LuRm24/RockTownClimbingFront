/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import models.TipoEntrada;

/**
 * Ventana de visualización de detalles de un bono (tipo de entrada).
 *
 * Esta interfaz permite mostrar al usuario todos los atributos de un bono
 * previamente seleccionado, como su tipo, descripción, público objetivo,
 * frecuencia, precio y si incluye bebida o comida. Todos los campos son de solo
 * lectura.
 *
 * Esta ventana se utiliza desde la sección de Caja para consultar las
 * características del bono elegido.
 *
 * @author Lucía Rodríguez Martín
 * @version 1.0
 */
public class VerBono extends javax.swing.JFrame {

    TipoEntrada bono;

    public VerBono(TipoEntrada bono) {
        this.bono = bono;
        initComponents();
        rellenarDatosBono();
    }

    /**
     * Carga los datos del bono en los campos de la interfaz. Los campos están
     * deshabilitados para que no puedan ser modificados.
     */
    private void rellenarDatosBono() {
        tipo.setText(bono.getTipo());
        descripcion.setText(bono.getDescripcion());
        publico.setText(bono.getPublicoDestino());
        frecuencia.setText(bono.getFrecuencia());
        precio.setText(String.valueOf(bono.getPrecio()));
        bebidaIncluida.setSelected(bono.isBebidaIncluida());
        comidaIncluida.setSelected(bono.isComidaIncluida());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tipo = new javax.swing.JTextField();
        publico = new javax.swing.JTextField();
        frecuencia = new javax.swing.JTextField();
        precio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        cancelar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        bebidaIncluida = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        comidaIncluida = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Didot", 1, 36)); // NOI18N
        jLabel2.setText("Detalle Bono");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel3.setText("Tipo");

        jLabel4.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel4.setText("Descripción");

        jLabel5.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel5.setText("Público");

        jLabel6.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel6.setText("Frecuencia");

        jLabel7.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel7.setText("Precio");

        tipo.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        tipo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tipo.setEnabled(false);

        publico.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        publico.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        publico.setEnabled(false);

        frecuencia.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        frecuencia.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        frecuencia.setEnabled(false);

        precio.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        precio.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        precio.setEnabled(false);

        descripcion.setColumns(20);
        descripcion.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        descripcion.setRows(5);
        descripcion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        descripcion.setEnabled(false);
        jScrollPane1.setViewportView(descripcion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(76, 76, 76))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(publico, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(frecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(publico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(frecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 340));

        cancelar.setFont(new java.awt.Font("Kohinoor Bangla", 0, 13)); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        getContentPane().add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, -1, -1));

        jLabel8.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel8.setText("Bebida Incluida");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, -1, -1));

        bebidaIncluida.setBackground(new java.awt.Color(255, 255, 255));
        bebidaIncluida.setEnabled(false);
        getContentPane().add(bebidaIncluida, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, -1));

        jLabel9.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel9.setText("Comida Incluida");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, -1, -1));

        comidaIncluida.setBackground(new java.awt.Color(255, 255, 255));
        comidaIncluida.setEnabled(false);
        getContentPane().add(comidaIncluida, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, -5, 400, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents
 /**
     * Acción asociada al botón "Cancelar". Cierra la ventana actual y vuelve a
     * la vista de Caja.
     *
     * @param evt Evento de acción del botón
     */
    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        Caja c = new Caja();
        c.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox bebidaIncluida;
    private javax.swing.JButton cancelar;
    private javax.swing.JCheckBox comidaIncluida;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JTextField frecuencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField precio;
    private javax.swing.JTextField publico;
    private javax.swing.JTextField tipo;
    // End of variables declaration//GEN-END:variables
}
