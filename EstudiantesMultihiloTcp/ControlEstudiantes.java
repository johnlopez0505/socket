package EstudiantesMultihiloTcp;

import java.util.ArrayList;


public class ControlEstudiantes {
    private ArrayList<Estudiante> listEstudiantes = new ArrayList<>();
    private int id = 1;

    public ControlEstudiantes() {
    }

    public ControlEstudiantes(ArrayList<Estudiante> listEstudiantes) {
        this.listEstudiantes = listEstudiantes;
    }

    public synchronized ArrayList<Estudiante> getListEstudiantes() {
        return this.listEstudiantes;
    }

    public synchronized void setListEstudiantes(ArrayList<Estudiante> listEstudiantes) {
        this.listEstudiantes = listEstudiantes;
    }

    public synchronized ControlEstudiantes listEstudiantes(ArrayList<Estudiante> listEstudiantes) {
        setListEstudiantes(listEstudiantes);
        return this;
    }

    

    @Override
    public String toString() {
        return "{" +
            " listEstudiantes='" + getListEstudiantes() + "'" +
            "}";
    }

    public synchronized boolean agregarEstudiante(String nombre, int edad){
        Estudiante estudiante = new Estudiante(id, nombre, edad);
        listEstudiantes.add(estudiante);
        id += 1;
        return true;
    }
    
    
}
