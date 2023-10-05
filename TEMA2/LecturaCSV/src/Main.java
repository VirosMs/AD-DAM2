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


        try(Stream<String> contenidoFichero = Files.lines(Path.of(".", "src", "resources", "Funko.csv"))){

            List<Funko> listFun = contenidoFichero.map(l -> Arrays.asList(l.split(COMMA_DELIMITER))).skip(1).map(Funko::new).toList();
        }catch (IOException e) {

        }
    }
}