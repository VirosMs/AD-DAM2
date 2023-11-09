package org.example;

import org.example.pilotos.OperacionesCRUDPilotos;
import org.example.pilotos.Piloto;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Piloto piloto = new Piloto("HAM", "Lewis", "Hamilton", "1985-01-07", "British", "https://en.wikipedia.org/wiki/Lewis_Hamilton");

//        System.out.println(piloto);
//
        OperacionesCRUDPilotos operacionesCRUDPilotos = new OperacionesCRUDPilotos();
//
//        operacionesCRUDPilotos.crearPiloto(piloto);
//
//        System.out.println(operacionesCRUDPilotos.leerPiloto(2));
//
//        List<Piloto> pilotos = operacionesCRUDPilotos.leerPilotos();
//
//        pilotos.forEach(System.out::println);
//
//
//
//        Piloto p2 = new Piloto(39, "A", "A", "A", "1985-01-07", "British", "https://en.wikipedia.org/wiki/Lewis_Hamilton");
//
//        operacionesCRUDPilotos.actualizarPiloto(p2);
//
//
//        operacionesCRUDPilotos.borrarPiloto(39);

        //operacionesCRUDPilotos.mostrarClasificacionPiloto();

        operacionesCRUDPilotos.mostrarClasificacionConstructores();

    }
}