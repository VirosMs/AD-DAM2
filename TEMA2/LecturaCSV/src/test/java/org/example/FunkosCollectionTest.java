package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FunkosCollectionTest {
    FunkosCollection funkosCollection;
    @BeforeEach
    void setUp() {
        funkosCollection = new FunkosCollection(Path.of(".", "src", "main","java", "org", "example", "resources", "Funko.csv"));
    }
    @ParameterizedTest
    @CsvSource({"2023","2022"})
    void groupFunkosByYear(int year) {
        var fcGrouped = funkosCollection.groupFunkosByYear(year);
        for (Funko f: fcGrouped) {
            assertEquals(year, f.getFechaLazmiento().getYear());
        }
    }

    @Test
    void groupFunkosByModelo() {
    }

    @Test
    void findFunkoByPriceReversed() {
    }

    @Test
    void avg() {
    }

    @Test
    void ser() {
    }

    @Test
    void deser() {
    }
}