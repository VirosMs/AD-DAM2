package org.example;



import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



public class PlanarTest {


    /**
     * Test method for {@link org.example.Planar#planar()}.
     * This test is used to test the method planar() in the class Planar.
     *
     * @see org.example.Planar#planar()
     */
    @Test
    public void testPlanar() {
        Planar planar = Mockito.mock(Planar.class);
        planar.planar();
        Mockito.verify(planar).planar();
    }
}

