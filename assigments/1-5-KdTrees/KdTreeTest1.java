import java.util.Iterator;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeTest1 {
    public static void runTests() {
        testIsEmpty();
        testSize();
        testInsert();
        testContains();
        //testDraw();
        testRange();
        testNearest();
    }

    private static void testIsEmpty() {
        KdTree kdTree = new KdTree();
        assert kdTree.isEmpty() == true;
    }

    private static void testSize() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.0, 0.0));
        kdTree.insert(new Point2D(0.4, 0.5));
        kdTree.insert(new Point2D(0.7, 0.3));
        kdTree.insert(new Point2D(0.2, 0.6));
        kdTree.insert(new Point2D(0.0, 0.4));
        assert kdTree.size() == 5;
    }

    private static void testInsert() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0, 0));
    }

    private static void testContains() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.0, 0.0));
        kdTree.insert(new Point2D(0.4, 0.5));
        kdTree.insert(new Point2D(0.7, 0.3));
        kdTree.insert(new Point2D(0.2, 0.6));
        kdTree.insert(new Point2D(0.0, 0.4));
        assert kdTree.contains(new Point2D(0.0, 0.0));
        assert kdTree.contains(new Point2D(0.4, 0.5));
        assert kdTree.contains(new Point2D(0.7, 0.3));
        assert kdTree.contains(new Point2D(0.2, 0.6));
        assert kdTree.contains(new Point2D(0.0, 0.4));
        assert !kdTree.contains(new Point2D(0.5, 0.5));
        assert !kdTree.contains(new Point2D(0.5, 0.3));
        assert !kdTree.contains(new Point2D(0.5, 0.6));
        assert !kdTree.contains(new Point2D(0.5, 0.4));
    }

    private static void testDraw() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.206107, 0.095492));
        kdTree.insert(new Point2D(0.975528, 0.654508));
        kdTree.insert(new Point2D(0.024472, 0.345492));
        kdTree.insert(new Point2D(0.793893, 0.095492));
        kdTree.insert(new Point2D(0.793893, 0.095492));
        kdTree.insert(new Point2D(0.793893, 0.904508));
        kdTree.insert(new Point2D(0.975528, 0.345492));
        kdTree.insert(new Point2D(0.206107, 0.904508));
        kdTree.insert(new Point2D(0.500000, 0.000000));
        kdTree.insert(new Point2D(0.024472, 0.654508));
        kdTree.insert(new Point2D(0.500000, 1.000000));
        kdTree.draw();
    }

    private static void testRange() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.2, 0.2));
        kdTree.insert(new Point2D(0.2, 0.5));
        Iterator<Point2D> iterator = kdTree.range(new RectHV(0.0, 0.0, 1, 1)).iterator();

        Point2D point2D = iterator.next();
        assert point2D.x() == 0.2 : point2D.x();
        assert point2D.y() == 0.2 : point2D.y();
        point2D = iterator.next();
        assert point2D.x() == 0.2 : point2D.x();
        assert point2D.y() == 0.5 : point2D.y();
        assert iterator.hasNext() == false;

        iterator = kdTree.range(new RectHV(0.0, 0.0, 0.4, 0.4)).iterator();
        point2D = iterator.next();
        assert point2D.x() == 0.2 : point2D.x();
        assert point2D.y() == 0.2 : point2D.y();
        assert iterator.hasNext() == false;
    }

    private static void testNearest() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.8, 0.8));
        kdTree.insert(new Point2D(0.2, 0.5));

        Point2D nearest = kdTree.nearest(new Point2D(0.1, 0.1));
        assert nearest.x() == 0.2;
        assert nearest.y() == 0.5;
    }
}