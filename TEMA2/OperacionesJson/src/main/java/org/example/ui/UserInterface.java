/**
 * Clase UserInterface
 * Clase que contiene los métodos para mostrar el menú y ejecutar las opciones
 *
 * @version 1.0
 * @since 2021-04-12
 * @author Charles Arruda
 */

package org.example.ui;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.libros.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Data
@AllArgsConstructor
public class UserInterface {
    BufferedReader br;

    /**
     * Constructor de la clase UserInterface
     *
     */
    public UserInterface() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Método que muestra el menú
     */
    public void showMenu() {
        System.out.println("1. Añadir libro");
        System.out.println("2. Buscar libro por título o autor");
        System.out.println("3. Mostrar todos los libros");
        System.out.println("4. Salir");
        System.out.print("Introduce una opción: ");
    }

    /**
     * Método que muestra el menú y ejecuta las opciones
     * @param books Lista de libros en la que se va a trabajar
     * @throws IOException Excepción de entrada/salida
     */
    public void menuExecute(List<Book> books) throws IOException {
        int option;
        do {
            showMenu();
            option = Integer.parseInt(br.readLine());
            switch (option) {
                case 1 -> books.add(addBook());

                case 2 -> callFindBook(books);

                case 3 -> books.forEach(System.out::println);

                case 4 -> System.out.println("Adiós");

                default -> System.out.println("Opción no válida");
            }
        } while (option != 4);
    }

    /**
     * Método que pide un parámetro de búsqueda y llama al método findBookByTitleOrAuthor
     * de la clase Book
     * @see Book#findBookByTitleOrAuthor(String, List)
     * @param books Lista de libros en la que se va a buscar
     * @throws IOException Excepción de entrada/salida
     */
    public void callFindBook(List<Book> books)throws IOException{
        System.out.print("Introduce el título o autor a buscar: ");
        String buscar = br.readLine();
        System.out.println(Book.findBookByTitleOrAuthor(buscar, books));

    }

    /**
     * Método que pide los datos de un libro por consola y devuelve un objeto Book
     * @return Book Objeto Book con los datos introducidos por consola
     * @throws IOException Excepción de entrada/salida
     */
    public Book addBook() throws IOException {
        System.out.print("Introduce el ISBN: ");
        String isbn = br.readLine();
        System.out.print("Introduce el título: ");
        String title = br.readLine();
        System.out.print("Introduce el autor: ");
        String author = br.readLine();
        System.out.print("Introduce el número de páginas: ");
        int pages = Integer.parseInt(br.readLine());
        System.out.print("Introduce el año de publicación: ");
        int year = Integer.parseInt(br.readLine());


        return new Book(isbn, title, author, pages, year);
    }
}
