/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Representa a un empleado del rocódromo Rocktown Climbing.
 * <p>
 * Contiene información personal, de contacto y de autenticación de los
 * empleados. Cada empleado tiene un rol (por ejemplo, administrador o monitor)
 * y puede estar asociado a actividades o gestionar clientes en el sistema.
 *
 * Esta clase se utiliza en procesos como el inicio de sesión, asignación de
 * actividades y gestión de usuarios internos de la aplicación.
 *
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */
public class Empleado {

    private String dni;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String rol;
    private String nombreUsuario;
    private String email;
    private String contrasenaHash;
    private Long id;
    private String telefono;

    public Empleado() {

    }

    public Empleado(String dni, String nombre, String apellidos, String direccion, String rol, String nombreUsuario, String email, Long id, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.rol = rol;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasenaHash = contrasenaHash;
        this.id = id;
        this.telefono = telefono;
    }

    // Getters y setters como vimos antes
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Devuelve una representación del empleado como texto, mostrando su nombre
     * completo.
     *
     * @return Nombre y apellidos del empleado
     */
    @Override
    public String toString() {
        return nombre + "-" + apellidos;
    }

    /**
     * Compara dos empleados por su identificador.
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
        final Empleado other = (Empleado) obj;
        return this.getId() == other.getId();
    }
}
