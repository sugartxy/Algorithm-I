import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] trial;
    private int tT;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        tT = trials;
        trial = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                // Random random=new Random();
                int r = StdRandom.uniform(n) + 1;
                int c = StdRandom.uniform(n) + 1;
                // System.out.println("r "+r+", c "+c);
                percolation.open(r, c);
            }
            int nOpen = percolation.numberOfOpenSites();
            trial[i] = (double) nOpen / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(trial);
    }

    public double stddev() {

        return StdStats.stddev(trial);
    }

    public double confidenceLo() {

        double res = mean() - (1.96 * stddev() / Math.sqrt(tT));
        return res;
    }

    public double confidenceHi() {
        double res = mean() + (1.96 * stddev() / Math.sqrt(tT));
        return res;
    }

    public static void main(String[] args) {
        // Scanner sc= new Scanner(System.in);
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.println("mean= " + ps.mean() + "\n");
        StdOut.println("stddev= " + ps.stddev() + "\n");
        StdOut.println("95% confidence interval= [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]\n");
    }
}
