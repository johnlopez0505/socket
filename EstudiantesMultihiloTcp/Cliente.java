package EstudiantesMultihiloTcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Socket socketComunicacion;
        String locaHost = "localHost";
        InetAddress ip ;
        int puertoServer = 34000;
        try {
            Scanner teclado = new Scanner(System.in);
            socketComunicacion = new Socket(locaHost,puertoServer);
            ip = socketComunicacion.getInetAddress();
            System.out.println("Conectado al servidor con ip :" + ip.getHostAddress()+" >>>>>");
            try ( Scanner br = new Scanner(socketComunicacion.getInputStream());
                PrintWriter pw = new  PrintWriter(socketComunicacion.getOutputStream())) {
                    String lineas;
                while ((lineas = teclado.nextLine()) != null) {
                    pw.println(lineas);
                    pw.flush();
                    System.out.println();
                    System.out.printf("Respuesta del servidor > %s%n", br.nextLine());
                    System.out.print(">");
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
           

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
