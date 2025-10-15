package data;

public class Main {
    public static void main(String[] args) {
/*
        Persona persona = new Persona("51006644G", "Alberto Cortijo", 640069976);

        System.out.println(persona);*/
        Agenda agenda = new Agenda();

        System.out.println(agenda.agregar("1", "Pepe", 444444444));

        agenda.agregar("22334455D", "Alberto Cortijo", 223344556);
        agenda.agregar("11223344G", "David Garcia", 112233445);
        System.out.println(agenda);
        System.out.println(agenda.eliminar("12"));
        System.out.println(agenda.eliminar("1"));
        System.out.println(agenda);
    }
}