package org.example;

import org.example.enums.Modelo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class FunkoTest {

    @DisplayName("Si el constructor recibe un funko bien formado, no debe lanzar excepciones")
    @Test
    public void testConstructorCorrect() {
        List<String> lista = Arrays.asList("123e4567-e89b-12d3-a456-426655440000", "Funko Pop! Naruto Shippuden - Naruto Uzumaki", "ANIME", "12.99", "2009-01-10");
        Funko funko = new Funko(lista);

        assertAll("funko",
                () -> assertEquals("123e4567-e89b-12d3-a456-426655440000", funko.getCod().toString()),
                () -> assertEquals("Funko Pop! Naruto Shippuden - Naruto Uzumaki", funko.getNombre()),
                () -> assertEquals(Modelo.ANIME, funko.getModelo()),
                () -> assertEquals(12.99, funko.getPrecio()),
                () -> assertEquals(LocalDate.of(2009, 1, 10), funko.getFechaLazmiento())
        );
    }

    @DisplayName("Si el constructor recibe una lista nula, debe lanzar NullPointerException")
    @Test
    void testConstructorWhenListIsNull() {
        List<String> lista = null;
        assertThrows(NullPointerException.class, () -> new Funko(lista));
    }
    @DisplayName("Si el constructor recibe una lista diferente de 5 elementos, debe lanzar IllegalArgumentException")
    @Test
    void testConstructorWhenListHasNotFiveElements() {
        List<String> lista = Arrays.asList("123e4567-e89b-12d3-a456-426655440000", "Funko Pop! Naruto Shippuden - Naruto Uzumaki", "ANIME", "12.99");
        assertThrows(IllegalArgumentException.class, () -> new Funko(lista));
    }


    @DisplayName("Test para comprobar que el devuelve modelo correcto")
    @Test
    void getModeloIsCorrect() {
        var funko = new Funko(Arrays.asList("123e4567-e89b-12d3-a456-426655440000", "Funko Pop! Naruto Shippuden - Naruto Uzumaki", "ANIME", "12.99", "2009-01-10"));

        assertEquals(Modelo.ANIME, funko.getModelo());
    }

    @DisplayName("Test para comprobar que el devuelve modelo incorrecto")
    @Test
    void getModeloIsNotCorrect() {
        var funko = new Funko(Arrays.asList("123e4567-e89b-12d3-a456-426655440000", "Funko Pop! Naruto Shippuden - Naruto Uzumaki", "ANIME", "12.99", "2009-01-10"));

        assertNotEquals(Modelo.DISNEY, funko.getModelo());
    }

    @DisplayName("Test para comprobar que el OTROS si el modelo null")
    @Test
    void getModeloIsNull() {
        var funko = new Funko(Arrays.asList("123e4567-e89b-12d3-a456-426655440000", "Funko Pop! Naruto Shippuden - Naruto Uzumaki", null, "12.99", "2009-01-10"));

        assertEquals(Modelo.OTROS, funko.getModelo());
    }

    @DisplayName("Comprobando si el formato del precio es correcto")
    @Test
    void testConvertDoubleWhenFormatNumberIsCorrect() {
        var funko = new Funko(Arrays.asList("123e4567-e89b-12d3-a456-426655440000", "Funko Pop! Naruto Shippuden - Naruto Uzumaki", "ANIME", "12.99", "2009-01-10"));
        double pre1Expected = 12.99;

        assertEquals(pre1Expected, funko.getPrecio());
    }

}