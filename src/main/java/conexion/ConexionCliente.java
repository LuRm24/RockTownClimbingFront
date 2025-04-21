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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 *
 * @author luciarodriguezmartin
 */
public class ConexionCliente {
    
    private static Socket socket;
    
    public boolean verificarCredenciales(String usuario, String contrasena) {
        try {
            socket = new Socket("localhost", 49300);
            //Le envia al servidor el usuario y el password
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            salida.println(usuario);
            //Enviarle la contraseña encriptada
            //salida.println(BCrypt.hashpw(String.valueOf(contrasena), BCrypt.gensalt()));
            salida.println(contrasena);

            //El servidor le contesta si es correcto y se abre la ventana principal
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return entrada.readLine().equals("true");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static Socket getSocket() {
        return socket;
    }  
}
