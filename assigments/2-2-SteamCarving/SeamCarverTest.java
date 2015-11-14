import java.awt.Color;
import edu.princeton.cs.algs4.Picture;

public class SeamCarverTest {

    public static void runTests() {
        testPicture();
        testHeight();
        testWidth();
        testEnergy();
    }

    public static void testPicture() {
        Picture picture = new Picture(200, 200);
        SeamCarver seamCarver = new SeamCarver(picture);
        assert seamCarver.picture() != null;
    }

    public static void testHeight() {
        Picture picture = new Picture(200, 300);
        SeamCarver seamCarver = new SeamCarver(picture);
        assert 300 == seamCarver.height();
    }

    public static void testWidth() {
        Picture picture = new Picture(200, 300);
        SeamCarver seamCarver = new SeamCarver(picture);
        assert 200 == seamCarver.width();
    }

    public static void testEnergy() {
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
        double energy = seamCarver.energy(1, 2);
        assert 228.08770243044668 == energy : energy;
        energy = seamCarver.energy(1, 1);
        assert 228.52789764052878 == energy : energy;
    }
}