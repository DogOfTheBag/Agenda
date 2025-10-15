package data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Agenda {
    Set<String> dnis;
    HashMap<String,Persona> personas;

    public Agenda() {
        personas = new HashMap();
        dnis = new HashSet<>();
    }

    public boolean agregar(String dni, String nombre, long telefono) throws Exception {
        personas.put(dni,new Persona(dni, nombre, telefono));
        if(dnis.add(dni)){
            return true;
        }
        throw new Exception("Error al introducir a la persona, datos erróneos o ya hay un DNI igual en la agenda");
    }
//deberia hacer que lance excepciones esto
    public boolean eliminar(String dni) throws Exception {
        for (Persona p : personas.values()) {
            if (p.getDni().equalsIgnoreCase(dni)){
                personas.remove(dni);
                return true;
            }
        }
        throw new Exception("DNI erroneo o no existe la persona");
    }

    public Persona recuperar(String dni) throws Exception {
            for (Persona p : personas.values()) {
                if(p.getDni().equalsIgnoreCase(dni)){
                    return p;
                }
            }
        throw new Exception("No se ha encontrado una persona con este DNI");
    }

    @Override
    public String toString() {
        // que no se me olvide la existencia de StringBuilder que hace las cadenas mucho mejor que el + y ya
        StringBuilder sb = new StringBuilder("En la agenda tenemos las siguientes personas:\n");
        /*Lo nuevo a tener en cuenta de los hashmaps en el fore es que no le podemos pasar directamente personas
        * debido a que como el mapa como tal tiene clave valor, le pasarías ambas
        * de modo que si le quieres pasar el contenido, le pasas values y se queda como de normal*/
        for (Persona p : personas.values()) {
            sb.append(p);

        }
        return sb.toString();
    }
}
