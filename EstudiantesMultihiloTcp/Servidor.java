package EstudiantesMultihiloTcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket  server;
        int puertoServer = 34000;
        Socket conexionClientes;
        InetAddress ip;
        ControlEstudiantes controlEstudiantes = new ControlEstudiantes();
        try {
            System.out.println("Servidor a la esperar de peticiones del cliente");
            server = new ServerSocket(puertoServer);
            while (server.isBound()) {
                conexionClientes = server.accept();
                ip = conexionClientes.getInetAddress();
                System.out.println("conexion establesida con cliente de ip: " + ip.getHostAddress());
                ServidorMultihilo1 servidorH = new ServidorMultihilo1(conexionClientes, controlEstudiantes);
                servidorH.start();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
