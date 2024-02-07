package EstudiantesMultihiloTcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServidorMultihilo1 extends Thread{
    private  Socket socketCliente;
    private  ControlEstudiantes controlEstudiantes;
    private int id = 1;


    public ServidorMultihilo1(Socket cliente,ControlEstudiantes controlEstudiantes){
        this.socketCliente = cliente;
        this.controlEstudiantes = controlEstudiantes;
    }


    @Override
    public void run() {
        try (Scanner br = new Scanner(socketCliente.getInputStream())) {
            PrintWriter pw = new PrintWriter(socketCliente.getOutputStream());
            String lineas;
            while ((lineas = br.nextLine()) != null && lineas.length() > 0) {
                System.out.println("conectado con cliente"+id);
                id += 1;
                buscarCoincidencia(lineas, pw);
                pw.flush();
            }
            

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public  void buscarCoincidencia(String solicitud, PrintWriter salida){
        final String regexpost = "post\\s+([a-z]+)\\s+([0-9]+)";
        final String regexget = "get\\s+[0-9]+";

        Pattern pattern = Pattern.compile(regexpost);
        
        Matcher  matcherPost = pattern.matcher(solicitud);
        if(matcherPost.find()){
            String nombre = matcherPost.group(1);
            int edad = Integer.parseInt(matcherPost.group(2));
            boolean estudiante = controlEstudiantes.agregarEstudiante(nombre,edad);
            if(estudiante){
                salida.println("Se ha agregado el estudiante " + nombre +" con exito.");
                salida.flush();
            }
            salida.println("No se pudo agregar el estudiante");
        }

    }
}
