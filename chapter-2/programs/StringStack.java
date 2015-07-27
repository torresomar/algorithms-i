public class StringStack
{
   private Node first = null;
   public boolean isEmpty()
   {
      return first == null;
   }
   public void push(String item)
   {
      Node old = first;
      first = new Node();
      first.next = old;
      first.item = item;
   }
   public String pop()
   {
      String item = first.item;
      first = first.next;
      return item;
   }
   public String toString()
   {
      StringBuffer bf = new StringBuffer();
      Node head = first;
      while(head.next != null)
      {
         bf.append(head.item);
         head = head.next; 
      }
      bf.append(head.item);
      return bf.toString();
   }
   private class Node
   {
      Node next;
      String item;
   }
   public static void main(String args[])
   {
      StringStack stack = new StringStack();
      stack.push("1");
      stack.push("2");
      stack.push("3");
      stack.push("4");
      System.out.println(stack);
      stack.pop();
      System.out.println(stack);
   }
}
