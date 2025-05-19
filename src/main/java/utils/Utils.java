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

public class Utils {
    
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
                           obj.get("frecuencia").getAsString(), obj.get("precio").getAsDouble(), obj.get("notas").getAsString());

                    //Al combo le añadimos el objeto completo
                    tipoBono.addItem(datos);
                }
            }
        }
        catch (IOException io){
            io.printStackTrace();
        }
    }
    
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
        }
        catch (IOException io){
            io.printStackTrace();
        }
    }
}
