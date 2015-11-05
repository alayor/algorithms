import java.lang.Iterable;
import java.lang.Override;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {

    private Set<Point2D> _points;

    public PointSET() {
        _points = new TreeSet<Point2D>();
    }

    public boolean isEmpty() {
        return _points == null || _points.size() < 1;
    }

    public int size() {
        return _points == null ? 0 : _points.size();
    }

    public void insert(Point2D point2D) {
        _points.add(point2D);
    }

    public boolean contains(Point2D point2D) {
        return _points.contains(point2D);
    }

    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        for (Point2D point2D : _points) {
            point2D.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rectHV) {
        List<Point2D> rectHVList = new ArrayList<Point2D>();
        for (Point2D point2D : _points) {
            if (rectHV.contains(point2D)) {
                rectHVList.add(point2D);
            }
        }

        return new Iterable<Point2D>() {
            @Override
            public Iterator<Point2D> iterator() {
                return rectHVList.iterator();
            }
        };
    }

    public Point2D nearest(Point2D point2D) {
        Point2D nearest = null;
        double minDistance = Double.MAX_VALUE;
        for (Point2D p : _points) {
            final double currentDistance = point2D.distanceTo(p);
            if (currentDistance < minDistance) {
                nearest = p;
                minDistance = currentDistance;
            }
        }
        return nearest;
    }

    public static void main(String[] args){
    }
}