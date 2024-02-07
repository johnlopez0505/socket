package examencoches;
import java.util.Objects;

class Coches {
    private int id;
    private String modelo;
    private int cilindrada;
    
    public Coches(int id, String modelo, int cilindrada) {
        this.id = id;        
        this.modelo = modelo; 
        this.cilindrada = cilindrada;  
    }

     
    public Coches(String modelo, int cilindrada) {       
        this.modelo = modelo; 
        this.cilindrada = cilindrada;  
    }


    public Coches() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCilindrada() {
        return this.cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public Coches id(int id) {
        setId(id);
        return this;
    }

    public Coches modelo(String modelo) {
        setModelo(modelo);
        return this;
    }

    public Coches cilindrada(int cilindrada) {
        setCilindrada(cilindrada);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Coches)) {
            return false;
        }
        Coches coches = (Coches) o;
        return id == coches.id && Objects.equals(modelo, coches.modelo) && cilindrada == coches.cilindrada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelo, cilindrada);
    }

    @Override
    public String toString() {
        return "[" +
            " 'id':'" + getId() + "'" +
            ", 'modelo':'" + getModelo() + "'" +
            ", 'cilindrada':'" + getCilindrada() + "'" +
            "]";
    }

    
}