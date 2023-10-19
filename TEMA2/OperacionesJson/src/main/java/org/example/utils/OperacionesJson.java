package org.example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.libros.Book;

import java.nio.file.Path;
import java.util.List;
/**
 * Clase que contiene los métodos para escribir y leer una lista de objetos en un fichero json
 *
 * @version 1.0
 * @since 2021-04-12
 * @author Charles Arruda
 *
 */
public class OperacionesJson {

    /**
     * Método que escribe una lista de objetos en un fichero json
     * @param lista Lista de objetos
     * @param ruta Ruta del fichero json
     */
    public static void escribirListaObjetosJson(List<Book> lista, Path ruta) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(ruta.toFile(), lista);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Método que lee una lista de objetos de un fichero json
     * @param ruta Ruta del fichero json
     * @return Devuelve la lista de objetos
     */
    public static List<Book> leerListaObjetosJson(Path ruta) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ruta.toFile(), new TypeReference<>() { });
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
