
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Operando {
    static InetAddress ipServidor;
    static String hostServidor = "localhost";
    static int numPuertoServidor = 4500;
    static final int MAXBYTES = 600;
    
    public static void main(String[] args)  {
        DatagramSocket socketCliente;
        try {
            ipServidor = InetAddress.getByName(hostServidor);
            socketCliente = new DatagramSocket();
            byte[] bufferEscritura = new byte[MAXBYTES];
            String lineaAMandar = "Hola, soy john y quiero saludarle";
            bufferEscritura = lineaAMandar.getBytes();
            DatagramPacket pE = new DatagramPacket(bufferEscritura, bufferEscritura.length,ipServidor, numPuertoServidor);
            socketCliente.send(pE);

            byte[] bufferLectura = new byte[MAXBYTES];
            DatagramPacket pL = new DatagramPacket(bufferLectura, MAXBYTES);
            System.out.println("Esperando respuesta del servidor");

            socketCliente.receive(pL);
            String lineaRecibida = new String (pL.getData(), 0, pL.getLength(), "UTF-8");
            System.out.println(lineaRecibida);


        } catch (UnknownHostException e) {
           System.out.println(e.getMessage());
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
    }
}
