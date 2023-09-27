package org.example.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public record BookCollection(List<Book> books) {
    public List<Book> find(String textToFind) {
        List<Book> foundBooks = new ArrayList<>();
        if (!books.isEmpty()){
            for (Book book : this.books) {
                if (book.isbn().equals(textToFind) ||
                        book.title().contains(textToFind) ||
                        book.author().contains(textToFind)) {
                    foundBooks.add(book);
                }
            }
        }

        return foundBooks;
    }

    public List<Book> findOrFail(String textToFind)throws NoSuchElementException{
        if (books.isEmpty()){
            throw new NoSuchElementException("CollectionIsEmpty");
        }

        List<Book> matchingBooks  = books.stream()
                .filter(book -> book.isbn().equals(textToFind) ||
                                book.title().equals(textToFind) ||
                                book.author().equals(textToFind))
                .toList();

        if (matchingBooks.isEmpty()){
            throw new NoSuchElementException("NoBooksFound");
        }

        return matchingBooks;
    }


}
