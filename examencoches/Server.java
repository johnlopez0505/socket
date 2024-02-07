package examencoches;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int puertoServidor = 45000; // Puerto en el que se va a escuchar al servidor
        ServerSocket  socketServidor;
        Socket conexionCliente = null;
        InetAddress ipCliente;
        ControlVehiculos  controlVehiculos = new ControlVehiculos();
        int id = 1;


        try {
            System.out.println("Esperando peticion de conexión del cliente .....");
            socketServidor = new ServerSocket(puertoServidor);
        
            while(socketServidor.isBound()){
                conexionCliente = socketServidor.accept();
                ipCliente=conexionCliente.getInetAddress();
                System.out.printf("Conexion establesida con cliente de ip: %s%n",ipCliente);
                ServidorMultihilo  servidorH = new ServidorMultihilo(conexionCliente,id,controlVehiculos);
                id = id +1;
                servidorH.start();
                
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
}
