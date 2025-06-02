/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Representa un recordatorio creado por un empleado dentro de la aplicación
 * Rocktown Climbing.
 * <p>
 * Un recordatorio contiene un texto y está asociado a un empleado específico.
 * Puede utilizarse para anotar tareas pendientes, avisos internos, mensajes de
 * coordinación o recordatorios personales para la gestión del rocódromo.
 *
 * Esta clase puede ser utilizada para funcionalidades de agenda o panel de
 * avisos en la interfaz de administración.
 *
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */
public class Recordatorio {

    private Long id;
    private String texto;
    private Empleado empleado;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
