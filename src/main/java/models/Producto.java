/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class Producto {
    private Long id;
    private String nombre;
    private double precio;
    
    public Producto(Long id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Long getId() { return id; }

    public String getNombre() { return nombre; }

    public double getPrecio() { return precio; }
    
    @Override
    public String toString() {
        return nombre + " - " + precio + "€";
    }

}
