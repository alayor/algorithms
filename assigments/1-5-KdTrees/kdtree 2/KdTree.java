import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private Node _root;
    private int _size;

    public boolean isEmpty() {
        return _root == null;
    }

    public int size() {
        return _size;
    }

    public void insert(Point2D point2D) {
        _root = put(_root, point2D, true, new RectHV(0.0, 0.0, 1, 1));
        _size++;
    }

    private Node put(Node node, Point2D point2D, boolean searchVertical, RectHV rectHV) {
        if (node == null) {
            return createNode(point2D, searchVertical, rectHV);
        }
        int compare = getComparation(node, point2D, searchVertical);
        if (compare < 0) {
            node.leftBelowNode = put(node.leftBelowNode, point2D, !searchVertical, node.rectHV);
        } else if (compare > 0) {
            node.rightTopNode = put(node.rightTopNode, point2D, !searchVertical, node.rectHV);
        } else {
            if (point2D.equals(node.point2D)) {
                setPoint(point2D, node);
            } else {
                node.rightTopNode = put(node.rightTopNode, point2D, !searchVertical, node.rectHV);
            }
        }
        return node;
    }

    private int getComparation(Node node, Point2D point2D, boolean vertical) {
        if (vertical) {
            return Double.compare(point2D.x(), node.point2D.x());
        } else {
            return Double.compare(point2D.y(), node.point2D.y());
        }
    }

    private KdTree.Node createNode(Point2D point2D, boolean searchVertical, RectHV rectHV) {
        Node n = new Node();
        setPoint(point2D, n);
        setRectangle(point2D, searchVertical, rectHV, n);
        return n;
    }

    private void setPoint(Point2D point2D, KdTree.Node n) {
        n.point2D = point2D;
    }

    private void setRectangle(Point2D point2D, boolean searchVertical, RectHV rectHV, KdTree.Node n) {
        double xmin = 0.0;
        double ymin = 0.0;
        double xmax = 0.0;
        double ymax = 0.0;
        if (searchVertical) {
            xmin = point2D.x();
            xmax = point2D.x();
            if (point2D.y() <= rectHV.ymax()) {
                ymin = 0;
                ymax = rectHV.ymax();
            } else {
                ymin = rectHV.ymax();
                ymax = 1;
            }
        } else {
            ymin = point2D.y();
            ymax = point2D.y();
            if (point2D.x() <= rectHV.xmax()) {
                xmin = 0;
                xmax = rectHV.xmax();
            } else {
                xmin = rectHV.xmax();
                xmax = 1;
            }
        }
        n.rectHV = new RectHV(xmin, ymin, xmax, ymax);
    }

    public boolean contains(Point2D point2D) {
        Node node = _root;
        boolean searchVertical = true;
        while (node != null) {
            int compare = getComparation(node, point2D, searchVertical);
            if (compare < 0) {
                node = node.leftBelowNode;
            } else if (compare > 0) {
                node = node.rightTopNode;
            } else {
                if (point2D.equals(node.point2D)) {
                    return true;
                } else {
                    node = node.rightTopNode;
                }
            }
            searchVertical = !searchVertical;
        }
        return false;
    }

    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        printNode(_root, true);
    }

    private void printNode(Node node, boolean vertical) {
        if (node == null) {
            return;
        }
        printNode(node.leftBelowNode, !vertical);
        printPoint(node);
        printLine(node, vertical);
        printNode(node.rightTopNode, !vertical);
    }

    private void printPoint(KdTree.Node node) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        node.point2D.draw();
    }

    private void printLine(KdTree.Node node, boolean vertical) {
        StdDraw.setPenRadius();
        final RectHV rectHV = node.rectHV;
        if (vertical) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(rectHV.xmax(), rectHV.ymin(), rectHV.xmax(), rectHV.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(rectHV.xmin(), rectHV.ymax(), rectHV.xmax(), rectHV.ymax());
        }
    }

    public Iterable<Point2D> range(RectHV rectHV) {
        List<Point2D> points = new ArrayList<Point2D>();
        collectPointsInRectangle(points, rectHV, _root, true);
        return new Iterable<Point2D>() {
            @Override
            public Iterator<Point2D> iterator() {
                return points.iterator();
            }
        };
    }

    private void collectPointsInRectangle(List<Point2D> points, RectHV rectHV, Node node, boolean searchVertical) {
        if (node == null) {
            return;
        }
        if (rectHV.contains(node.point2D)) {
            points.add(node.point2D);
        }
        if (rectHV.intersects(node.rectHV)) {
            collectPointsInRectangle(points, rectHV, node.leftBelowNode, !searchVertical);
            collectPointsInRectangle(points, rectHV, node.rightTopNode, !searchVertical);
        } else {
            if (searchVertical) {
                if (rectHV.xmax() < node.point2D.x()) {
                    collectPointsInRectangle(points, rectHV, node.leftBelowNode, !searchVertical);
                } else {
                    collectPointsInRectangle(points, rectHV, node.rightTopNode, !searchVertical);
                }
            } else {
                if (rectHV.ymax() < node.point2D.y()) {
                    collectPointsInRectangle(points, rectHV, node.leftBelowNode, !searchVertical);
                } else {
                    collectPointsInRectangle(points, rectHV, node.rightTopNode, !searchVertical);
                }
            }
        }
    }

    public Point2D nearest(Point2D point2D) {
        Node node = _root;
        Point2D nearest = null;
        double minDistance = Double.MAX_VALUE;
        boolean searchVertical = true;
        while (node != null) {
            final double currentDistance = point2D.distanceTo(node.point2D);
            if (currentDistance < minDistance) {
                nearest = node.point2D;
                minDistance = currentDistance;
            }
            if (searchVertical) {
                if (point2D.x() <= node.point2D.x()) {
                    node = node.leftBelowNode;
                } else {
                    node = node.rightTopNode;
                }
            } else {
                if (point2D.y() <= node.point2D.y()) {
                    node = node.leftBelowNode;
                } else {
                    node = node.rightTopNode;
                }
            }
            searchVertical = !searchVertical;
        }
        return nearest;
    }

    public static void main(String[] args) {

    }

    private static class Node {
        public Point2D point2D;
        public RectHV rectHV;
        public Node leftBelowNode;
        public Node rightTopNode;
        public boolean visited;
    }
}