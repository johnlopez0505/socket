import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTcp {
    public static final Scanner teclado = new Scanner(System.in);
    public static final String CODTEXTO = "UTF-8";
    public static void main(String[] args) {
        int numPuertoServidor = 44000;
        String hostServidor = "localhost";
        Socket socketComunicacion;
        InetAddress ipServidor;
        try{
            socketComunicacion = new Socket(hostServidor, numPuertoServidor);
            ipServidor=socketComunicacion.getInetAddress();
            System.out.printf("Cliente conectado con servidor %s...%n",ipServidor.getHostAddress());
            Scanner br = new Scanner (socketComunicacion.getInputStream());
            PrintWriter pw = new PrintWriter(socketComunicacion.getOutputStream());
            System.out.print(">");
            String lineas;

            while ( (lineas=teclado.nextLine()).length()>0 ){
                pw.println(lineas);
                pw.flush();
                System.out.printf("Respuesta del servidor &> %s%n", br.nextLine());
                System.out.print(">");
            }
        }  
        catch (UnknownHostException ex){
            System.out.printf("Servidor desconocido %s%n", hostServidor);
            ex.printStackTrace();
            System.exit(2);
        } 
        catch (IOException e){
            System.out.println("Error en flujo de E/S");
            e.printStackTrace();
            System.exit(1);
        }
     }  
} 
