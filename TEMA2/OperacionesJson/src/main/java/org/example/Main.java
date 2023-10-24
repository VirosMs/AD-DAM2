package org.example;

import org.example.libros.Book;
import org.example.utils.OperacionesJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        }};



        OperacionesJson.escribirListaObjetosJson(list, ruta);

        List<Book> books = OperacionesJson.leerListaObjetosJson(ruta);
        System.out.println(books);
    }

    public String menu() throws IOException {
        int opcion = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do{
            System.out.println("1. Añadir libro");
            System.out.println("2. Buscar libro por titulo o autor");
            System.out.println("3. Mostrar todos los libros");
            System.out.println("4. Salir");

            opcion = Integer.parseInt(br.readLine());
        }while(opcion != 4);

        return "";
    }

    public void cases(int opcion){
        switch (opcion) {
            case 1 -> ;
            default ->  System.out.println("Opcion no valida");

        }
    }



}