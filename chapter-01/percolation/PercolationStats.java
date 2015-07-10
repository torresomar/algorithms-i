public class PercolationStats {
   private int numTests;
   private int gridSize;
   private double sum;
   private double[] data;
   public PercolationStats(int N, int T){
      if(N == 0 || T == 0){
         throw new IndexOutOfBoundsException("Illegal parameter value.");
      }
      numTests = T;
      gridSize = N;
   }// perform T independent experiments on an N-by-N grid
   public double mean(){ 
      return 2.0;
   }// sample mean of percolation threshold
   public double stddev(){
      return 2.0;
   }// sample standard deviation of percolation threshold
   public double confidenceLo(){
      return 2.0;
   }// low  endpoint of 95% confidence interval
   public double confidenceHi(){
      return 2.0;
   }// high endpoint of 95% confidence interval
   public void runConfiguration(){
      int col,row,size,count;
      double sum = 0.0;
      Percolation p;
      double[] fra = new double[gridSize];
      for(int i = 0; i < numTests; i++){
         count = 0;  
         p = new Percolation(gridSize);
         while(!p.percolates()){
            row = StdRandom.uniform(gridSize) + 1;
            col = StdRandom.uniform(gridSize) + 1;
            if(!p.isOpen(row,col)){
               p.open(row,col);
               count++;
            }
         }
         double fraction = (double) count/ (gridSize  * gridSize);
         fra[i] = fraction;
      }
      System.out.println(mean(fra));
   }
   public static double mean(double[] a) {
      if (a.length == 0) return Double.NaN;
      double sum = sum(a);
      return sum / a.length;
   }
   public static double sum(double[] a) {
      double sum = 0.0;
      for (int i = 0; i < a.length; i++) {
         sum += a[i];
      }
      return sum;
   }
   public static void main(String[] args){
      int N,T;
      N = Integer.parseInt(args[0]);
      T = Integer.parseInt(args[1]);
      PercolationStats p_stats = new PercolationStats(N,T);
      p_stats.runConfiguration();
   }// test client (described below)
}
