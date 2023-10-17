package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;

import java.nio.file.Path;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FunkosCollectionTest {
    @Mock
    FunkosCollection funkosCollection;


    @BeforeEach
    void setUp() {
        funkosCollection = mock(FunkosCollection.class);
    }
    @ParameterizedTest
    @CsvSource({"2023","2022","2010"})
    void testGroupFunkosByYear(int year) {
        //when
        when(funkosCollection.groupFunkosByYear(year)).thenReturn(Collections.emptyList());

        var fcGrouped = funkosCollection.groupFunkosByYear(year);

        verify(funkosCollection, times(1)).groupFunkosByYear(year);

        // Realiza las aserciones de tus resultados
        for (Funko f: fcGrouped) {
            assertAll("f",
                    () -> assertNotNull(f.getFechaLazmiento()),
                    () -> assertEquals(year, f.getFechaLazmiento().getYear()));
        }
    }

    @Test
    void groupFunkosByModelo() {


        //when(funkosCollection.groupFunkosByModelo()).thenReturn(Collections.emptyMap());
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