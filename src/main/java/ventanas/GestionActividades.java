
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Actividad;
import models.Empleado;
import models.HorarioDisponible;
import utils.LocalDateAdapter;
import utils.LocalTimeAdapter;
import utils.Utils;

/**
 * Clase que gestiona la interfaz de administración de actividades en el
 * sistema. Permite crear, modificar y visualizar actividades, así como asignar
 * horarios disponibles y monitores responsables. La comunicación con el backend
 * se realiza mediante peticiones HTTP.
 *
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */
public class GestionActividades extends javax.swing.JFrame {

    Actividad actividad;
    boolean cambios = false;
    String nombreActividad;
    String descripcionActividad;

    /**
     * Constructor de la ventana de gestión de actividades. Inicializa los
     * componentes y carga los datos necesarios según si se crea o edita una
     * actividad.
     *
     * @param insertActividad true si se está creando una nueva actividad, false
     * si se está editando una existente
     * @param actividad Objeto Actividad que se desea gestionar (puede ser null
     * si es nuevo)
     */
    public GestionActividades(boolean insertActividad, Actividad actividad) {
        initComponents();
        cargarEmpleados();
        cargarHoras("Lunes", comboHoraInicio);
        cargarHoras("Lunes", comboHoraFin);
        this.actividad = actividad;

        if (insertActividad) {
            insertarHorario1.setEnabled(false);
            tablaHorarios.setEnabled(false);
            eliminarHorario.setEnabled(false);
            comboHoraInicio.setEnabled(false);
            comboHoraFin.setEnabled(false);
            diaSemana.setEnabled(false);

        } else {
            insertarHorario1.setEnabled(true);
            tablaHorarios.setEnabled(true);
            eliminarHorario.setEnabled(true);
            comboHoraInicio.setEnabled(true);
            comboHoraFin.setEnabled(true);
            diaSemana.setEnabled(true);

            //Cuando modificas cargamos la actividad
            rellenarDatosActividad();
            cambios = false;

            //Cargar horarios Actividad
            cargarHorariosTabla();
        }
    }

