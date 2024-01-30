import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ExamenServidorMultihilo extends Thread{

    private Socket socketCliente;
        private BufferedReader entrada;
        private PrintWriter salida;

        public ExamenServidorMultihilo(Socket socketCliente) {
            this.socketCliente = socketCliente;
            try {
                entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                salida = new PrintWriter(socketCliente.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    String[] partes = mensaje.split(" ");
                    String comando = partes[0];

                    switch (comando) {
                        case "reg":
                            registrarUsuario(partes);
                            break;
                        case "log":
                            iniciarSesion(partes);
                            break;
                        case "datu":
                            obtenerDatosUsuario(partes);
                            break;
                        case "list":
                            enviarListaUsuarios();
                            break;
                        case "hash5":
                            calcularHash(partes);
                            break;
                        default:
                            // Comando no reconocido
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }

        private void registrarUsuario(String[] partes) {
            // Implementar lógica de registro de usuario
        }

        private void iniciarSesion(String[] partes) {
            // Implementar lógica de inicio de sesión
        }

        private void obtenerDatosUsuario(String[] partes) {
            // Implementar lógica para devolver los datos del usuario
        }

        private void enviarListaUsuarios() {
            // Implementar lógica para enviar la lista de usuarios
        }

        private void calcularHash(String[] partes) {
            // Implementar lógica para calcular el hash MD5 del archivo
        }

    
}
