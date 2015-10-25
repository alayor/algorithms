import java.util.Iterator;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSETTest {
    public static void runTests() {
        testIsEmpty();
        testSize();
        testInsert();
        testContains();
        testDraw();
        testRange();
        testNearest();
    }

    private static void testIsEmpty() {
        PointSET pointSET = new PointSET();
        assert pointSET.isEmpty() == true;
    }

    private static void testSize() {
        PointSET pointSET = new PointSET();
        assert pointSET.size() == 0;
    }

    private static void testInsert() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0, 0));
    }

    private static void testContains() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0, 0));
        assert pointSET.contains(new Point2D(0, 0));
    }

    private static void testDraw() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.4, 0.9));
        pointSET.insert(new Point2D(0.2, 0.5));
//        pointSET.draw();
    }

    private static void testRange() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.2, 0.2));
        pointSET.insert(new Point2D(0.2, 0.5));
        Iterator<Point2D> iterator = pointSET.range(new RectHV(0.0, 0.0, 1, 1)).iterator();

        Point2D point2D = iterator.next();
        assert point2D.x() == 0.2 : point2D.x();
        assert point2D.y() == 0.2 : point2D.y();
        point2D = iterator.next();
        assert point2D.x() == 0.2 : point2D.x();
        assert point2D.y() == 0.5 : point2D.y();
        assert iterator.hasNext() == false;

        iterator = pointSET.range(new RectHV(0.0, 0.0, 0.4, 0.4)).iterator();
        point2D = iterator.next();
        assert point2D.x() == 0.2 : point2D.x();
        assert point2D.y() == 0.2 : point2D.y();
        assert iterator.hasNext() == false;
    }

    private static void testNearest() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.8, 0.8));
        pointSET.insert(new Point2D(0.2, 0.5));

        Point2D nearest = pointSET.nearest(new Point2D(0.1, 0.1));
        assert nearest.x() == 0.2;
        assert nearest.y() == 0.5;
    }
}