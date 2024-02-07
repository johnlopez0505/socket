package examencoches;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Socket socketCliente;
        String localhost = "localhost";
        int puertoServidor = 45000;
        InetAddress ip;

        try (Scanner teclado = new Scanner(System.in)) {
            try {
                socketCliente = new Socket(localhost,puertoServidor);
                ip = socketCliente.getInetAddress();
                System.out.printf("Conectado al servidor %s%n",ip.getHostAddress());
                try (Scanner br = new Scanner(socketCliente.getInputStream())) {
                    PrintWriter pw = new PrintWriter(socketCliente.getOutputStream());
                    String lineas;

                    while((lineas = teclado.nextLine()).length() > 0){ 
                       pw.println(lineas); 
                       pw.flush();
                       System.out.printf("Respuesta del servidor &> %s%n", br.nextLine());
                       System.out.print(">");

                    }
                }

            } catch (IOException e) {
              System.out.println(e.getMessage());
            }
        }
    }
}
