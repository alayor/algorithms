import java.lang.System;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

public class FastCollinearPoints {
  private List<LineSegment> _lineSegmentList = new ArrayList<LineSegment>();
  private Point[] _points;

  public FastCollinearPoints(Point[] points) {
    _points = points;
      Arrays.sort(_points);
    find4PointCollinearSegment();
  }

    private List<LineSegment> find4PointCollinearSegment() {
    if (_points.length > 2) {
      for (int i = 0; i < _points.length; i++) {
        findCollinearWithCurrentPoint(i);
      }
    }
    return _lineSegmentList;
  }

    private void findCollinearWithCurrentPoint(int current) {
    Comparator<Point> comparator = _points[current].slopeOrder();
      Point[] sortedPoints = new FastCollinearPointsUtil().sortPoints(_points, comparator);
      Map<Double, List<Point>> slopeMap = new HashMap<Double, List<Point>>();
      for(int i = 0; i < sortedPoints.length; i++) {
          Double slopeKey = sortedPoints[0].slopeTo(sortedPoints[i]);
          if(slopeKey != Double.NEGATIVE_INFINITY) {
              List<Point> pointList = slopeMap.get(slopeKey);
              if(pointList == null) {
                  pointList = new ArrayList<Point>();
                  pointList.add(sortedPoints[0]);
              }
              pointList.add(sortedPoints[i]);
              slopeMap.put(slopeKey, pointList);
          }
      }
      for(Map.Entry<Double, List<Point>> entry : slopeMap.entrySet()) {
          List<Point> pointList = entry.getValue();
          if (pointList.size() > 3) {
              _lineSegmentList.add(new LineSegment(pointList.get(0), pointList.get(pointList.size() - 1)));
          }
      }
  }

  public int numberOfSegments() {
      return _lineSegmentList.size();
  }

  public LineSegment[] segments() {
    LineSegment[] segments = new LineSegment[_lineSegmentList.size()];
    _lineSegmentList.toArray(segments);
    return segments;
  }
  public static void main(String[] args) {
  }

    private static class FastCollinearPointsUtil {
    Point[] sortPoints(Point[] points, Comparator<Point> comparator) {
      Point[] result = new Point[points.length];
      for (int i = 0; i < points.length; i++) {
        result[i] = points[i];
      }
      Arrays.sort(result, comparator);
      return result;
    }
  }
}
