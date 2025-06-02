/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JComboBox;
import models.Empleado;
import models.TipoEntrada;

/**
 * Clase de utilidades generales para la aplicación Rocktown Climbing.
 * <p>
 * Contiene métodos estáticos para cargar datos en componentes de interfaz
 * gráfica como {@link JComboBox}, así como validaciones comunes de formularios
 * (DNI, email y contraseña).
 *
 * Estos métodos ayudan a mantener el código más organizado y reutilizable.
 *
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */
public class Utils {

    /**
     * Realiza una petición HTTP GET al backend para obtener todos los tipos de
     * entrada disponibles y los carga en un {@link JComboBox}.
     *
     * @param tipoBono JComboBox donde se cargarán los objetos
     * {@link TipoEntrada}
     */
    public static void cargarTiposEntrada(JComboBox tipoBono) {
        try {
            URL url = new URL("http://localhost:8080/tipo_entrada/select-all");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));

            cargarDatosCombo(br, tipoBono);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Carga una lista de objetos {@link TipoEntrada} en un {@link JComboBox} a
     * partir de un {@link BufferedReader} con datos en formato JSON.
     *
     * @param entrada Flujo de datos con la respuesta JSON
     * @param tipoBono JComboBox a llenar con los tipos de entrada
     */
    public static void cargarDatosCombo(BufferedReader entrada, JComboBox tipoBono) {
        try {
            // Leer todo el contenido del BufferedReader
            String datosLeidos = "";
            String linea;
            while ((linea = entrada.readLine()) != null) {
                datosLeidos += linea;
            }

            if (datosLeidos.startsWith("[")) {
                // Parsear como JsonArray
                JsonArray jsonArray = JsonParser.parseString(datosLeidos).getAsJsonArray();

                //Recorrer el json e ir añadiendo las filas
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject obj = jsonArray.get(i).getAsJsonObject();

                    //Añadimos los datos de la fila en un array
                    TipoEntrada datos = new TipoEntrada(obj.get("id").getAsLong(), obj.get("tipo").getAsString(),
                            obj.get("descripcion").getAsString(), obj.get("publicoDestino").getAsString(),
                            obj.get("frecuencia").getAsString(), obj.get("precio").getAsDouble(),
                            Boolean.parseBoolean(obj.get("bebidaIncluida").getAsString()),
                            Boolean.parseBoolean(obj.get("comidaIncluida").getAsString()),
                            obj.get("notas").getAsString());

                    //Al combo le añadimos el objeto completo
                    tipoBono.addItem(datos);
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Carga una lista de empleados en un {@link JComboBox} a partir de un flujo
     * JSON.
     *
     * @param entrada Flujo con los datos en formato JSON
     * @param monitor JComboBox a llenar con objetos {@link Empleado}
     */
    public static void cargarDatosComboEmpleado(BufferedReader entrada, JComboBox monitor) {
        try {
            // Leer todo el contenido del BufferedReader
            String datosLeidos = "";
            String linea;
            while ((linea = entrada.readLine()) != null) {
                datosLeidos += linea;
            }

            if (datosLeidos.startsWith("[")) {
                // Parsear como JsonArray
                JsonArray jsonArray = JsonParser.parseString(datosLeidos).getAsJsonArray();

                //Recorrer el json e ir añadiendo las filas
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject obj = jsonArray.get(i).getAsJsonObject();

                    //Añadimos los datos de la fila en un array
                    Empleado emp = new Empleado(obj.get("dni").getAsString(), obj.get("nombre").getAsString(),
                            obj.get("apellidos").getAsString(), obj.get("direccion").getAsString(),
                            obj.get("rol").getAsString(), obj.get("nombreUsuario").getAsString(),
                            obj.get("email").getAsString(), obj.get("id").getAsLong(), obj.get("telefono").getAsString());

                    //Al combo le añadimos el objeto completo
                    monitor.addItem(emp);
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Valida si un DNI tiene un formato correcto: 8 números seguidos de una
     * letra mayúscula.
     *
     * @param dni DNI a validar
     * @return {@code true} si el formato es válido, {@code false} en caso
     * contrario
     */
    public static boolean validarDNI(String dni) {
        //Devolver si el dni cuadra con el patron
        return dni.matches("[0-9]{8}[A-Z]{1}");
    }

    /**
     * Valida si un email tiene un formato sintácticamente correcto.
     *
     * @param email Dirección de correo electrónico
     * @return {@code true} si el formato es válido, {@code false} si es
     * inválido
     */
    public static boolean validarEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[a-zA-Z]{2,}$");
    }

    /**
     * Valida si una contraseña cumple los criterios definidos: contener al
     * menos una letra mayúscula, un carácter especial y tener entre 6 y 10
     * caracteres.
     *
     * @param contrasena Contraseña a validar
     * @return {@code true} si es segura, {@code false} en caso contrario
     */
    public static boolean validarContrasena(String contrasena) {
        return contrasena.matches(".*[A-Z].*") && contrasena.matches(".*[!@#$%^&*()_+\\-={}:;\"'<>,.?/~`].*")
                && contrasena.length() >= 6 && contrasena.length() <= 10;
    }
}
