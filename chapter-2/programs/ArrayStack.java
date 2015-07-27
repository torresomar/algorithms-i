public class ArrayStack
{
   private String [] stack;
   private int position = 0;
   public ArrayStack(int size)
   {
      stack = new String[size];
   }
   public void push(String item)
   {
      stack[position++] = item;
   }
   public String pop()
   {
      return stack[position--];
   }
}
