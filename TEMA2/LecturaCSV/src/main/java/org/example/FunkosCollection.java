package org.example;


import org.example.enums.Modelo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunkosCollection implements Serializable {


    private static List<Funko> listFun = new ArrayList<>();

    private final static String COMMA_DELIMITER = ",";

    public FunkosCollection(Path path) {
        if (path == null) {
            throw new NullPointerException("Path cannot be null");
        }

        try (Stream<String> contenidoFichero = Files.lines(path)) {
            listFun = contenidoFichero.map(l -> Arrays.asList(l.split(COMMA_DELIMITER))).skip(1).map(Funko::new).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*print funkos by year*/
    public List<Funko> groupFunkosByYear() {
        return listFun.stream().filter(f -> f.getFechaLazmiento().getYear() == 2023).toList();
    }


    public HashMap<Modelo, List<Funko>> groupFunkosByModelo() {
        return (HashMap<Modelo, List<Funko>>) listFun.stream().collect(Collectors.groupingBy(Funko::getModelo));
    }

    /**
     * This method is used to find the most expensive funko
     *
     * @return Optional<Funko>
     */
    public Optional<Funko> findFunkoByPriceReversed() {
        listFun = listFun.stream().sorted(Comparator.comparing(Funko::getPrecio).reversed()).toList();
        return listFun.stream().findFirst();
    }

    /**
     * This method is used to calculate the average of the prices
     *
     * @return avg rounded to 2 decimals
     */
    public double avg() {
        double avg = listFun.stream().mapToDouble(Funko::getPrecio).average().orElse(0.0);

        return Math.round(avg * 100.0d) / 100.0d;
    }


    /**
     * This method is used to serialize the object
     *
     * @param funkosCollection FunkosCollection
     * @return boolean
     */
    public boolean ser(FunkosCollection funkosCollection) {
        boolean ser = false;
        try (FileOutputStream fos = new FileOutputStream(Path.of(".", "src", "main", "resources", "funkos.dat").toString());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(funkosCollection);
            ser = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ser;
    }

    /**
     * This method is used to deserialize the object
     *
     * @return FunkosCollection
     */
    public FunkosCollection deser() {
        FunkosCollection funkos = null;
        try (FileInputStream fis = new FileInputStream(Path.of(".", "src", "main", "resources", "funkos.dat").toString());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            funkos = ((FunkosCollection) ois.readObject());
            if (funkos == null)
                throw new NullPointerException("El objeto no puede ser null");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return funkos;
    }


}
