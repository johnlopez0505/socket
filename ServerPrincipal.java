import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerPrincipal {
    static int numPuertoServidor = 4500;
    static final int MAXBYTES = 600;
    public static void main(String[] args) {
        InetAddress ipCliente;
        /*Implementar un Cliente/Servidor, en la que el cliente mande operaciones del tipo operando1 
        operacion operando2. El servidor deberá mandar la solución al cliente. El cliente podrá pedir 
        tantas operaciones como requiera, hasta que sea fin de línea.
        El ejemplo será del tipo Cliente -> 10+5 y el Servidor responde con ->(fecha y hora de la respuesta)
        res:20. Tener en cuenta, que si el cliente manda una operacion del tipo 10:0, el servidor deberá mandar 
        una respuesta del tipo -> (fecha y hora de la respuesta) res:err.
        Como consejo, utilizar los Parseos, ya que el cliente podrá mandar tanto enteros, como reales con o sin 
        espacios en blanco. El servidor, deberá mandar en todo momento la fecha y hora de la respuesta, junto al 
        resultado */
        DatagramSocket socketServidor = null;
        try {
            socketServidor = new DatagramSocket(numPuertoServidor);
            byte[] bufferLectura = new byte[MAXBYTES];
            DatagramPacket pL = new DatagramPacket(bufferLectura, MAXBYTES);
            System.out.println("Esperando algún datagrama");

            socketServidor.receive(pL);
            String lineaRecibida = new String (pL.getData(), 0, pL.getLength(), "UTF-8");
            System.out.println(lineaRecibida);
            ipCliente = pL.getAddress();
            int puertoOrigenCliente = pL.getPort();
            byte [] bufferEscritura = new byte[MAXBYTES];
            bufferEscritura = "Muchas gracias, yo también le saludo a usted".getBytes();
            DatagramPacket pE = new DatagramPacket(bufferEscritura,bufferEscritura.length, ipCliente, puertoOrigenCliente);

            socketServidor.send(pE);
        
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
