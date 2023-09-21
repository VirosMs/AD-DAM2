import java.util.*;
import java.util.stream.Collectors;

public class BookCollection {
    // ¿Qué tipo de colección es la más adecuada para almacenar los libros?
    ArrayList<Book> libros = new ArrayList<>();

    public void libPlusPag500() {

        int cantidad = 0;
        for (Book col : libros) {
            if (col.pages() > 500)
                cantidad++;
        }

        System.out.println("Pregunta 1: Hay " + cantidad + " libros con mas de 500 paginas.");
    }

    public void lib300Less() {

        int cantidad = 0;
        for (Book col : libros) {
            if (col.pages() < 300)
                cantidad++;
        }

        System.out.println("Pregunta 2: Hay " + cantidad + " libros con menos de 300 paginas.");
    }

    public void titlePlus500() {

        System.out.println("Pregunta 3: Titulos de los libros ");
        for (Book col : libros) {
            if (col.pages() > 500)
                System.out.println(col.title());
        }
    }


    /*libros.sort(new Comparator<Book>() {
           @Override
           public int compare(Book o1, Book o2) {
               return o2.pages() - o1.pages();
           }
       });*/
    public void titleMostPages3() {

        //Expression Lambda
        libros.sort((o1, o2) -> o2.pages() - o1.pages());

        System.out.println("Pregunta 4: Los titulos de los libros que mas tiene paginas ");
        for (int i = 0; i < 3; i++) {
            System.out.println(libros.get(i).title());
        }

    }

    public int sumPages() {

        int sum = 0;
        for (Book col : libros) {
            sum += col.pages();
        }
        return sum;
    }

    public void aboveAvgPag() {

        System.out.println("Pregunta 6: Todos los libros que tiene mas paginas que la media:");

        int avg = sumPages() / libros.size();

        for (Book col : libros)
            if (col.pages() > avg)
                System.out.println(col.title());
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


        HashSet<String> names = new HashSet<>();

        for (Book col : libros)
            names.add(col.author());

        for (String author : names)
            System.out.println("Autor: " + author);
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
        libros.sort((o1, o2) -> o2.pages() - o1.pages());
        System.out.println(libros.get(0).title());
    }

    public ArrayList<String> colTitlesLibs() {
        ArrayList<String> titles = new ArrayList<>();

        for (Book col : libros)
            titles.add(col.title());

        return titles;
    }

    // Crea los métodos solicitados en el enunciado del ejercicio



}
