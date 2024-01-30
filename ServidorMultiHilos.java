import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServidorMultiHilos extends Thread{
    private Socket socketClientes;
    private String nombre = "cliente"; 
    private int id;

    public ServidorMultiHilos(Socket socketClientes,int id) {
        this.socketClientes = socketClientes;
        this.id=id;
    }

    @Override
    public void run() {
        try {
            Scanner br = new Scanner (socketClientes.getInputStream());
            PrintWriter pw = new PrintWriter(socketClientes.getOutputStream());
            System.out.println("Ahora esperamos peticion de los clientes");
            String lineaRecibida;
            while ( (lineaRecibida=br.nextLine())!=null && lineaRecibida.length()>0){
                System.out.printf("Peticion cliente: %s$> %s%n",nombre+this.id,lineaRecibida);
                lineaRecibida = "#"+lineaRecibida+"#";
                pw.println(lineaRecibida);  //mandamos la linea recibida modificada
                pw.flush(); //limpiamos el buffer para que se mande inmediatamente.
            }
            
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }
}
