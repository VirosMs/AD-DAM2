package main;

import main.enums.Modelo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private final static String COMMA_DELIMITER = ",";

    public static void main(String[] args) {


        //List<Funko> listFun = new ArrayList<>();
        List<Funko> listFun = null;

        //Creamos un stream para leer el fichero
        try (Stream<String> contenidoFichero = Files.lines(Path.of(".", "src", "main", "resources", "Funko.csv"))) {

            //Creamos una lista de Funko a partir del stream
             listFun = contenidoFichero.map(l -> Arrays.asList(l.split(COMMA_DELIMITER))).skip(1).map(Funko::new).toList();

            //Ordenamos la lista por precio de mayor a menor
             listFun = listFun.stream().sorted(Comparator.comparing(Funko::getPrecio).reversed()).toList();


        } catch (IOException e) {
            e.printStackTrace();
        }

        //Comprobamos que la lista no sea null
        if(listFun == null)
            throw new NullPointerException("La lista no puede ser null");

        //Imprimimos el Funko mas caro
        System.out.println("El funko mas caro es: "+ listFun.stream().findFirst());

        //Creamos un HashMap con los funkos agrupados por modelo
        Map<Modelo, List<Funko>> groupModelo = separarPorModelo(listFun);

        //Imprimimos los funkos agrupados por modelo
        System.out.println("Funkos agrupados por modelo:\n"+ groupModelo);

        //Imprimimos la media de los precios
        System.out.println("El precio medio es: " + avg(listFun));

        //Imprimimos el numero de funkos por modelo
        listFun.stream().collect(Collectors.groupingBy(Funko::getModelo)).forEach((k, v) -> System.out.println(k + " " + v.size()));

        //Imprimimos los funkos que salen en 2023
        listFun.stream().filter(f -> f.getFechaLazmiento().getYear() == 2023).forEach(System.out::println);




    }

    /**
     * This method is used to calculate the average of the prices
     *
     * @param listFun List of Funko
     * @return avg rounded to 2 decimals
     */
    public static double avg(List<Funko> listFun){
        if (listFun == null)
            throw new NullPointerException("La lista no puede ser null");

        double avg = listFun.stream().mapToDouble(Funko::getPrecio).average().orElse(0.0);

        return  Math.round(avg * 100.0d) / 100.0d;
    }

    /**
     * This method is used to separate the funkos by model
     *
     * @param listFun List of Funko
     * @return HashMap<Modelo, Funko>
     */
    protected static HashMap<Modelo, List<Funko>> separarPorModelo(List<Funko> listFun) {
        if (listFun == null)
            throw new NullPointerException("La lista no puede ser null");

        HashMap<Modelo, List<Funko>> map = new HashMap<>();

       listFun.stream().collect(Collectors.groupingBy(Funko::getModelo)).forEach((k, v) -> map.put(k, v.stream().toList()));

        return map;
    }
}