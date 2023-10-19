package org.example.libros;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Clase Book (Libro)
 * Clase que contiene los atributos de un libro y un método para buscar un libro por título o autor
 *
 * @version 1.0
 * @since 2021-04-12
 * @author Charles Arruda
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private  String isbn;
    private  String title;
    private  String author;
    private int numeroPaginas;
    private int anioPublicacion;

    /**
     * Método que busca un libro por título o autor
     * @param par Título o autor del libro
     * @param books Lista de libros en la que se va a buscar
     * @return Devuelve el libro si lo encuentra, si no, devuelve un mensaje de error
     */
    public static String findBookByTitleOrAuthor(String par, List<Book> books) {
        for(Book book : books) {
            if(book.getTitle().equals(par) || book.getAuthor().equals(par)) {
                return book.toString();
            }
        }
        return "No se ha encontrado el libro";
    }
}
