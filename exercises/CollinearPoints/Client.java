import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;


public class Client {
    public static void main(String[] args) {

        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        if(args[1].equals("brute")) {
            // print and draw the line segments
            BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
            for (LineSegment segment : bruteCollinearPoints.segments()) {
                StdOut.println(segment);
                segment.draw();
            }
        } else {
            // print and draw the line segments
            FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
            for (LineSegment segment : fastCollinearPoints.segments()) {
                StdOut.println(segment);
                segment.draw();
            }
        }
    }
}