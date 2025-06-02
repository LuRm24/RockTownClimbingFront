/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Clase encargada de gestionar el proceso de autenticación del usuario mediante
 * una solicitud HTTP al backend.
 * <p>
 * Envía las credenciales introducidas (usuario y contraseña) en formato JSON al
 * endpoint {@code http://localhost:8080/login} utilizando el método POST.
 *
 * La respuesta esperada es un JSON con los datos del empleado autenticado.
 *
 * Este servicio puede ser utilizado desde una interfaz gráfica para verificar
 * si un usuario puede acceder a la aplicación.
 *
 * <p>
 * Ejemplo de uso:
 * <pre>{@code
 * LoginService servicio = new LoginService();
 * String respuesta = servicio.login("lucia", "1234");
 * }</pre>
 *
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */
public class LoginService {

    /**
     * Realiza una solicitud POST al backend para autenticar a un usuario.
     *
     * @param usuario Nombre de usuario introducido.
     * @param contrasena Contraseña introducida por el usuario.
     * @return Una cadena JSON con los datos del usuario autenticado, o
     * {@code null} si ocurre un error en la conexión o en la autenticación.
     */
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
