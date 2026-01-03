/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    // constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws the point
    public void draw() {
        StdDraw.point(x, y);
    }

    // draws the segment line from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // string representation
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
        if (this.y == that.y) return +0.0;
        if (this.x == that.x) return Double.POSITIVE_INFINITY;

        return 1.0 * (that.y - this.y) / (that.x - this.x);
    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                return Double.compare(slopeTo(o1), slopeTo(o2));
            }
        };
    }

    public static void main(String[] args) {
        // 1. Create a central point "p" at (0,0)
        Point p = new Point(0, 0);

        // 2. Create target points for different cases
        Point qHorizontal = new Point(10, 0); // Horizontal line to p
        Point qVertical = new Point(0, 10); // Vertical line to p
        Point qSame = new Point(0, 0);  // Same location as p
        Point qNormal = new Point(4, 2);  // Normal slope (0.5)

        // 3. Test and Print Results
        // Test Horizontal (+0.0)
        double slopeH = p.slopeTo(qHorizontal);
        StdOut.println("Horizontal (Exp: +0.0): " + slopeH);

        // Test Vertical (Infinity)
        double slopeV = p.slopeTo(qVertical);
        StdOut.println("Vertical (Exp: Infinity): " + slopeV);

        // Test Same Point (Negative Infinity)
        double slopeS = p.slopeTo(qSame);
        StdOut.println("Same Point (Exp: -Infinity): " + slopeS);

        // Test Normal
        double slopeN = p.slopeTo(qNormal);
        StdOut.println("Normal (Exp: 0.5): " + slopeN);
    }
}
