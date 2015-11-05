public class FastCollinearPointsTest {

  public static void runTests() {
     testSortPoints();
     testConstructor();
  }

  private static void testSortPoints() {
    FastCollinearPoints.FastCollinearPointsUtil util = new FastCollinearPoints.FastCollinearPointsUtil();
    Point[] points = new Point[4];
    points[0] = new Point(1, 1);
    points[1] = new Point(3, 4); //slope = 2.5
    points[2] = new Point(5, 6); //slope = 1.25
    points[3] = new Point(7, 8); // slope = 1.17
    points = util.sortPoints(points, points[0].slopeOrder());
    assert points[0].toString().equals("(1, 1)") : points[0].toString();
    assert points[1].toString().equals("(7, 8)") : points[1].toString();
    assert points[2].toString().equals("(5, 6)") : points[2].toString();
    assert points[3].toString().equals("(3, 4)") : points[3].toString();
  }

  private static void testConstructor() {
    Point[] points = new Point[5];
    points[0] = new Point(1, 1);
    points[1] = new Point(2, 2);
    points[2] = new Point(3, 3);
    points[3] = new Point(4, 4);

      points[4] = new Point(200, 74);

    FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
    LineSegment[] segments = fastCollinearPoints.segments();
    for (LineSegment segment : segments) {
      System.out.println(segment);
    }
    assert fastCollinearPoints.segments()[0].toString().equals("(1, 1) -> (4, 4)");
  }
}
