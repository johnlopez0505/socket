import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EnviarFicheroTcp {
    public static void main(String[] args) {

        File  f = new File("ficheros/archivo1.txt"); 
        if(f.exists()){
            System.out.println("hola mundo");
        }
        System.out.println("el fichero no existe");
        try (FileWriter writer = new FileWriter(f)) {

            PrintWriter  pw = new PrintWriter(writer);
            // Escribimos en el fichero
            pw.println("Hola estoy escribiendo en un fichero");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
}
