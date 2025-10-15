package data;



import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Agenda {
    //he puesto el Set de DNI para que cuando te lo pasen no deje meter ni un repetido
    Set<String> dnis;
    HashMap<String,Persona> personas;


    public Agenda() {
        personas = new HashMap();
        dnis = new HashSet<>();
    }

    /*IMPORTANTE: CON LA CARGA Y EL GUARDADO MANEJAMOS BYTES, POR LO QUE TENEMOS QUE HACER SERIALIZABLE
    * LA CLASE PERSONA, DE FORMA QUE PUEDAS PASAR ESA INFORMACION A BYTES Y QUE EL ARCHIVO FUNCIONE.*/

    public void cargarDatosDeArchivo(){
        //me he puesto a usar java.nio en vez de io para probarlo
        //le tengo que poner res primero y luego el nombre del archivo debido a que esta en la carpeta res y no en la misma
        Path ruta = Path.of("res","personas.dat");

        //comprobamos que exista el archivo primero y si no salimos
        if(!Files.exists(ruta)){
            System.out.println("No existe el archivo");
            return;
        }
        /*Usaré un try with resources, que cierra automaticamente el flujo, de modo que nos quitamos lios de finally*/
        /*Voy a hacer un try with resources para evitar usar un finally y tener que cerrar el flujo
        * la cosa de esto basicamente es que mientras el inputStream reciba cosas, ira convirtiendo los objetos que pille
        * en personas, y las agregue a la agenda*/
        try (ObjectInputStream is = new ObjectInputStream(Files.newInputStream(ruta))){
            while(true) {
                Persona p = (Persona) is.readObject();
                personas.put(p.getDni(), p);
            }
            //importante end of file para que pille cuando ya hayas metido todo y salga de aqui
        }catch(EOFException e){
                System.out.println("Todos los datos cargados correctamente.");
            }
         catch(IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /* El principio del guardar es parecido al cargar, path y comprobar si existe el archivo*/
    public void guardarDatosEnArchivo(){
        Path ruta = Path.of("res", "personas.dat");

        if(!Files.exists(ruta)){
            System.out.println("No existe el archivo");
            return;
        }
            /*El guardar es mas facil, cogemos un for each, y los valores del hashMap los ponemos en forma de byte con
            * el ObjectOutputStream*/
        try(ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(ruta))){
            for (Persona p : personas.values()) {
                os.writeObject(p);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        /*Para evitar repetidos usamos el Set de DNI, de forma que le pases tu el DNI, comprueba si hay ese DNI en el set
        * y si lo hay no te agrega la persona automaticamente.*/
    public boolean agregar(String dni, String nombre, long telefono) throws Exception {
        personas.put(dni,new Persona(dni, nombre, telefono));
        if(dnis.add(dni)){
            return true;
        }
        throw new Exception("Error al introducir a la persona, datos erróneos o ya hay un DNI igual en la agenda");
    }

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
