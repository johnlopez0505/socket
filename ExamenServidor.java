import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExamenServidor {
    private static final int PUERTO = 60000;
    private static List<ExamenUsuarios> listaUsuarios = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor en espera de conexiones...");
            ExecutorService executorService = Executors.newFixedThreadPool(10);

            while (true) {
                Socket socketCliente = serverSocket.accept();
                System.out.println("Cliente conectado desde " + socketCliente.getInetAddress());

                ExamenServidorMultihilo clienteHandler = new ExamenServidorMultihilo(socketCliente);
                clienteHandler.start();
                //executorService.execute(clienteHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
