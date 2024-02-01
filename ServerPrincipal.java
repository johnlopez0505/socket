import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Date;

public class ServerPrincipal {
    public static void main(String[] args) {
        // Configurar el servidor
        try (ServerSocket serverSocket = new ServerSocket(50000)) {
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexión establecida con " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Recibido: " + inputLine);

                    // Obtener la fecha y hora de la respuesta
                    String fechaHoraRespuesta = new Date().toString();

                    // Realizar la operación y enviar la respuesta al cliente
                    String respuesta = "(" + fechaHoraRespuesta + ") " + realizarOperacion(inputLine);
                    out.println(respuesta);
                }

                System.out.println("Cerrando conexión con " + clientSocket.getInetAddress());
                clientSocket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static String realizarOperacion(String operacion) {
        try {
            // Utilizar expresiones regulares para separar operandos y operador
            String[] partes = operacion.trim().split("([+\\-/*])");
            double operando1 = Double.parseDouble(partes[0].trim());
            String[] operadores = operacion.trim().split("[0-9]+");
            String operador = operadores[1].trim();
            double operando2 = Double.parseDouble(partes[1].trim());

            switch (operador) {
                case "+":
                    return "resultado:" + (operando1 + operando2);
                case "-":
                    return "resultado:" + (operando1 - operando2);
                case "*":
                    return "resultado:" + (operando1 * operando2);
                case "/":
                    if (operando2 != 0) {
                        return "resultado:" + (operando1 / operando2);
                    } else {
                        return "resultado: no se puede dividir por cero";
                    }
                default:
                    return "resultado:err";
            }
        } catch (Exception e) {
            return "resultado:err - " + e.getMessage();
        }
    }
}