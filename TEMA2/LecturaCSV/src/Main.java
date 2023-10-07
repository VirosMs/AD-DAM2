import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private final static String COMMA_DELIMITER = ",";

    public static void main(String[] args) {



        List<Funko> listFun = null;
        try (Stream<String> contenidoFichero = Files.lines(Path.of(".", "src", "resources", "Funko.csv"))) {

             listFun = contenidoFichero.map(l -> Arrays.asList(l.split(COMMA_DELIMITER))).skip(1).map(Funko::new).toList();


             listFun = listFun.stream().sorted(Comparator.comparing(Funko::getPrecio).reversed()).toList();


        } catch (IOException e) {
            e.printStackTrace();
        }

        if(listFun == null)
            throw new NullPointerException("La lista no puede ser null");

        System.out.println("El funko mas caro es: "+ listFun.stream().findFirst());

        System.out.println("El precio medio es: " + avg(listFun));
    }

    public static double avg(List<Funko> listFun){
        double avg = listFun.stream().mapToDouble(Funko::getPrecio).average().orElse(0.0);

        return  Math.round(avg * 100.0d) / 100.0d;
    }
}