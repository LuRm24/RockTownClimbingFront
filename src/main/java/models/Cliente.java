package models;

import java.time.LocalDate;

/**
 * Representa un cliente del rocódromo Rocktown Climbing.
 * <p>
 * Contiene información personal como nombre, apellidos, teléfono, DNI, edad y
 * datos adicionales como la fecha de inicio del bono, el número de sesiones
 * gastadas, si alquila pies de gato, y el tipo de entrada que ha adquirido.
 *
 * Esta clase se utiliza para gestionar los clientes registrados en el sistema.
 *
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */
public class Cliente {

    private Long id;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String dni;
    private LocalDate fechaBono;
    private int sesionesGastadas;
    private boolean pieGato;
    private int edad;
    private TipoEntrada tipo_entrada;

    public Cliente() {
    }

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaBono() {
        return fechaBono;
    }

    public void setFechaBono(LocalDate fechaBono) {
        this.fechaBono = fechaBono;
    }

    public int getSesionesGastadas() {
        return sesionesGastadas;
    }

    public void setSesionesGastadas(int sesionesGastadas) {
        this.sesionesGastadas = sesionesGastadas;
    }

    public boolean isPieGato() {
        return pieGato;
    }

    public void setPieGato(boolean pieGato) {
        this.pieGato = pieGato;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public TipoEntrada getTipo_entrada() {
        return tipo_entrada;
    }

    public void setTipo_entrada(TipoEntrada tipo_entrada) {
        this.tipo_entrada = tipo_entrada;
    }
}
