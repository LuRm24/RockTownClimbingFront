/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import ventanas.Interfaz;

/**
 * Clase principal de la aplicación Rocktown Climbing.
 *
 * Este es el punto de entrada a la aplicación. Se encarga de lanzar la interfaz
 * gráfica principal utilizando el hilo de eventos de Swing.
 *
 * Al ejecutarse, instancia la ventana {@link ventanas.Interfaz} y la hace
 * visible.
 *
 * Ejemplo de ejecución:
 * <pre>{@code
 * java conexion.RocktownClimbingApp
 * }</pre>
 *
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */
public class RocktownClimbingApp {

    public static void main(String[] args) {
        /**
         * Método principal (main) que lanza la interfaz gráfica de la
         * aplicación.
         *
         * @param args Argumentos de la línea de comandos (no se utilizan en
         * esta aplicación).
         */
        java.awt.EventQueue.invokeLater(() -> {
            new Interfaz().setVisible(true);
        });
    }
}
