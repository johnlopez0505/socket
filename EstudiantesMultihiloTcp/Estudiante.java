package EstudiantesMultihiloTcp;


public class Estudiante {
    private int id;
    private  String nombre;
    private int edad;

    public Estudiante() {
    }
    public Estudiante(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
    }

    public Estudiante(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Estudiante id(int id) {
        setId(id);
        return this;
    }

    public Estudiante nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Estudiante edad(int edad) {
        setEdad(edad);
        return this;
    }

  

    @Override
    public String toString() {
        return "[" +
            " 'id':'" + getId() + "'" +
            ", 'nombre':'" + getNombre() + "'" +
            ", 'edad':'" + getEdad() + "'" +
            "}";
    }
    
}
