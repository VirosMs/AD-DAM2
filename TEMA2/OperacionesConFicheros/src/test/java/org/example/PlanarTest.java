package org.example;



import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



public class PlanarTest {


    @Test
    public void testPlanar() {
        Planar planar = Mockito.mock(Planar.class);
        planar.planar();
        Mockito.verify(planar).planar();
    }
}

