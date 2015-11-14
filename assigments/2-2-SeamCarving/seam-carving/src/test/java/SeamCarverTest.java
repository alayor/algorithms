import java.awt.Color;
import org.junit.Test;

import static org.junit.Assert.*;
import edu.princeton.cs.algs4.Picture;
import solution.SeamCarver;

public class SeamCarverTest {

    @Test
    public void testPicture(){
        Picture picture = new Picture(200, 200);
        SeamCarver seamCarver = new SeamCarver(picture);
        assertNotNull(seamCarver.picture());
    }

    @Test
    public void testHeight(){
        Picture picture = new Picture(200, 300);
        SeamCarver seamCarver = new SeamCarver(picture);
        assertEquals(300, seamCarver.height());
    }

    @Test
    public void testWidth(){
        Picture picture = new Picture(200, 300);
        SeamCarver seamCarver = new SeamCarver(picture);
        assertEquals(200, seamCarver.width());
    }

    @Test
    public void testEnergy(){
        Picture picture = new Picture(200, 300);
        picture.set(0, 0, new Color(255, 101, 51));
        picture.set(0, 1, new Color(255, 153, 51));
        picture.set(0, 2, new Color(255, 203, 51));
        picture.set(0, 3, new Color(255, 255, 51));

        picture.set(1, 0, new Color(255, 101, 153));
        picture.set(1, 1, new Color(255, 153, 153));
        picture.set(1, 2, new Color(255, 204, 153));
        picture.set(1, 3, new Color(255, 255, 153));

        picture.set(2, 0, new Color(255, 101, 255));
        picture.set(2, 1, new Color(255, 153, 255));
        picture.set(2, 2, new Color(255, 205, 255));
        picture.set(2, 3, new Color(255, 255, 255));

        SeamCarver seamCarver = new SeamCarver(picture);
        assertEquals(228.08, seamCarver.energy(1, 2), 0.01);
        assertEquals(228.52, seamCarver.energy(1, 1), 0.01);
    }
}