/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Representa un producto disponible en la tienda del rocódromo Rocktown
 * Climbing.
 * <p>
 * Un producto puede ser cualquier artículo a la venta, como bebidas, material
 * de escalada, camisetas, etc. Cada producto tiene un identificador, un nombre
 * descriptivo y un precio.
 *
 * Esta clase se utiliza para la gestión de ventas y para mostrar los productos
 * en la interfaz de caja o tienda de la aplicación.
 *
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */
public class Producto {

    private Long id;
    private String nombre;
    private double precio;

    public Producto(Long id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return nombre + " - " + precio + "€";
    }

}
