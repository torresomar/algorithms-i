public class PercolationStats {
   private int numTests;
   private int gridSize;
   private double[] fractions;
   public PercolationStats(int N, int T) {
      if (N == 0 || T == 0) {
         throw new IndexOutOfBoundsException("Illegal parameter value.");
      }
      numTests = T;
      gridSize = N;
      int col, row, size, count;
      double sum = 0.0;
      Percolation p;
      fractions = new double[gridSize];
      for (int i = 0; i < numTests; i++) {
         count = 0;  
         p = new Percolation(gridSize);
         while (!p.percolates()) {
            row = StdRandom.uniform(gridSize) + 1;
            col = StdRandom.uniform(gridSize) + 1;
            if (!p.isOpen(row, col)) {
               p.open(row, col);
               count++;
            }
         }
         double fraction = (double) count / (gridSize  * gridSize);
         fractions[i] = fraction;
      }
   }
   public double mean() {
      return StdStats.mean(fractions);
   }
   public double stddev() {
      return StdStats.stddev(fractions);
   }
   public double confidenceLo() {
      return mean() - ((1.96 * stddev()) / Math.sqrt(numTests));
   }
   public double confidenceHi() {
      return mean() + ((1.96 * stddev()) / Math.sqrt(numTests));
   }
   public static void main (String args []) {
      int size = Integer.parseInt(args[0]);
      int tests = Integer.parseInt(args[1]);
      PercolationStats ps = new PercolationStats(size, tests);
      String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
      StdOut.println("mean                    = " + ps.mean());
      StdOut.println("stddev                  = " + ps.stddev());
      StdOut.println("95% confidence interval = " + confidence);
   }
}