    /**
     * Carga los horarios asociados a la actividad actual en la tabla de la
     * interfaz gráfica.
     */
    private void cargarHorariosTabla() {
        //Definir las columnas de la tabla
        String[] columnas = {"Dia semana", "Hora inicio", "Hora Fin", "Id"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        //Recorrer la lista de horarios de la actividad
        for (HorarioDisponible horario : actividad.getHorarios()) {
            String[] datos = {
                HorarioDisponible.diaSemanaStr(horario.getDiaSemana()),
                horario.getHoraInicio().format(DateTimeFormatter.ofPattern("HH:mm")),
                horario.getHoraFin().format(DateTimeFormatter.ofPattern("HH:mm")),
                String.valueOf(horario.getId())
            };
            //Al modelo le añadimos los datos como una fila
            model.addRow(datos);
        }

        //A la tabla se le asigna el modelo
        tablaHorarios.setModel(model);
    }

    /**
     * Rellena los campos de nombre, descripción y monitor con los datos de la
     * actividad seleccionada.
     */
    private void rellenarDatosActividad() {
        nombre.setText(actividad.getNombre());
        descripcion.setText(actividad.getDescripcion());
        monitor.setSelectedItem((Empleado) actividad.getEmpleado());

        nombreActividad = actividad.getNombre();
        descripcionActividad = actividad.getDescripcion();
    }

    /**
     * Carga una serie de horarios incrementales (de 1 hora) en un JComboBox,
     * desde una hora inicial hasta una final.
     *
     * @param hora JComboBox donde se cargarán las horas
     * @param inicio Hora inicial
     * @param fin Hora final
     */
    private void cargarHorariosCombo(JComboBox hora, LocalTime inicio, LocalTime fin) {
        //Antes de cargar, eliminar si hay horarios en el combo
        hora.removeAllItems();

        //Si la hora de inicio es menor o igual que la hora de fin => añadimos horario
        while (inicio.isBefore(fin) || inicio.equals(fin)) {
            hora.addItem(inicio);
            //Sumamos 1 hora al horario, de tipo long, se añade la L al valor
            inicio = inicio.plusHours(1L);
        }
    }

    /**
     * Carga las horas disponibles en función del día de la semana seleccionado.
     *
     * @param diaSelecc Día de la semana en formato texto
     * @param hora JComboBox que se llenará con las horas correspondientes
     */
    private void cargarHoras(String diaSelecc, JComboBox hora) {
        //Si el dia es de lunes a jueves
        if (diaSelecc.equals("Lunes") || diaSelecc.equals("Martes") || diaSelecc.equals("Miercoles")
                || diaSelecc.equals("Jueves")) {
            //Cargar las horas desde las 12 hasta las 22h
            cargarHorariosCombo(hora, LocalTime.of(12, 0), LocalTime.of(22, 0));
        } //Si es viernes
        else if (diaSelecc.equals("Viernes")) {
            //Cargar las horas desde las 12 hasta las 20h
            cargarHorariosCombo(hora, LocalTime.of(12, 0), LocalTime.of(20, 0));
        } //Si es sabado o domingo
        else {
            //Cargar las horas desde las 12 hasta las 20h
            cargarHorariosCombo(hora, LocalTime.of(10, 0), LocalTime.of(20, 0));
        }
    }

    /**
     * Realiza una petición GET al backend para obtener la lista de empleados
     * disponibles. Carga los datos en el comboBox de monitores.
     */
    private void cargarEmpleados() {
        try {
            URL url = new URL("http://localhost:8080/empleado/select-all");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));

            // Aquí usamos el método que ya tienes hecho para cargar datos en la tabla
            Utils.cargarDatosComboEmpleado(br, monitor);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Asocia un horario ya creado a una actividad existente en el
     * backend.Modifica la actividad una vez insertada
     *
     * @param horarioId ID del horario a asociar
     * @param actividadId ID de la actividad destino
     */
    private void modificarActividadHorario(Long horarioId, Long actividadId) {
        try {
            URL url = new URL("http://localhost:8080/horarios/" + horarioId + "/actividad/" + actividadId);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            int responseCode = con.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(this, "Error al actualizar la actividad del horario");
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    
    /**
     * Comprueba si un horario dado se solapa con alguno de los horarios ya presentes
     * en la tabla de horarios.
     * El método recorre todos los horarios registrados en la tabla y compara día y horas.
     * Se considera que dos horarios se solapan si:
     * Son del mismo día de la semana
     *   El inicio del nuevo horario es anterior al fin de uno existente, y
     *   El fin del nuevo horario es posterior al inicio de uno existente
     * 
     * @param horario Objeto horario a comprobar
     * @return true si el horario se solapa con alguno existente o false en caso contrario
     */
    private boolean comprobarHorarioSolapado(HorarioDisponible horario){
        boolean solapado = false;
        
        //Recorrer tabla de horarios
        for (int i = 0; i < tablaHorarios.getModel().getRowCount() && !solapado; i++) {
            DayOfWeek dia = HorarioDisponible.diaSemana(String.valueOf(tablaHorarios.getValueAt(i, 0)));
            if (horario.getDiaSemana().equals(dia) 
                    && (horario.getHoraFin().isAfter(LocalTime.parse(String.valueOf(tablaHorarios.getValueAt(i, 1))))
                        && horario.getHoraInicio().isBefore(LocalTime.parse(String.valueOf(tablaHorarios.getValueAt(i, 2)))))){
                solapado = true;
            }
        }
        
        return solapado;
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
        jLabel3 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        monitor = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaHorarios = new javax.swing.JTable();
        eliminarHorario = new javax.swing.JButton();
        menuPrincipal = new javax.swing.JButton();
        insertarHorario1 = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        horaInicio = new javax.swing.JLabel();
        comboHoraFin = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        diaSemana = new javax.swing.JComboBox<>();
        comboHoraInicio = new javax.swing.JComboBox<>();
        volver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Didot", 1, 36)); // NOI18N
        jLabel2.setText("Gestión Actividades");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel3.setText("Nombre actividad");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        nombre.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 390, -1));

        jLabel4.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel4.setText("Monitor asignado");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        jLabel6.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel6.setText("Horarios disponibles");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        monitor.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        monitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monitorActionPerformed(evt);
            }
        });
        jPanel1.add(monitor, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 390, -1));

        jLabel5.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel5.setText("Descripción");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));

        descripcion.setColumns(20);
        descripcion.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        descripcion.setRows(5);
        jScrollPane1.setViewportView(descripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 390, -1));

        tablaHorarios.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        tablaHorarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia Semana", "Hora inicio", "Hora fin"
            }
        ));
        jScrollPane2.setViewportView(tablaHorarios);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 530, 170));

        eliminarHorario.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        eliminarHorario.setText("Eliminar horario");
        eliminarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarHorarioActionPerformed(evt);
            }
        });
        jPanel1.add(eliminarHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, -1, -1));

        menuPrincipal.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        menuPrincipal.setText("Menú Principal");
        menuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPrincipalActionPerformed(evt);
            }
        });
        jPanel1.add(menuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 560, -1, -1));

        insertarHorario1.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        insertarHorario1.setText("+");
        insertarHorario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarHorario1ActionPerformed(evt);
            }
        });
        jPanel1.add(insertarHorario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 310, 50, -1));

        guardar.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        guardar.setText("Guardar todo");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 560, -1, -1));

        horaInicio.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        horaInicio.setText("Hora inicio");
        jPanel1.add(horaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, -1, -1));

        comboHoraFin.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(comboHoraFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 140, -1));

        jLabel8.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel8.setText("Dia semana");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        jLabel9.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jLabel9.setText("Hora fin");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, -1, -1));

        diaSemana.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        diaSemana.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" }));
        diaSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diaSemanaActionPerformed(evt);
            }
        });
        jPanel1.add(diaSemana, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 120, -1));

        comboHoraInicio.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        jPanel1.add(comboHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, 140, -1));

        volver.setFont(new java.awt.Font("Malayalam MN", 0, 13)); // NOI18N
        volver.setText("<--");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });
        jPanel1.add(volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 560, 70, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 660, 620));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoPrincipal.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * Elimina el horario seleccionado de la actividad actual, tanto visualmente
     * como en el backend.
     *
     * @param evt Evento del botón "Eliminar horario"
     */
    private void eliminarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarHorarioActionPerformed
        int fila = tablaHorarios.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccionar horario a eliminar");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Deseas borrar el horario?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            Long id = Long.parseLong((String) tablaHorarios.getValueAt(fila, 3));
            URL url = new URL("http://localhost:8080/horarios/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.connect();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(this, "Horario eliminado correctamente");
                //Eliminar el horario de la actividad que esta cargada en la ventana
                actividad.getHorarios().remove(fila);
                //Cargamos los horarios de nuevo en la tabla
                cargarHorariosTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el horario. Código: " + responseCode);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en la operación: " + ex.getMessage());
        }
    }//GEN-LAST:event_eliminarHorarioActionPerformed
    /**
     * Abre la ventana del menú principal y cierra la actual.
     *
     * @param evt Evento del botón "Menú principal"
     */
    private void menuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPrincipalActionPerformed
        // TODO add your handling code here:
        Principal p = new Principal();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuPrincipalActionPerformed
    /**
     * Guarda o actualiza una actividad en el backend. Si es nueva, la inserta;
     * si ya existe, la modifica.
     *
     * @param evt Evento del botón "Guardar todo"
     */
    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
        try {
            // Crear Gson con soporte para LocalDate
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                    .create();

            // Serializar a JSON
            String json;

            //Si la actividad cargada es null => estamos insertando actividad
            //Al json le paso la actividad nueva
            if (actividad == null) {
                Actividad nuevaActividad = new Actividad();
                nuevaActividad.setNombre(nombre.getText());
                nuevaActividad.setDescripcion(descripcion.getText());
                nuevaActividad.setEmpleado((Empleado) monitor.getSelectedItem());
                json = gson.toJson(nuevaActividad);
            } else {
                //Si la actividad ya esta cargada => estamos modificando la actividad
                //Al json le pasamos al actividad actual
                actividad.setNombre(nombre.getText());
                actividad.setDescripcion(descripcion.getText());
                actividad.setEmpleado((Empleado) monitor.getSelectedItem());
                json = gson.toJson(actividad);
            }

            // Crear conexión HTTP
            URL url = new URL("http://localhost:8080/actividad/insert");
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
                if (actividad == null) {
                    JOptionPane.showMessageDialog(this, "Actividad insertada correctamente");
                } else if (cambios == true || !nombreActividad.equals(nombre.getText())
                        || !descripcionActividad.equals(descripcion.getText())) {
                    JOptionPane.showMessageDialog(this, "Actividad modificada correctamente");
                }
                VerActividades vact = new VerActividades();
                vact.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al insertar la actividad");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_guardarActionPerformed

    /**
     * Recarga las horas disponibles cuando se cambia el día de la semana
     * seleccionado.
     *
     * @param evt Evento del comboBox "Día de la semana"
     */
    private void diaSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diaSemanaActionPerformed
        // TODO add your handling code here:
        cargarHoras(String.valueOf(diaSemana.getSelectedItem()), comboHoraInicio);
        cargarHoras(String.valueOf(diaSemana.getSelectedItem()), comboHoraFin);
    }//GEN-LAST:event_diaSemanaActionPerformed
    /**
     * Marca que se ha modificado el monitor seleccionado.
     *
     * @param evt Evento al cambiar el combo de monitor
     */
    private void monitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monitorActionPerformed
        cambios = true;
    }//GEN-LAST:event_monitorActionPerformed

    /**
     * Vuelve a la ventana de visualización de actividades y cierra la actual.
     *
     * @param evt Evento del botón "<--"
     */
    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        // TODO add your handling code here:
        VerActividades ver = new VerActividades();
        ver.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed
    /**
     * Acción asociada al botón extra "+", actualmente sin funcionalidad
     * implementada.
     *
     * @param evt Evento del botón "+"
     */
    private void insertarHorario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarHorario1ActionPerformed
        // TODO add your handling code here:
        try {
            cambios = true;

            //Pasamos a String el dia de la semana
            String diaCombo = String.valueOf(diaSemana.getSelectedItem());
            //convertimos el String a la clase DayOfWeek
            DayOfWeek dia = HorarioDisponible.diaSemana(diaCombo);

            //Hora inicio y fin
            String horaIniCombo = String.valueOf(comboHoraInicio.getSelectedItem());
            String horaFinCombo = String.valueOf(comboHoraFin.getSelectedItem());

            //Convertir a LocalTime
            LocalTime horaInicio = LocalTime.parse(horaIniCombo, DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime horaFin = LocalTime.parse(horaFinCombo, DateTimeFormatter.ofPattern("HH:mm"));

            //Si el horario de fin es menor que el inicio
            if (horaFin.isBefore(horaInicio) || horaFin.equals(horaInicio)) {
                JOptionPane.showMessageDialog(this, "El horario de fin es menor que el horario de inicio", "Error horario", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Construir objeto Horario
            HorarioDisponible horario = new HorarioDisponible();
            horario.setDiaSemana(dia);
            horario.setHoraInicio(horaInicio);
            horario.setHoraFin(horaFin);
            
            if (comprobarHorarioSolapado(horario)){
                JOptionPane.showMessageDialog(this, "El horario se solapa con otro horario en el mismo día", "Error horario", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Serializar a JSON
            // Crear Gson con soporte para LocalDate
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                    .create();

            String json = gson.toJson(horario);

            // Crear conexión HTTP
            URL url = new URL("http://localhost:8080/horarios/insert");
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

                //Recuperar la respuesta del servidor una vez insertado para guardar el id nuevo
                //que acaba de insertar
                try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {

                    String respuesta = "";
                    JsonObject obj = null;
                    //Leemos la respuesta y la guardamos en un String
                    respuesta = br.readLine();
                    //Convertimos el String a JSON
                    obj = JsonParser.parseString(respuesta).getAsJsonObject();
                    //Del JSON guardamos el id en el horario
                    horario.setId(obj.get("id").getAsLong());
                }

                //Modificar la actividad en la bbdd una vez insertado el horario
                modificarActividadHorario(horario.getId(), actividad.getId());

                JOptionPane.showMessageDialog(this, "Horario insertado correctamente");
                //Añadir el horario a la tabla
                actividad.getHorarios().add(horario);
                //Actualizar tabla
                cargarHorariosTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al insertar el horario");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_insertarHorario1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboHoraFin;
    private javax.swing.JComboBox<String> comboHoraInicio;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JComboBox<String> diaSemana;
    private javax.swing.JButton eliminarHorario;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel horaInicio;
    private javax.swing.JButton insertarHorario1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton menuPrincipal;
    private javax.swing.JComboBox<Empleado> monitor;
    private javax.swing.JTextField nombre;
    private javax.swing.JTable tablaHorarios;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
