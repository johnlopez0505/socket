import java.io.*;
import java.net.*;

public class ClienteEnvia {

    public static void main(String[] args) {
        // Variables internas
        String ipDestino = "127.0.0.1"; // Cambiar a la IP del servidor
        int puertoDestino = 12345; // Cambiar al puerto deseado
        String nombreArchivo = "archivo.txt"; // Cambiar al nombre del archivo a enviar

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress direccionDestino = InetAddress.getByName(ipDestino);
            File file = new File(nombreArchivo);
            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] buffer = new byte[500];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, bytesRead, direccionDestino, puertoDestino);
                socket.send(datagramPacket);
            }

            // Enviar un último datagrama vacío o con un texto que indique "fin transmisión"
            String finTransmision = "fin transmision";
            byte[] finBuffer = finTransmision.getBytes();
            DatagramPacket finPacket = new DatagramPacket(finBuffer, finBuffer.length, direccionDestino, puertoDestino);
            socket.send(finPacket);

            // Cerrar flujos
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

