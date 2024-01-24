import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;


public class ServerTcp {
    public static final String CODTEXTO = "UTF-8";
    public static void main(String[] args) {
        int numPuertoServidor = 44000;
        ServerSocket socketServidor;
        Socket socketComunicacion=null;
        InetAddress ipCliente;
        boolean noConectado=false;
        int id = 1;

        try{
            //Creamos el socket del servidor.
            System.out.println("Esperando peticion de conexión del cliente .....");
            socketServidor = new ServerSocket(numPuertoServidor);
            while(!noConectado) {
                socketComunicacion = socketServidor.accept();  //aceptamos la comunicación con el cliente
                ipCliente = socketComunicacion.getInetAddress();
                //String hostCliente = ipCliente.getHostAddress();
                System.out.printf("Conexión establecida con cliente con ip ......%s%n", ipCliente);
                ServidorMultiHilos servidorH = new ServidorMultiHilos(socketComunicacion,id);
                id = id+1;
                servidorH.start();
            }

        } catch (NoSuchElementException e){
            System.out.println("El Cliente ha cerrado su conexión....");

            //COMO EJERCICIO......, ANALIZAR EL SIGUIENTE CÓDIGO.........
            if (socketComunicacion!=null && socketComunicacion.isConnected())
                try{
                     socketComunicacion.close();
                }catch (IOException ex){
                    System.out.println("Error en flujo de E/S al cerrar el Socket una vez desconectado con cliente");
                    ex.printStackTrace();
                }
            
        }
        catch (IOException e){
            System.out.println("Error en flujo de E/S");
            e.printStackTrace();
        }
    }
}
