public class Percolation {
   private int [][] id;
   private int size;
   private static final int OPEN = 1;
   private static final int FULL = 0;
   private WeightedQuickUnionUF qu;
   public Percolation(int N){
      id = new int[N][N];
      size = N;
      for(int i = 0; i < N; i++){
         for(int j = 0;j < N; j++){
            id[i][j] = FULL;
         }
      }
      qu = new WeightedQuickUnionUF((N * N) + 2); //Adding top/bot node
   }// create N-by-N grid, with all sites blocked
   public void open(int i, int j){
      checkIndex(i,j);
      // Reduce by one
      i = i - 1;
      j = j - 1;
      id[i][j] = OPEN;
      if(i == 0){
         System.out.println("Union with top");
         qu.union(0,gridToArray(i,j));
      }
      if(i == (size - 1)){
         System.out.println("Union with bottom");
         qu.union((size * size) + 1,gridToArray(i,j));
      }
      if(j >= 0 && j < (size - 1) && id[i][j + 1] == OPEN){
         System.out.println("Union with right");
      }

   }// open site (row i, column j) if it is not open already

   public boolean isOpen(int i, int j){
      checkIndex(i,j);
      return id[i - 1][j - 1] == OPEN;
   }// is site (row i, column j) open?
   public boolean isFull(int i, int j){
      checkIndex(i,j);
      return id[i - 1][j - 1] == FULL;
   }// is site (row i, column j) full?
   public boolean percolates(){
      return true;
   }// does the system percolate?
   public void checkIndex(int i,int j){
      if(i < 1 || i > size || j < 1 || j > size){
         throw new IndexOutOfBoundsException("Illegal parameter value.");
      }
   }
   public int gridToArray(int i,int j){
      return ((i * size) + j) + 1;
   }
   public void print(){
      System.out.println("");
      System.out.println("------------- START -----------------------");
      for(int i = 0; i < size; i++){
         for(int j = 0; j < size; j++){
            System.out.print(id[i][j] + " ");
         }
         System.out.print("\n");
      }
      System.out.println("------------- QU -----------------------");
      qu.print(size);
      System.out.println("------------- END -----------------------");
   }
   //Test
   public static void main(String[] args){
      Percolation p = new Percolation(5);
      p.open(1,2);
      p.open(1,1);
      p.open(1,5);
      p.open(5,1);
      p.print();
   }// test client (optional)
}
