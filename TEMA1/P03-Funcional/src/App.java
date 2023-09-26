import java.util.Comparator;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class App {
    public static void main(String[] args) {

        BookCollection collection = new BookCollection();

        collection.libros.add(new Book("9788422616337", "El Señor de los Anillos", "J.R.R. Tolkien", 800));
        collection.libros.add(new Book("9788445077528", "El Hobbit", "J.R.R. Tolkien", 350));
        collection.libros.add(new Book("9788466316781", "Cabo Trafalgar", "Arturo Pérez Reverte", 320));
        collection.libros.add(new Book("9788493975074", "El corazón de la piedra", "José María García López", 560));
        collection.libros.add(new Book("9788493291488", "Salmos de vísperas", "Esteban Hernández Castelló", 95));
        collection.libros.add(new Book("9788420685625", "La música en las catedrales españolas del Siglo de Oro", "Robert Stevenson", 600));
        collection.libros.add(new Book("9788423913077", "Luces de bohemia", "Ramón del Valle-Inclán", 296));
        collection.libros.add(new Book("9788448031121", "Contando atardeceres", "La vecina rubia", 528));
        collection.libros.add(new Book("9781529342079", "The Master: The Brilliant Career of Roger Federer", "Christopher Clarey", 456));
        collection.libros.add(new Book("9788408264385", "La teoría de los archipiélagos", "Alice Kellen", 300));
        collection.libros.add(new Book("9788423362479", "Esperando al diluvio", "Dolores Redondo", 576));
        collection.libros.add(new Book("9788466367349", "El italiano", "Arturo Pérez Reverte", 400));
        collection.libros.add(new Book("9788466359290", "Línea de fuego", "Arturo Pérez Reverte", 688));

        System.out.println("---------------------------");
        collection.libPlusPag500();

        System.out.println("---------------------------");
        collection.lib300Less();

        System.out.println("---------------------------");
        collection.titlePlus500();

        System.out.println("---------------------------");
        collection.titleMostPages3();

        System.out.println("---------------------------");
        System.out.println("Pregunta 5: La suma de las paginas es de  " + collection.sumPages());

        System.out.println("---------------------------");
        collection.aboveAvgPag();

        System.out.println("---------------------------");
        collection.getNameAuthorNR();

        System.out.println("---------------------------");
        collection.authorPlus1Lib();

        System.out.println("---------------------------");
        collection.mostPagesLib();

        System.out.println("---------------------------");
        System.out.println("Pregunta 10: Obten una coleccion con todos los titulos");
        for (String col : collection.colTitlesLibs())
            System.out.println(col);

        System.out.println("---------------------------");

        //Comparators

        System.out.println("Pregunta 11: Ordenar los libros A - Z");
        //collection.libros.sort((o1, o2) -> o1.title().compareTo(o2.title()));
        collection.libros.sort(Book::compareTo);


        for (Book b : collection.libros){
            System.out.println(b.title());
        }


    }
}