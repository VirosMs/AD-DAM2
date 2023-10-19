package org.example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.libros.Book;

import java.nio.file.Path;
import java.util.List;

public class OperacionesJson {

    public static void escribirListaObjetosJson(List<Book> lista, Path ruta) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(ruta.toFile(), lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Book> leerListaObjetosJson(Path ruta) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ruta.toFile(), new TypeReference<>() { });
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String findBookByTitleOrAuthor(String title, String author, List<Book> books) {
        for(Book book : books) {
            if(book.getTitle().equals(title) || book.getAuthor().equals(author)) {
                return book.getIsbn();
            }
        }
        return "No se ha encontrado el libro";
    }
}
