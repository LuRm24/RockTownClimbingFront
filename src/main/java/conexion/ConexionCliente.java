/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.Socket;
import java.io.PrintWriter;

/**
 *
 * @author luciarodriguezmartin
 */
public class ConexionCliente {
    
    private Socket socket;
    
    public void probarConexion() {
        try {
            
            /*
            URL url = new URL("http://localhost:49300/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");*

            int codigo = conn.getResponseCode();

            if (codigo == 200) {
                System.out.println("Conexión establecida");
            } else {
                System.out.println("El backend respondió con código: " + codigo);
            }

            conn.disconnect();*/
        } catch (Exception e) {
            System.out.println("No se pudo conectar al backend");
            e.printStackTrace();
        }

    }
   public boolean verificarCredenciales(String usuario, String contrasena) {
    try {
        /*
        URL url = new URL("http://localhost:49300/login");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String jsonInputString = "{\"nombreUsuario\": \"" + usuario + "\", \"contrasena\": \"" + contrasena + "\"}";

        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);           
        }

        int codigo = conn.getResponseCode();
        
        if (codigo == HttpURLConnection.HTTP_OK) {
            try(BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    return response.toString().equals("ACCESO CONCEDIDO");
            }
        }

        conn.disconnect();*/
        
        socket = new Socket("localhost", 49300);
        //Le envia al servidor el usuario y el password
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        salida.println(usuario);
        salida.println(contrasena);
        
        //El servidor le contesta si es correcto y se abre la ventana principal
        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return entrada.readLine().equals("ACCESO CONCEDIDO");
            
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

    
}
