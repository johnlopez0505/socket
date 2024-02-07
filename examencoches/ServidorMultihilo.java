package examencoches;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServidorMultihilo extends Thread {
    private  Socket socketComunicacion;
    private ControlVehiculos  controlVehiculos;
    private int id;
    private String nombre ="cliente";

    public ServidorMultihilo(Socket socketComunicacion, int id, ControlVehiculos controlVehiculos){
        this.socketComunicacion = socketComunicacion;
        this.id = id;
        this.controlVehiculos = controlVehiculos;
    }

    @Override
    public void run() {
        
        try (Scanner br = new Scanner(socketComunicacion.getInputStream())) {
            PrintWriter pw = new PrintWriter(socketComunicacion.getOutputStream());
            System.out.println("Ahora esperamos peticion de los clientes");
            String lineaRecibida;
            while ((lineaRecibida =br.nextLine()) != null && lineaRecibida.length() > 0) {
                System.out.printf("Peticion cliente: %s$> %s%n",nombre+this.id,lineaRecibida);
                //lineaRecibida = "#"+lineaRecibida+"#";
                //pw.println(lineaRecibida);  //mandamos la linea recibida modificada
                procesarSolicitud(lineaRecibida, pw);
                pw.flush(); //limpiamos el buffer para que se mande inmediatamente.

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void procesarSolicitud(String solicitud, PrintWriter salida) {
        final String expPost = "post\\s+([a-zA-Z]+)\\s+(\\d{1,4})";
        final String expGetId = "get\\s+(\\d+)";
        final String expGetAllIds = "get\\s*\\?";
        final String expPut = "put\\s+(\\d+)\\s+([a-zA-Z]+)\\s+(\\d{1,4})";
        final String expDelete = "delete\\s+(\\d+)";

        // Patrones
        Pattern patPost = Pattern.compile(expPost);
        Pattern patGetId = Pattern.compile(expGetId);
        Pattern patGetAllIds = Pattern.compile(expGetAllIds);
        Pattern patPut = Pattern.compile(expPut);
        Pattern patDelete = Pattern.compile(expDelete);

        Matcher matcherPost = patPost.matcher(solicitud);
        Matcher matcherGetId = patGetId.matcher(solicitud);
        Matcher matcherGetAllIds = patGetAllIds.matcher(solicitud);
        Matcher matcherPut = patPut.matcher(solicitud);
        Matcher matcherDelete = patDelete.matcher(solicitud);

        if (matcherPost.matches()) {
            String modelo = matcherPost.group(1);
            int cilindrada = Integer.parseInt(matcherPost.group(2));
            Coches coche = controlVehiculos.agregarCoche(modelo, cilindrada);
            if (coche != null){
                salida.println("Coche creado correctamente: " + coche);
                salida.flush();
            }
            else{
                salida.println("Error 400: No se pudo crear el coche");
                salida.flush();
            }
        } else if (matcherGetId.matches()) {
            int id = Integer.parseInt(matcherGetId.group(1));
            Coches coche = controlVehiculos.obtenerCochePorId(id);
            if (coche != null) {
                salida.println(coche);
            } else {
                salida.println("Error 400: No existe ese vehículo.");
            }
        } else if (matcherGetAllIds.matches()) {
            List<Integer> ids = controlVehiculos.obtenerTodosLosIds();
            salida.println(ids);
        } else if (matcherPut.matches()) {
            int id = Integer.parseInt(matcherPut.group(1));
            String modelo = matcherPut.group(2);
            int cilindrada = Integer.parseInt(matcherPut.group(3));
            Coches coche = controlVehiculos.actualizarCoche(id, modelo, cilindrada);
            salida.println("Coche actualizado correctamente: " + coche);

        } else if (matcherDelete.matches()) {
            int id = Integer.parseInt(matcherDelete.group(1));
            Coches coche = controlVehiculos.eliminarCoche(id);
            salida.println("Coche eliminado correctamente : " + coche);
        } else {
            salida.println("Error 400: Solicitud no válida.");
        }
    }

}
