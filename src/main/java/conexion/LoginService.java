/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginService {

    public String login(String usuario, String contrasena) {
        try {
            URL url = new URL("http://localhost:8080/login");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            // Montar JSON del login
            String jsonInputString = "{\"nombreUsuario\": \"" + usuario + "\", \"contrasena\": \"" + contrasena + "\"}";

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Leer la respuesta
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString(); // Devuelve JSON con datos del empleado
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

