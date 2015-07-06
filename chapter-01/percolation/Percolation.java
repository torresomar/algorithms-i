public class Percolation {
   private int [][] id;
   private int size;
   private static final int OPEN = 1;
   private static final int CLOSED = 0;
   private WeightedQuickUnionUF ufHelper;
   public Percolation(int N){
      id = new int[N][N];
      size = N;
      ufHelper = new WeightedQuickUnionUF(N);
      for(int i = 0; i < N; i++){
         for(int j = 0; j < N; j++){
            id[i][j] = CLOSED;
         }
      }
   }// create N-by-N grid, with all sites blocked
   public void open(int i, int j){
      if(id[i][j] == OPEN) return;
      id[i][j] = OPEN;
   }// open site (row i, column j) if it is not open already
   public boolean isOpen(int i, int j){
      if(id[i][j] == OPEN){
         return true;
      }else{
         return false;
      }
   }// is site (row i, column j) open?
   public boolean isFull(int i, int j){
      return true;
   }// is site (row i, column j) full?
   public boolean percolates(){
      return true;
   }// does the system percolate?
   public void printPercolation(){
      for(int i = 0; i < size; i++){
         for(int j = 0; j < size; j++){
            System.out.print(id[i][j] + " ");
         }
         System.out.print("\n");
      }
   }
   public boolean checkIndexBounds(int i, int j){
      //Offsetting this
      i = i - 1;
      j = j - 1;
      if(i < 0 || j < 0 || i > N || j > N ){
         throw new IndexOutOfBoundsException("Illegal parameter value.");
      }
   }
   public static void main(String[] args){
      int gridSize = Integer.parseInt(args[0]);
      Percolation per = new Percolation(gridSize);
      per.printPercolation();
   }// test client (optional)
}
