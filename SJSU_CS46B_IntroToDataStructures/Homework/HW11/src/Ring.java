/**
 * This program creates a linked list in a ring fashion
 */
public class Ring
{
   Node start; // The current position

   /**
    * The Node
    */
   class Node
   {
      Node next;
      Node previous;
      Object data;
   }

   /**
      Construct a ring with the given initial size, and all data set to null.
      @param initialSize the initial size of the ring
   */
   public Ring(int initialSize)
   {
      if (initialSize <= 0) throw new IllegalArgumentException();
      
      // Creates an array of Nodes of length initialSize
      Node[] arrayOfNode = new Node[initialSize];
      for (int i = 0; i < arrayOfNode.length; i ++) {
    	  arrayOfNode[i] = new Node();
      }
      
      //Sets the ring
      start = arrayOfNode[0];
      for (int i = 0; i < arrayOfNode.length; i ++) {
    	  if (i == 0) {
    		  //The first node
    		  arrayOfNode[i].next = arrayOfNode[i+1];
    		  arrayOfNode[i].previous = arrayOfNode[arrayOfNode.length-1];
    	  } else if (i == arrayOfNode.length-1) {
    		  //The last node
    		  arrayOfNode[i].previous = arrayOfNode[i-1];
    		  arrayOfNode[i].next = arrayOfNode[0];
    	  } else {
    		  arrayOfNode[i].previous = arrayOfNode[i-1];
    		  arrayOfNode[i].next = arrayOfNode[i+1];
    	  }
      }
   }

   /**
      Moves the current position forward.
   */
   public void forward()
   {
      start = start.next;     
   }

   /**
      Moves the current position backward.
   */
   public void backward()
   {
      start = start.previous;     
   }

   /**
      Gets the current element.
      @return the value
   */
   public Object get()
   {
      return start.data;
   }

   /**
      Sets the current element.
      @param newValue the new value
      @return the old value
   */
   public Object set(Object newValue)
   {
	   Object oldDate = start.data;
       start.data = newValue;   
       return oldDate;
   }

   /**
      Adds an element before the current element.
      @param newValue the value to add
   */
   public void add(Object newValue)
   {
       Node newNode = new Node();
       newNode.data = newValue;
       
       if (size() >= 2) {
    	   start.previous.next = newNode;
    	   newNode.previous = start.previous;
    	   start.previous = newNode;
    	   newNode.next = start;
       } else {
    	   //Of size 1
    	   start.next = newNode;
    	   start.previous = newNode;
    	   newNode.next = start;
    	   newNode.previous = start;
       }
   }
   
   /**
    * Gets the size of the ring
    * @return the size of the ring
    */
   private int size()
   {
	   int count = 1;
	   Node first = start;
	   while (first != start.next) {
		   count ++;
		   start = start.next;
	   }
	   start = start.next;
	   return count;
   }

   /**
      Removes the current element and makes the next element the current one. 
      @return the old value
   */
   public Object remove()
   {
      if (start.next == start) throw new IllegalStateException();
      Object removed = start.data;
      
      if (size() > 2) {
    	  start.previous.next = start.next;
    	  start.next.previous = start.previous;
    	  start = start.next;
      } else {
    	  //Ring of size 2
    	  start = start.next;
    	  start.next = start;
    	  start.previous = start;
      } 
      return removed;
   }
}
