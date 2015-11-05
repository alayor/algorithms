import java.util.Comparator;

public class PointTest {

  public void runTests() {
    testCompareTo();
    testSlopeTo();
    testSlopeOrder();
  }

  private void testSlopeOrder(){
    Point point1 = new Point(10, 20);
    Comparator<Point> comparator = point1.slopeOrder();

    Point point2 = new Point(4, 8); //slope = 2
    Point point3 = new Point(3, 10); //slope = 1.4285
    assert comparator.compare(point2, point3) > 0;

    point2 = new Point(4, 8); //slope = 2
    point3 = new Point(2, 2); //slope = 2.25
    assert comparator.compare(point2, point3) < 0;
  }

  private void testSlopeTo() {
    Point point1 = new Point(2, 2);
    Point point2 = new Point(1, 2);
    assert point1.slopeTo(point2) == +0.00;

    point1 = new Point(2, 1);
    point2 = new Point(2, 2);
    assert point1.slopeTo(point2) == Double.POSITIVE_INFINITY;

    point1 = new Point(2, 3);
    point2 = new Point(2, 3);
    assert point1.slopeTo(point2) == Double.NEGATIVE_INFINITY;

    point1 = new Point(4, 5);
    point2 = new Point(20, 10);
    double slope = point1.slopeTo(point2);
    assert slope == 0.3125 : "Expected: 0.3125, Actual: " + slope;
  }

  private void testCompareTo() {
      Point point1 = new Point(2, 2);
      Point point2 = new Point(1, 1);
      assert point1.compareTo(point2) > 0;

      point1 = new Point(1, 1);
      point2 = new Point(2, 2);
      assert point1.compareTo(point2) < 0;

      point1 = new Point(2, 1);
      point2 = new Point(1, 1);
      assert point1.compareTo(point2) > 0;

      point1 = new Point(1, 1);
      point2 = new Point(2, 1);
      assert point1.compareTo(point2) < 0;

      point1 = new Point(1, 1);
      point2 = new Point(1, 1);
      assert point1.compareTo(point2) == 0;
  }
}
