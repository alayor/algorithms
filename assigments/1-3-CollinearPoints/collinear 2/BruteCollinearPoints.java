import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {
  private List<LineSegment> _lineSegmentList = new ArrayList<LineSegment>();
  private Point[] _points;

   public BruteCollinearPoints(Point[] points) {
     if (points == null) {
       throw new NullPointerException();
     }
     for (Point point : points) {
       if (point == null) {
         throw new NullPointerException();
       }
     }
     _points = points;
     sortPoints();
     find4PointCollinearSegment();
   }

  private Point[] sortPoints() {
     Arrays.sort(_points);
     return _points;
   }

   private List<LineSegment> find4PointCollinearSegment() {
     for (int p = 0; p < _points.length; p++) {
       for (int q = p + 1; q < _points.length; q++) {
        for (int r = q + 1; r < _points.length; r++) {
          for (int s = r + 1; s < _points.length; s++) {
            if (areCollinearPoints(p, q, r, s)) {
              _lineSegmentList.add(new LineSegment(_points[p], _points[s]));
            }
          }
        }
       }
     }
     return _lineSegmentList;
   }

  private boolean areCollinearPoints(int... indexes) {
     boolean areCollinearPoints = false;
     if (_points.length <= 2) {
       return true;
     }
     double slopeToCompare = _points[indexes[0]].slopeTo(_points[indexes[1]]);
     for (int i = 2; i < indexes.length; i++) {
       if (slopeToCompare != _points[indexes[0]].slopeTo(_points[indexes[i]])) {
         return false;
       }
     }
     return true;
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
}
