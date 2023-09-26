import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookCollection {
    // ¿Qué tipo de colección es la más adecuada para almacenar los libros?
    ArrayList<Book> libros = new ArrayList<>();

    public void libPlusPag500() {

        long cantidad = libros.stream().filter(p -> p.pages() > 500).count();

        System.out.println("Pregunta 1: Hay " + cantidad + " libros con mas de 500 paginas.");
    }

    public void lib300Less() {

        long cantidad = libros.stream().filter(p -> p.pages() < 300).count();

        System.out.println("Pregunta 2: Hay " + cantidad + " libros con menos de 300 paginas.");
    }

    public void titlePlus500() {

        System.out.println("Pregunta 3: Titulos de los libros ");
        libros.stream().filter(p -> p.pages() > 500).forEach(p -> System.out.println(p.title()));
    }


    /*libros.sort(new Comparator<Book>() {
           @Override
           public int compare(Book o1, Book o2) {
               return o2.pages() - o1.pages();
           }
       });*/
    public void titleMostPages3() {

        //Expression Lambda


        System.out.println("Pregunta 4: Los titulos de los libros que mas tiene paginas ");
        libros.stream().sorted((o1, o2) -> o2.pages() - o1.pages()).map(Book::title)
                .limit(3).forEach(System.out::println);


    }

    public int sumPages() {
        int sum = 0;
        sum = libros.stream().mapToInt(Book::pages).sum();
        return sum;
    }

    public void aboveAvgPag() {

        System.out.println("Pregunta 6: Todos los libros que tiene mas paginas que la media:");

        libros.stream().filter(p1 -> p1.pages() > libros.stream().collect(Collectors.averagingInt(Book::pages)))
                .map(Book::title)
                .forEach(System.out::println);
    }


    /*HashMap<String, String> name = new HashMap<>();

        for (Book col : libros)
           if (!name.containsKey(col.author()))
               name.put(col.author(), col.title());


        for (Map.Entry<String, String> entry : name.entrySet()){
            System.out.println("Autor: " + entry.getKey());
        }*/
    public void getNameAuthorNR() {
        System.out.println("Pregunta 7: Los Autores de los libros son ");

        libros.stream().map(Book::author).distinct().forEach(System.out::println);

    }

    /* HashMap<String, Integer> name = new HashMap<>();

        int num = 0;

        for (Book col : libros) {
            if (name.containsKey(col.author())) {
                if (name.get(col.author()) == 1) {
                    System.out.println(col.author());
                    num = name.get(col.author()) + 1;
                    name.replace(col.author(), num);
                } else {
                    name.replace(col.author(), num, name.get(col.author()) + 1);
                }
            } else {
                name.put(col.author(), 1);
            }
        }*/
    public void authorPlus1Lib() {
        System.out.println("Pregunta 8: Los Autores con mas de 1 libro");

        Map<String, Long> authorBookCount = libros.stream().collect
                (Collectors.groupingBy(Book::author, Collectors.counting()));

        authorBookCount.entrySet().stream().filter(entry -> entry.getValue() > 1)
                .forEach(entry -> System.out.println(entry.getKey()));

    }

    public void mostPagesLib() {
        System.out.println("Pregunta 9: Obten el libro con mas paginas");
        libros.stream().sorted((o1, o2) -> o2.pages() - o1.pages())
                .limit(1).forEach(b -> System.out.println(b.title()));


    }

    public ArrayList<String> colTitlesLibs() {
        return new ArrayList<>(libros.stream().map(Book::title).toList());
    }

    // Crea los métodos solicitados en el enunciado del ejercicio


}
