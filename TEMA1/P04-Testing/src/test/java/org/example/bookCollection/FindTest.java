package org.example.bookCollection;

import org.example.entidades.Book;
import org.example.entidades.BookCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class FindTest {

    BookCollection books;

    @BeforeEach
    void setUp() {
        books = new BookCollection( new ArrayList<>());
    }

    @Test
    @DisplayName("should Find A Book By Isbn")
    public void shouldFindABookByIsbn()
    {
        final String isbnToLocate = "un-isbn-2";
        books = new BookCollection(List.of(new Book[]{
                new Book("un-isbn-1", "un titulo 1", "un autor 1", 100),
                new Book(isbnToLocate, "un titulo 2", "un autor 2", 200),
                new Book("un-isbn-3", "un titulo 3", "un autor 3", 300),
        }));
        books.find(isbnToLocate).forEach((Book book) -> assertEquals(isbnToLocate, book.isbn()));
    }

    @Test
    @DisplayName("should Not Find If Collections Empty")
    public void shouldNotFindIfCollectionsEmpty(){
        assertTrue(books.find("Tonto").isEmpty());
    }

    @Test
    @DisplayName("should Get An Empty List If No Matches Are Found")
    public void shouldGetAnEmptyListIfNoMatchesAreFound(){
        final String isbnToLocate = "un-isbn-5";
        books = new BookCollection(List.of(new Book[]{
                new Book("un-isbn-1", "un titulo 1", "un autor 1", 100),
                new Book("hahahah", "un titulo 2", "un autor 2", 200),
                new Book("un-isbn-3", "un titulo 3", "un autor 3", 300),
        }));

       assertTrue(books.find(isbnToLocate).isEmpty());
    }

    @Test
    @DisplayName("should Get Exception When Using Find Or With AnEmpty Collection")
    public void shouldGetExceptionWhenUsingFindOrWithAnEmptyCollection() {
        final String isbnToLocate = "un-isbn-5";
        assertThrows(NoSuchElementException.class,() -> books.findOrFail(isbnToLocate));
    }


    @Test
    @DisplayName("should Get Exception When Using FindOrFail With An Empty Collection")
    public void shouldGetExceptionWhenUsingFindOrFailWithAnEmptyCollection(){
        final String isbnToLocate = "un-isbn-5";
        books = new BookCollection(List.of(new Book[]{
                new Book("un-isbn-1", "un titulo 1", "un autor 1", 100),
                new Book("hahahah", "un titulo 2", "un autor 2", 200),
                new Book("un-isbn-3", "un titulo 3", "un autor 3", 300),
        }));

        assertThrows(NoSuchElementException.class,() -> books.findOrFail(isbnToLocate));
    }
}
