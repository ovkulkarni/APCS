//name:  Omkar Kulkarni        date: 11-21-16

/*****************************************
Demonstrates many ways to reverse a list made of ListNodes.
******************************************/
public class ListLabReverse
{
   public static void main(String[] args)
   {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
         new ListNode("science",
         new ListNode("java",
         new ListNode("coffee", head))));
         
      System.out.print("original: \t\t\t");
      print(head);
      
      System.out.print("recur and print: \t\t");
      recurAndPrint(head);
      
      System.out.println();
      System.out.print("original: \t\t\t");
      print(head);
   
      System.out.print("reverse by building a new list: ");
      head = reverseBuildNew(head);
      print(head);      
   	
      System.out.print("iterate with 3 pointers:\t");
      head = iterateThreePointers(head);
      print(head);
   	
      System.out.print("recur with 2 pointers: \t\t");
      head = recurTwoPointers(null, head);
      print(head);
           
      System.out.print("recur with pointers and append: ");
      head = recurPointersAppend(head);
      print(head);
   	/*
      System.out.print("Mind Bender reverse:\t\t");
      head = mindBender(head);
      print(head);
      */
   }
   public static void print(ListNode head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }
	/*********************************************
	1. This approach doesn't actually reverse the list.  It only prints
	the list in reverse order.  recurAndPrint() prints the square 
	brackets and calls helper().  helper() is recursive.
	********************************************************/
   public static void recurAndPrint(ListNode h)
   {
      System.out.print("[");
      helper(h);
      System.out.print("]");
   }
   private static void helper(ListNode p)
   {
      if(p != null){   
         helper(p.getNext());
         if(p.getNext() != null)
            System.out.print(", ");
         System.out.print(p.getValue());
      }
     
   }
   
   /*********************************************
	2. This iterative method (for or while) produces a copy of the 
	reversed list.	 For each node going forward, make a new node and 
	link it to the list.	The list will naturally be in reverse. 
	***********************************************************/
   public static ListNode reverseBuildNew(ListNode head)
   {
      ListNode temp = head;
      ListNode newNode = new ListNode(head.getNext().getValue(), head);
      head = head.getNext();
      while(head.getNext() != null){
         newNode = new ListNode(head.getNext().getValue(), newNode);
         head = head.getNext();
      }
      temp.setNext(null);
      return newNode;
   }

   /*******************************************
	3a. This iterative method (while) uses 3 pointers to reverse 
	the list in place.   The two local pointers are called
	prev and next.
   ********************************************************/
   public static ListNode iterateThreePointers(ListNode head)
   {
      ListNode prev = null;
      ListNode next = head;
      while(head != null){
         next = next.getNext();
         head.setNext(prev);
         prev = head;
         head = next;
      }
      return prev;
   }
   	
	/**************************************************
	3b. This method uses two pointers as arguments to reverse 
	the list in place. It is recursive.
	*********************************************************/
   public static ListNode recurTwoPointers(ListNode prev, ListNode head)
   {
      ListNode next = head;
      if(head == null)
         return prev;
      next = head.getNext();
      head.setNext(prev);
      return recurTwoPointers(head, next);
   } 
	/**********************************************
	3c. On each recursive level, find pointerToLast() and 
	nextToLast(). Make a new last. On way back, append() 
	one level to the other. 
	********************************************************/
   public static ListNode recurPointersAppend(ListNode head)
   {
      if(head.getNext() == null)
         return head;
      ListNode lastNode = pointerToLast(head);
      ListNode nextToLastNode = nextToLast(head);
      nextToLastNode.setNext(null);
      return append(lastNode, recurPointersAppend(head));
   }
   private static ListNode pointerToLast(ListNode head)
   {
      if(head == null) 
         return null;
      if(head.getNext() == null) 
         return head;
      else 
         return pointerToLast(head.getNext());
   }
   private static ListNode nextToLast(ListNode head)
   {
      if(head == null) 
         return null;
      if(head.getNext().getNext() == null) 
         return head;
      else 
         return nextToLast(head.getNext());
   }
   private static ListNode append(ListNode h1, ListNode h2)
   {
      h1.setNext(h2);
      return h1;
   }

   
   /**********************************************
   3d. This difficult method reverses the list in place, using one
   local pointer. Start with pointerToLast(). The helper method
   is recursive.
	********************************************************/
   public static ListNode mindBender(ListNode head)
   {
      ListNode temp = pointerToLast(head);
      mindBenderHelper(head);
      head.setNext(null);
      return temp;
   }
   public static void mindBenderHelper(ListNode head)
   {
      
   }
}
/*
//the College Board's standard ListNode class
class ListNode
{
   private Object value;
   private ListNode next;
   public ListNode(Object v, ListNode n)
   {
      value=v;
      next=n;
   }
   public Object getValue()
   {
      return value;
   }
   public ListNode getNext()
   {
      return next;
   }
   public void setValue(Object newv)
   {
      value=newv;
   }
   public void setNext(ListNode newn)
   {
      next=newn;
   }
}
*/