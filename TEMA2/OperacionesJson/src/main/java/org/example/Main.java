package org.example;

import org.example.libros.Book;
import org.example.ui.UserInterface;
import org.example.utils.OperacionesJson;


import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Path ruta = Path.of(".", "src", "main", "resources", "books.json");
        List<Book> list = new ArrayList<>() {{
            add(new Book("1234567890123", "El Quijote", "Cervantes", 1000, 1605));
            add(new Book("1234567890124", "El Quijote 2", "Cervantes", 1000, 1605));
            add(new Book("1234567890125", "El Quijote 3", "Cervantes", 1000, 1605));
            add(new Book("1234567890126", "Monte Cristo", "Dumas", 1000, 1605));
        }};


        OperacionesJson.escribirListaObjetosJson(list, ruta);

        List<Book> books = OperacionesJson.leerListaObjetosJson(ruta);

        UserInterface ui = new UserInterface();

        try {
            ui.menuExecute(books);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        OperacionesJson.escribirListaObjetosJson(list, ruta);

    }


}