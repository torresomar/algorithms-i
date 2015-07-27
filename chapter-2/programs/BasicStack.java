public class BasicStack
{
   private Node first = null;
   private int size;
   public BasicStack(){
      size = 0;
   }
   public boolean isEmpty(){
      return first == null;
   }
   public void push(String item)
   {
      Node old = first;
      first = new Node();
      first.item = item;
      first.next = old;
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
   public static void main(String args[]){
      BasicStack stack = new BasicStack();
      stack.push("Amazing");
      stack.push("this");
      stack.push("is");
      stack.push("a");
      stack.push("Stack");
      System.out.println(stack);
   }
   private class Node
   {
      String item;
      Node next;
   }
}
