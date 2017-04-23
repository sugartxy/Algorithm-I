import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int nN;
    // private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF full;
    private int count;
    // for less memory to change int to boolean
    private byte[] sites;

    public Percolation(int n) {
        // System.out.println("enter Percolation()"+n);
        // create n-by-n grid, with all sites blocked
        if (n <= 0)
            throw new IllegalArgumentException();
        nN = n;
        count = 0;
        // uf = new WeightedQuickUnionUF(n * n + 2);
        // prevent the backwash problem
        // https://www.sigmainfy.com/blog/avoid-backwash-in-percolation.html
        full = new WeightedQuickUnionUF(n * n + 1);
        // generate 2 virtual notes for decreasing calls of uf
        sites = new byte[n * n + 2];
    }

    public void open(int row, int col) {

        // System.out.println("enter open()"+row+" "+col);
        // open site (row, col) if it is not open already
        int index = getIndex(row, col);
        if (sites[index] == 0) {
            count++;
            sites[index] = 1;
            // open the site;

            // for the last row
            if (row == nN) {
                // uf.union(sites.length - 1, index);
                sites[index] = 2;
            }
            // for the first row
            if (row == 1) {
                // uf.union(0, index);
                full.union(0, index);
                if (sites[index] == 2)
                    sites[0] = 2;
            }
            // union left if left is open
            if (col - 1 >= 1 && isOpen(row, col - 1)) {
                int left = getIndex(row, col - 1);
                int rootL = full.find(left);
                int root = full.find(index);
                full.union(left, index);
                int newRoot = full.find(index);
                if (sites[rootL] == 2 || sites[root] == 2)
                    sites[newRoot] = 2;
            }
            // union right if is open
            if (col + 1 <= nN && isOpen(row, col + 1)) {
                int right = getIndex(row, col + 1);
                int rootR = full.find(right);
                int root = full.find(index);
                full.union(right, index);
                int newRoot = full.find(index);
                if (sites[rootR] == 2 || sites[root] == 2)
                    sites[newRoot] = 2;
            }
            // union up if is open
            if (row - 1 >= 1 && isOpen(row - 1, col)) {
                int up = getIndex(row - 1, col);
                int rootU = full.find(up);
                int root = full.find(index);
                full.union(up, index);
                int newRoot = full.find(index);
                if (sites[rootU] == 2 || sites[root] == 2)
                    sites[newRoot] = 2;
            }
            // union down if is open
            if (row + 1 <= nN && isOpen(row + 1, col)) {
                int down = getIndex(row + 1, col);
                int rootD = full.find(down);
                int root = full.find(index);
                full.union(down, index);
                int newRoot = full.find(index);
                if (sites[rootD] == 2 || sites[root] == 2)
                    sites[newRoot] = 2;
            }
        }
    }

    private int getIndex(int row, int col) {
        // System.out.println("enter getIndex()"+row+" "+col);
        validate(row, col);
        int index = (row - 1) * nN + col;
        return index;
    }

    public boolean isOpen(int row, int col) {
        // System.out.println("enter isOpen()"+row+" "+col);
        // is site (row, col) open?

        int index = getIndex(row, col);
        return sites[index] > 0;
    }

    public boolean isFull(int row, int col) {
        // System.out.println("enter isFull()"+row+" "+col);
        // is site (row, col) full?

        int index = getIndex(row, col);
        if (full.connected(0, index))
            return true;

        return false;
    }

    public int numberOfOpenSites() {
        // System.out.println("enter numberOfOpenSites()");
        // number of open sites
        return count;
    }

    public boolean percolates() {
        // System.out.println("enter percolates()");
        // does the system percolate?
        int root = full.find(0);
        return sites[root] == 2;
    }

    private void validate(int row, int col) {
        // System.out.println("enter validate()"+row+" "+col);
        if (!(row >= 1 && row <= nN && col >= 1 && col <= nN)) {
            throw new IndexOutOfBoundsException("row " + row + "or col " + col + "is not validate");
        }
    }
}
