/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("argument to constructor us null");
        }

        // create copy to avoid mutating the argument
        int n = points.length;
        Point[] copy = new Point[n];
        for (int i = 0; i < n; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("point at index " + i + " is null");
            }
            copy[i] = points[i];
        }

        // sort to detect duplicates and ensure ordering
        Arrays.sort(copy);

        // check for duplicates after sorting
        for (int i = 0; i < n - 1; i++) {
            if (copy[i].compareTo(copy[i + 1]) == 0) {
                throw new IllegalArgumentException("duplicate points");
            }
        }

        // brute force algorithm
        ArrayList<LineSegment> foundSegments = new ArrayList<>();

        for (int p = 0; p < n - 3; p++) {
            for (int q = p + 1; q < n - 2; q++) {
                for (int r = q + 1; r < n - 1; r++) {
                    for (int s = r + 1; s < n; s++) {

                        Point pPoint = copy[p];
                        Point qPoint = copy[q];
                        Point rPoint = copy[r];
                        Point sPoint = copy[s];

                        // calculate slopes between p and others
                        double slopePQ = pPoint.slopeTo(qPoint);
                        double slopePR = pPoint.slopeTo(rPoint);
                        double slopePS = pPoint.slopeTo(sPoint);

                        // if all slopes are equal, they are collinear
                        if (slopePQ == slopePR && slopePQ == slopePS) {
                            foundSegments.add(new LineSegment(pPoint, sPoint));
                        }
                    }
                }
            }
        }
        // convert ArrayList to plain array
        this.segments = foundSegments.toArray(new LineSegment[0]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        // return a clone so the user can't modify the internal array
        return segments.clone();
    }

    public static void main(String[] args) {

    }
}
