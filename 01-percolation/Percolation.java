/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean[] id;
    private int openSitesCount;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufFull;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0.");
        }
        this.n = n;
        // set up the boolean grid
        this.id = new boolean[n * n];
        this.openSitesCount = 0;
        // to extra squares: virtual top and virtual bottom
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        // avoid the Backwash bug
        this.ufFull = new WeightedQuickUnionUF(n * n + 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int index = indexOf(row, col);

        // check if the square is open. If not, open it
        if (id[index]) {
            return;
        }
        else {
            id[index] = true;
            openSitesCount++;
        }

        // check the neighbors, if open, connect them
        // neighbor to the top
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(index, indexOf(row - 1, col));
            ufFull.union(index, indexOf(row - 1, col));
        }
        // neighbor to the bottom
        if (row < n && isOpen(row + 1, col)) {
            uf.union(index, indexOf(row + 1, col));
            ufFull.union(index, indexOf(row + 1, col));
        }
        // neighbor to the left
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(index, indexOf(row, col - 1));
            ufFull.union(index, indexOf(row, col - 1));
        }
        // neighbor to the right
        if (col < n && isOpen(row, col + 1)) {
            uf.union(index, indexOf(row, col + 1));
            ufFull.union(index, indexOf(row, col + 1));
        }
        // virtual top
        if (row == 1) {
            uf.union(index, n * n);
            ufFull.union(index, n * n);
        }
        // virtual bottom
        if (row == n) {
            uf.union(index, n * n + 1);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);

        // return true if the square is open
        return id[indexOf(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int index = indexOf(row, col);

        // return true if the square is open AND it connects to the top
        return isOpen(row, col) && ufFull.find(index) == ufFull.find(n * n);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(n * n) == uf.find(n * n + 1);
    }

    // converts square from (0 to n - 1) to (1 to n)
    private int indexOf(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    // validates the selected square
    private void validate(int row, int col) {
        if ((row < 1 || row > n) || (col < 1 || col > n)) {
            throw new IllegalArgumentException("row and column must be between 1 and n inclusive.");
        }
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
