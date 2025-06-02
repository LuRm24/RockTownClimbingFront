/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Representa un horario disponible para realizar una actividad en el rocódromo
 * Rocktown Climbing.
 * <p>
 * Incluye el día de la semana, la hora de inicio y la hora de fin. Puede
 * asociarse a una o varias actividades, permitiendo definir cuándo están
 * disponibles para los clientes.
 * <p>
 * También incluye métodos utilitarios para convertir nombres de días en español
 * a objetos {@link DayOfWeek} y viceversa.
 *
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */

public class HorarioDisponible {

    private Long id;
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * Convierte un nombre de día de la semana en español a un objeto
     * {@link DayOfWeek}.
     *
     * @param diaStr Nombre del día (ej. "Lunes", "Martes", etc.)
     * @return Objeto {@code DayOfWeek} correspondiente, o {@code null} si no se
     * reconoce
     */
    public static DayOfWeek diaSemana(String diaStr) {
        DayOfWeek dia = null;

        switch (diaStr) {
            case "Lunes":
                dia = DayOfWeek.MONDAY;
                break;
            case "Martes":
                dia = DayOfWeek.TUESDAY;
                break;
            case "Miercoles":
                dia = DayOfWeek.WEDNESDAY;
                break;
            case "Jueves":
                dia = DayOfWeek.THURSDAY;
                break;
            case "Viernes":
                dia = DayOfWeek.FRIDAY;
                break;
            case "Sabado":
                dia = DayOfWeek.SATURDAY;
                break;
            case "Domingo":
                dia = DayOfWeek.SUNDAY;
                break;
        }

        return dia;
    }

    /**
     * Convierte un objeto {@link DayOfWeek} a su representación en español.
     *
     * @param dia Día de la semana (ej. {@code DayOfWeek.MONDAY})
     * @return Cadena con el nombre del día en español (ej. "Lunes")
     */
    public static String diaSemanaStr(DayOfWeek dia) {
        String diaSemana = null;

        switch (dia) {
            case MONDAY:
                diaSemana = "Lunes";
                break;
            case TUESDAY:
                diaSemana = "Martes";
                break;
            case WEDNESDAY:
                diaSemana = "Miercoles";
                break;
            case THURSDAY:
                diaSemana = "Jueves";
                break;
            case FRIDAY:
                diaSemana = "Viernes";
                break;
            case SATURDAY:
                diaSemana = "Sabado";
                break;
            case SUNDAY:
                diaSemana = "Domingo";
                break;
        }

        return diaSemana;
    }
}
