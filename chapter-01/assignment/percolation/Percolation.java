import java.util.Scanner;
public class Percolation {
   private static final int OPEN = 1;
   private static final int FULL = 0;
   private int [][] id;
   private int size;
   private WeightedQuickUnionUF qu;
   public Percolation(int N) {
      id = new int[N][N];
      size = N;
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            id[i][j] = FULL;
         }
      }
      qu = new WeightedQuickUnionUF((N * N) + 2); //Adding top/bot node
   } // create N-by-N grid, with all sites blocked
   public void open(int i, int j) {
      checkIndex(i,j);
      // Reduce by one
      i = i - 1;
      j = j - 1;
      id[i][j] = OPEN;
      if (i == 0) {
         // System.out.println("Union with top");
         qu.union(0, gridToArray(i, j));
      }
      if (i == (size - 1)) {
         // System.out.println("Union with bottom");
         qu.union((size * size) + 1, gridToArray(i, j));
      }
      if (j < (size - 1) && id[i][j + 1] == OPEN) {
         // System.out.println("Union with right");
         qu.union(gridToArray(i,j), gridToArray(i, j + 1));
      }
      if (j >= 1 && id[i][j - 1] == OPEN) {
         // System.out.println("Union with left");
         qu.union(gridToArray(i,j), gridToArray(i, j - 1));
      }
      if (i < (size - 1) && id[i + 1][j] == OPEN) {
         // System.out.println("Union with bot");
         qu.union(gridToArray(i,j), gridToArray(i + 1, j));
      }
      if (i >= 1 && id[i - 1][j] == OPEN) {
         // System.out.println("Union with bot");
         qu.union(gridToArray(i,j), gridToArray(i - 1, j));
      }
   } // open site (row i, column j) if it is not open already
   public boolean isOpen(int i, int j) {
      checkIndex(i, j);
      return id[i - 1][j - 1] == OPEN;
   } // is site (row i, column j) open?
   public boolean isFull(int i, int j){
      checkIndex(i, j);
      return qu.connected(0, gridToArray(i - 1, j - 1));
   } // is site (row i, column j) full?
   public boolean percolates() {
      return qu.connected(0, (size * size) + 1);
   } // does the system percolate?
   private void checkIndex(int i, int j) {
      if (i < 1 || i > size || j < 1 || j > size){
         throw new IndexOutOfBoundsException("Illegal parameter value.");
      }
   }
   private int gridToArray(int i, int j) {
      return ((i * size) + j) + 1;
   }
   private void print() {
      StdOut.println("");
      StdOut.println("---- START ----"); 
      StdOut.println("1|2|3|4|5");
      StdOut.println("---------");
      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            StdOut.print(id[i][j] + " ");
         }
         StdOut.print("\n");
      }
      StdOut.println("---- QU ----");
      // qu.print(size);
      StdOut.println("---- END ----");
   }
   //Test
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int size = Integer.parseInt(scanner.next());
      Percolation p = new Percolation(size);
      while (scanner.hasNext()) {
         p.open(scanner.nextInt(), scanner.nextInt());
         p.print();
      }
      scanner.close();
      // Percolation p = new Percolation(20);
      // p.open(1, 1);
      // p.open(1, 2);
      // p.open(1, 1);
      // p.open(1, 5);
      // p.open(2, 4);
      // p.open(2, 5);
      // p.open(5, 1);
      // p.open(4, 1);
      // p.open(3, 1);
      // p.open(2, 1);
      // p.print();
   }
}
