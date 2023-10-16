package org.example;


import org.example.enums.Modelo;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;


public class Main {
    private final static String COMMA_DELIMITER = ",";

    public static void main(String[] args) {


        /*Create a collection of funkos*/
        FunkosCollection funkosCollection = new FunkosCollection(Path.of(".", "src", "main","java", "org", "example", "resources", "Funko.csv"));


        //Consults

        /*print the most expensive funko*/
        System.out.println(funkosCollection.findFunkoByPriceReversed());


        /*print the average of the prices*/
        System.out.println("El precio medio es: " + funkosCollection.avg());

        /*print funkos grouped by modelo*/
        funkosCollection.groupFunkosByModelo().forEach((k, v) -> System.out.println(k + " " + v));



        /*print number of funkos by modelo*/
        funkosCollection.groupFunkosByModelo().forEach((k, v) -> System.out.println(k + " " + v.size()));


        /*print funkos by year*/
        funkosCollection.groupFunkosByYear().forEach(System.out::println);


        if (funkosCollection.ser(funkosCollection)) {
            System.out.println("Se ha serializado correctamente");
        } else {
            System.out.println("No se ha serializado correctamente");
        }

        FunkosCollection a = funkosCollection.deser();


    }
}