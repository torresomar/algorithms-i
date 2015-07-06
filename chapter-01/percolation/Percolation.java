public class Percolation {
   private int [][] id;
   private int size;
   private static final int OPEN = 1;
   private static final int CLOSED = 0;
   private WeightedQuickUnionUF quickUnionHelper;
   public Percolation(int N){
      id = new int[N][N];
      size = N;
      // Transform the problem into a 1d array + 2 due to the offset
      quickUnionHelper = new WeightedQuickUnionUF((N * N) + 2); 
      for(int i = 0; i < N; i++){
         for(int j = 0; j < N; j++){
            id[i][j] = CLOSED;
         }
      }
   }// create N-by-N grid, with all sites blocked
   public void open(int i, int j){
      int[] index = getShiftedIndex(i,j);
      if(id[index[0]][index[1]] == OPEN) return;
      id[index[0]][index[1]] = OPEN;
      // Check multiple cases of union
      System.out.println("Trying to open " + "[" + i + "," + j + "] => QU " + gridToArray(i,j));
   }// open site (row i, column j) if it is not open already
   public boolean isOpen(int i, int j){
      int[] index = getShiftedIndex(i,j);
      return id[index[0]][index[1]] == OPEN;
   }// is site (row i, column j) open?
   public boolean isFull(int i, int j){
      int[] index = getShiftedIndex(i,j);
      return id[index[0]][index[1]] == CLOSED;
   }// is site (row i, column j) full?
   public boolean percolates(){
      return quickUnionHelper.connected(0,(size * size) + 1);
   }// does the system percolate?
   public int gridToArray(int i, int j){
      int[] index = getShiftedIndex(i,j);
      return (index[0] * size) + index[1] + 1;
   }
   public void printPercolation(){
      for(int i = 0; i < size; i++){
         for(int j = 0; j < size; j++){
            System.out.print(id[i][j] + " ");
         }
         System.out.print("\n");
      }
   }
   public void checkIndexBounds(int i, int j){
      if(i < 0 || j < 0 || i > size || j > size ){
         throw new IndexOutOfBoundsException("Illegal parameter value.");
      }
   }
   public int[] getShiftedIndex(int i, int j){
      checkIndexBounds(i,j);
      int[] shifted = {i - 1, j - 1};
      return shifted;
   }
   public static void main(String[] args){
      int gridSize = Integer.parseInt(args[0]);
      Percolation per = new Percolation(gridSize);
      // Check for index exceptions
      // per.checkIndexBounds(gridSize + 1,0);
      // per.checkIndexBounds(0,0);
      int array = per.gridToArray(1,1);
      per.printPercolation();
      per.open(2,1);
      per.printPercolation();
      System.out.println("[2,1] is full? " + per.isFull(2,1));
      System.out.println("[2,1] is open? " + per.isOpen(2,1));
      per.open(2,1);
   }// test client (optional)
}
