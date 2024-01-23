import java.io.*;
import java.net.*;

public class ServidorRecibe {

    public static void main(String[] args) {
        // Variables internas
        int puertoEscucha = 12345; // Cambiar al puerto deseado
        String nombreArchivoDestino = "archivo_recibido.txt"; // Cambiar al nombre del archivo de destino

        try (DatagramSocket socket = new DatagramSocket(puertoEscucha)) {
            byte[] buffer = new byte[500];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

            // Crear el archivo de destino
            File miFile = new File(nombreArchivoDestino);
            FileOutputStream fileOutputStream = new FileOutputStream(miFile);

            boolean finFtp = false;

            while (!finFtp) {
                socket.receive(datagramPacket);

                // Verificar si es el último datagrama
                String contenido = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                if (contenido.equals("fin transmision")) {
                    finFtp = true;
                }

                // Escribir en el archivo
                if (!finFtp) {
                    fileOutputStream.write(datagramPacket.getData(), 0, datagramPacket.getLength());
                }
            }

            // Cerrar flujos
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

