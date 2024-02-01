
import java.io.*;
import java.net.*;


public class Operando {
    public static void main(String[] args) {
        // Configurar el cliente
        BufferedReader in;
        PrintWriter out;
        try (Socket socket = new Socket("localhost", 50000)) {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String operacion;

            do {
                System.out.print("Ingrese la operación (o 'fin' para salir): ");
                operacion = reader.readLine();
                // Enviar la operación al servidor
                out.println(operacion);
                // Recibir y mostrar la respuesta del servidor
                String respuesta = in.readLine();
                System.out.println("Respuesta del servidor: " + respuesta);
            } while (!(operacion.equalsIgnoreCase("fin")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}