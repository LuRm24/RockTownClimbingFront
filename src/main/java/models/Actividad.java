/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una actividad ofrecida en el rocódromo Rocktown Climbing.
 * <p>
 * Cada actividad tiene un nombre, una descripción, un empleado responsable y
 * una lista de horarios disponibles asociados. La actividad también posee un
 * identificador único.
 *
 * Esta clase se utiliza tanto para la gestión administrativa de actividades
 * como para su visualización y planificación en la aplicación.
 *
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */

public class Actividad {

    private Long id;
    private String nombre;
    private String descripcion;
    private Empleado empleado;

    private List<HorarioDisponible> horarios = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<HorarioDisponible> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioDisponible> horarios) {
        this.horarios = horarios;
    }

    /**
     * Compara esta actividad con otra en base a su ID.
     *
     * @param obj Objeto a comparar
     * @return {@code true} si tienen el mismo ID, {@code false} en caso
     * contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actividad other = (Actividad) obj;
        return this.id.equals(other.id);
    }
}
