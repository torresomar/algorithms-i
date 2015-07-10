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
      quickUnionHelper = new WeightedQuickUnionUF((N * N) + 2); //Imaginary nodes t/b
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
      if(i == 1){ //Upper node
         quickUnionHelper.union(0,gridToArray(i,j));
      }else if(i == size){
         quickUnionHelper.union((size * size) + 1 ,gridToArray(i,j));
      }
      // System.out.println("index " + index[0]+","+index[1]);
      // System.out.println("i,j " + i + "," + j);
      if(j < (size + 1) && j > 1){
         if(id[index[0]][index[1] - 1] == OPEN){
            quickUnionHelper.union(gridToArray(i,j) ,gridToArray(i,j - 1));
         }
      }
      if(j > 0 && j < size){
         if(id[index[0]][index[1] + 1] == OPEN){
            quickUnionHelper.union(gridToArray(i,j) ,gridToArray(i,j + 1));
         }
      }
      if(i > 1 && i < size){
         if(id[index[0] + 1][index[1]] == OPEN){
            quickUnionHelper.union(gridToArray(i,j) ,gridToArray(i + 1,j));
         }
      }
      if(i > 1 && i < (size + 1)){
         if(id[index[0] - 1][index[1]] == OPEN){
            quickUnionHelper.union(gridToArray(i,j) ,gridToArray(i - 1,j));
         }
      }
      this.printPercolation();
      quickUnionHelper.print();
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

   // Additional API methods
   public int gridToArray(int i, int j){
      int[] index = getShiftedIndex(i,j);
      return (index[0] * size) + index[1] + 1;
   }
   public void printPercolation(){
      System.out.println("");
      System.out.println("------------- START -----------------------");
      for(int i = 0; i < size; i++){
         for(int j = 0; j < size; j++){
            System.out.print(id[i][j] + " ");
         }
         System.out.print("\n");
      }
      System.out.println("------------- END -----------------------");
   }
   public void checkIndexBounds(int i, int j){
      if(i < 1 || j < 1 || i > size || j > size ){
         throw new IndexOutOfBoundsException("Illegal parameter value.");
      }
   }
   public int[] getShiftedIndex(int i, int j){
      checkIndexBounds(i,j);
      int[] shifted = {i - 1, j - 1};
      return shifted;
   }
   public int getSize(){
      return size;
   }
   //Test
   public static void main(String[] args){
      int tests = 1000;
      int gridSize = Integer.parseInt(args[0]);
      Percolation p = new Percolation(gridSize);
      int col,row,count;
      double sum = 0.0;
      for(int i = 0; i < tests; i++){
         count = 0;  
         while(!p.percolates()){
            row = StdRandom.uniform(gridSize) + 1;
            col = StdRandom.uniform(gridSize) + 1;
            if (p.isFull(row, col)) {
               p.open(row, col);
               count++;
            }
         }
         sum += count;
      }
   }// test client (optional)
}
