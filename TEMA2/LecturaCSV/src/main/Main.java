package main;


import main.enums.Modelo;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;


public class Main {
    private final static String COMMA_DELIMITER = ",";

    public static void main(String[] args) {


        /*Create a collection of funkos*/
       FunkosCollection funkosCollection = new FunkosCollection(Path.of(".", "src", "main", "resources", "Funko.csv"));

       /*Create a map with the funkos grouped by modelo*/
        HashMap<Modelo, List<Funko> > mapModelo = funkosCollection.separarPorModelo();

        //Consults

        /*print the most expensive funko*/
        System.out.println(funkosCollection.findFunkoByPriceReversed());


        /*print the average of the prices*/
       System.out.println("El precio medio es: " + funkosCollection.avg());

        /*print funkos grouped by modelo*/
       System.out.println("Funkos agrupados por modelo:\n"+ mapModelo);



        /*print number of funkos by modelo*/
        funkosCollection.numberOfFunkosByModelo();


        /*print funkos by year*/
        funkosCollection.printFunkosByYear();

        if(funkosCollection.ser(funkosCollection)){
            System.out.println("Se ha serializado correctamente");
        }else{
            System.out.println("No se ha serializado correctamente");
        }

        FunkosCollection a = funkosCollection.deser();


    }
}