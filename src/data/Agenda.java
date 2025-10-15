package data;

import java.util.HashMap;
import java.util.Set;

public class Agenda {
    Set<String> dnis;
    HashMap<String,Persona> personas;

    public Agenda() {
        personas = new HashMap();
    }

    public boolean agregar(String dni, String nombre, long telefono){
        try{
            personas.put(dni,new Persona(dni, nombre, telefono));
            return true;

        }catch (Exception e){
            System.out.println("Error al guardar persona: " + e.getMessage());
        }
        return false;
    }

    public boolean eliminar(String dni){
        for (Persona p : personas.values()) {
            if (p.getDni().equalsIgnoreCase(dni)){
                personas.remove(dni);
                return true;
            }
        }
        return false;
    }

    public Persona recuperar(String dni){
        for (Persona p : personas.values()) {
            if(p.getDni().equalsIgnoreCase(dni)){
                return p;
            }
        }
        return

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("En la agenda tenemos las siguientes personas:\n");
        /*Lo nuevo a tener en cuenta de los hashmaps en el fore es que no le podemos pasar directamente personas
        * debido a que como el mapa como tal tiene clave valor, le pasarias ambas
        * de modo que si le quieres pasar el contenido, le pasas values y se queda como de normal*/
        for (Persona p : personas.values()) {
            sb.append(p);

        }
        return sb.toString();
    }
}
