package org.example;

import org.example.pilotos.OperacionesCRUDPilotos;
import org.example.pilotos.Piloto;

public class Main {
    public static void main(String[] args) {
        Piloto piloto = new Piloto("HAM", "Lewis", "Hamilton", "1985-01-07", "British", "https://en.wikipedia.org/wiki/Lewis_Hamilton");

        System.out.println(piloto);

        OperacionesCRUDPilotos operacionesCRUDPilotos = new OperacionesCRUDPilotos();

        operacionesCRUDPilotos.crearPiloto(piloto);
    }
}