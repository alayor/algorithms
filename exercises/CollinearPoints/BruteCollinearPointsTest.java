import java.util.List;

public class BruteCollinearPointsTest {

  public static void runTests() {
    testNullArgumentsInConstructor();
     testSortPoints();
     testAreCollinearPoints();
     testFind4PointCollinearSegment();
     testFind4PointCollinearSegmentWith8Points();
     testFind4PointCollinearSegmentWith8Points1Found();
     testNumberOfSegments();
  }

  private static void testNullArgumentsInConstructor() {
    try {
          BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(null);
    } catch(Exception e) {
      assert e instanceof NullPointerException;
    }

    Point[] points = new Point[4];
    points[0] = new Point(4, 1);
    points[1] = null;
    points[2] = new Point(2, 4);
    points[3] = new Point(1, 4);

    try {
          BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(null);
    } catch(Exception e) {
      assert e instanceof NullPointerException;
    }
  }

  private static void testSortPoints() {
    Point[] points = new Point[4];
    points[0] = new Point(4, 1);
    points[1] = new Point(3, 2);
    points[2] = new Point(2, 4);
    points[3] = new Point(1, 4);
    BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    points = bruteCollinearPoints.sortPoints();
    assert points[0].toString().equals("(4, 1)") : points[0].toString();
    assert points[1].toString().equals("(3, 2)") : points[1].toString();
    assert points[2].toString().equals("(1, 4)") : points[2].toString();
    assert points[3].toString().equals("(2, 4)") : points[3].toString();
  }

  private static void testAreCollinearPoints() {
    Point[] points = new Point[4];
    points[0] = new Point(1, 1);
    points[1] = new Point(2, 2);
    points[2] = new Point(3, 3);
    points[3] = new Point(4, 4);
    BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);

    boolean areCollinearPoints = bruteCollinearPoints.areCollinearPoints(0, 1, 2, 3);
    assert areCollinearPoints == true : areCollinearPoints;
  }

  private static void testFind4PointCollinearSegment() {
    Point[] points = new Point[4];
    points[0] = new Point(1, 1);
    points[1] = new Point(2, 2);
    points[2] = new Point(3, 3);
    points[3] = new Point(4, 4);
    BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    assert bruteCollinearPoints.numberOfSegments() == 1 : bruteCollinearPoints.segments();
    assert bruteCollinearPoints.segments()[0].toString().equals("(1, 1) -> (4, 4)");
  }

  private static void testFind4PointCollinearSegmentWith8Points() {
    Point[] points = new Point[8];
    points[0] = new Point(1, 1);
    points[1] = new Point(2, 2);
    points[2] = new Point(3, 3);
    points[3] = new Point(4, 4);

    points[4] = new Point(1, 6);
    points[5] = new Point(2, 6);
    points[6] = new Point(3, 6);
    points[7] = new Point(12, 6);

    BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    assert bruteCollinearPoints.segments().length == 2 : bruteCollinearPoints.segments().length;
    assert bruteCollinearPoints.segments()[0].toString().equals("(1, 1) -> (4, 4)");
  }

  private static void testFind4PointCollinearSegmentWith8Points1Found() {
    Point[] points = new Point[8];
    points[0] = new Point(1, 1);
    points[1] = new Point(2, 2);
    points[2] = new Point(3, 3);
    points[3] = new Point(4, 4);

    points[4] = new Point(1, 6);
    points[5] = new Point(2, 6);
    points[6] = new Point(3, 6);
    points[7] = new Point(4, 6);

    BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    assert bruteCollinearPoints.segments().length == 2 : bruteCollinearPoints.segments().length;
    assert bruteCollinearPoints.segments()[0].toString().equals("(1, 1) -> (4, 4)");
    assert bruteCollinearPoints.segments()[1].toString().equals("(1, 6) -> (4, 6)") ;
  }


  private static void testNumberOfSegments() {
    Point[] points = new Point[4];
    points[0] = new Point(1, 1);
    points[1] = new Point(2, 2);
    points[2] = new Point(3, 3);
    points[3] = new Point(4, 4);
    BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
    int numberOfSegments = bruteCollinearPoints.numberOfSegments();
    assert numberOfSegments == 1 : "Expected: 1, Actual: " + numberOfSegments;
  }


}
