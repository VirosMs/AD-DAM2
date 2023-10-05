import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    private final static String COMMA_DELIMITER = ",";

    public static void main(String[] args) {

    /*
        try(Stream<String> contenidoFichero = Files.lines(Path.of(".", "src", "resources", "Funko.csv"))){

            List<Funko> listFun = contenidoFichero.map(l -> Arrays.asList(l.split(COMMA_DELIMITER))).map(f -> new Funko(f.get(0), f.get(1), f.get(2), f.get(3), f.get(4), f.get(5) ));
        }catch (IOException e){

        }*/
    }
}