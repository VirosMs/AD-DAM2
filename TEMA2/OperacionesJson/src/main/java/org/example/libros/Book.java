package org.example.libros;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private  String isbn;
    private  String title;
    private  String author;
    private int numeroPaginas;
    private int anioPublicacion;
}
