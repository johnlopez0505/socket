package examencoches;


import java.util.ArrayList;
import java.util.List;

class ControlVehiculos {
    private ArrayList <Coches> listaDeCoches = new ArrayList<>();
    private  int contadorIds = 1;

    public ControlVehiculos() {
    }
    
    public ControlVehiculos(ArrayList<Coches> listaDeCoches) {
        this.listaDeCoches = listaDeCoches;
    }

    public synchronized ArrayList<Coches> getListaDeCoches() {
        return this.listaDeCoches;
    }

    public synchronized void setListaDeCoches(ArrayList<Coches> listaDeCoches) {
        this.listaDeCoches = listaDeCoches;
    }

    public synchronized ControlVehiculos listaDeCoches(ArrayList<Coches> listaDeCoches) {
        setListaDeCoches(listaDeCoches);
        return this;
    }

   

    @Override
    public String toString() {
        return "{" +
            " listaDeCoches='" + getListaDeCoches() + "'" +
            "}";
    }
    

    public synchronized Coches obtenerCochePorId(int id) {
        // Lógica para obtener un coche por ID
        Coches  cocheEncontrado = null;
        for (Coches coche : listaDeCoches) {
            if (coche.getId() == id) {
                cocheEncontrado = new Coches(coche.getId(),coche.getModelo(),coche.getCilindrada());
            }
        }
        return cocheEncontrado;
        
    }

    public synchronized Coches agregarCoche(String modelo, int cilindrada) {
        // Lógica para agregar un coche a la lista
        Coches nuevoCoche = new Coches(contadorIds, modelo, cilindrada);
        contadorIds += 1;
        listaDeCoches.add(nuevoCoche);
        return nuevoCoche;
    }

    public synchronized List<Integer> obtenerTodosLosIds() {
        List<Integer> todosLosIds = new ArrayList<>();
        for (Coches coche : listaDeCoches) {
            todosLosIds.add(coche.getId());
        }
        return todosLosIds;
    }

    public synchronized Coches actualizarCoche(int id, String modelo, int cilindrada) {
        Coches cocheEditado = new Coches();
        for (Coches coche : listaDeCoches) {
            if (coche.getId() == (id)) {
                coche.setModelo(modelo);
                coche.setCilindrada(cilindrada);
                cocheEditado = coche;
            }
        }
        return cocheEditado;
    }

    public synchronized Coches eliminarCoche(int id) {
        Coches cocheBorrado = new Coches();
        for (Coches coche : listaDeCoches) {
            if (coche.getId() == id) {
                cocheBorrado = coche;
            }
        }
        listaDeCoches.remove(cocheBorrado);
        return cocheBorrado;
    }
}
    
