/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.time.DayOfWeek;
import java.time.LocalTime;
import jakarta.persistence.Transient;

public class HorarioDisponible {
    private Long id;
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    @Transient
    private transient Actividad actividad;

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

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
    
    public static DayOfWeek diaSemana(String diaStr){
        DayOfWeek dia = null;
        
        switch (diaStr){
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
    
    public static String diaSemanaStr(DayOfWeek dia){
        String diaSemana = null;
        
        switch (dia){
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
