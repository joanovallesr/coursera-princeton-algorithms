/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("argument to constructor null");
        }

        // create copy to avoid mutating argument
        int n = points.length;
        Point[] points1 = new Point[n];
        for (int i = 0; i < n; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("point at index " + i + " is null");
            }
            points1[i] = points[i];
        }

        // sort by coordinates
        Arrays.sort(points1);

        // check for duplicates
        for (int i = 0; i < n - 1; i++) {
            if (points1[i].compareTo(points1[i + 1]) == 0) {
                throw new IllegalArgumentException("duplicate points");
            }
        }

        ArrayList<LineSegment> foundSegments = new ArrayList<>();

        // iterate through every point treating "p" as the origin
        for (int i = 0; i < n; i++) {
            Point p = points1[i];

            // clone to sort it by slope relative to p
            Point[] slopeSorted = points1.clone();

            // sort slopeSorted
            Arrays.sort(slopeSorted, p.slopeOrder());

            // scan for runs of 3 or more points with the same slope
            int j = 1;
            while (j < n) {
                int k = j + 1;
                while (k < n && p.slopeTo(slopeSorted[j]) == p.slopeTo(slopeSorted[k])) {
                    k++;
                }

                // number of points in the run (excluding p)
                if (k - j >= 3) {
                    // if p is smaller than slopeSorted[j], then p is the true "start"
                    if (p.compareTo(slopeSorted[j]) < 0) {
                        foundSegments.add(new LineSegment(p, slopeSorted[k - 1]));
                    }
                }

                // jump "j" to the start of the next slope group
                j = k;
            }
        }
        this.segments = foundSegments.toArray(new LineSegment[0]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.clone();
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
