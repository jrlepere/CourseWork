import java.util.NoSuchElementException;

/**
   An implementation of a doubly linked list.
*/
public class LinkedList
{  
   Node first;
   Node last;
   
   /**
    * A pointer for the list
    */
   class Pointer
   {  
      Node position;

      /**
         Constructs a pointer that points to the front
         of the linked list.
      */
      public Pointer()
      {  
         position = first;
      }
      
      /**
         Moves the pointer to the next element.
         @throws NoSuchElementException when the pointer is at
         the past-the-end position.
      */
      public void next()
      {  
    	  if (!hasNext()) {
    		  throw new NoSuchElementException();
    	  }
    	  position = position.next;
      }
      
      /**
         Tests if one can call next on the pointer.
         @return true if there is an element or the past-the-end position
         after the pointer position
      */
      public boolean hasNext()
      {  
    	  return position != null;
      }

      /**
         Gets the object at the pointer.
         @throws NoSuchElementException when the pointer is at
         the past-the-end position.
      */
      public Object get()
      {
         if (position == null) {
        	 throw new NoSuchElementException();
         } 
         return position.data;
      }
      
      /**
         Sets the pointer element to a different value. 
         @param element the element to set
         @throws NoSuchElementException when the pointer is at
         the past-the-end position.
      */
      public void set(Object element)
      {
         if (position == null) {
        	 throw new NoSuchElementException();
         }
         position.data = element;
      }

      /**
         Moves the pointer before the previous element.
         @throws NoSuchElementException when trying to move before the
         first element
      */
      public void previous()
      {  
    	  if (!hasNext()) {
    		  position = last;
    	  } else if (!hasPrevious()) {
    		  throw new NoSuchElementException();
    	  } else {
    		  position = position.previous;
    	  }
      }
      
      /**
         Tests if there is an element before the pointer position.
         @return true if there is an element before the pointer position
      */
      public boolean hasPrevious()
      {   
    	  if (!hasNext()) {
    		  return true;
    	  }
    	  return position.previous != null;
      }

      /**
         Adds an element before the pointer position
         @param element the element to add
      */
      public void add(Object element)
      {  
    	  Node newNode = new Node();
    	  newNode.data = element;
    	  if (!hasNext()) {
    		  //Pointer after the last element
    		  last.next = newNode;
    		  newNode.previous = last;
    		  last = newNode;
    		  position = last;
    	  } else if (!hasPrevious()) {
    		  //Pointer at first position
    		  first.previous = newNode;
    		  newNode.next = first;
    		  first = newNode;
    		  position = first.next;
    	  } else {
    		  //Pointer is in the middle
    		  position.previous.next = newNode;
    		  newNode.previous = position.previous;
    		  newNode.next = position;
    		  position.previous = newNode;
    	  }
      }
      
      /**
         Removes the element at the pointer and moves the pointer
         to the next element.
         @throws NoSuchElementException when the pointer is at
         the past-the-end position.
      */
      public void remove()
      {  
         if (!hasNext()) {
        	 throw new NoSuchElementException();
         }
         if (!hasPrevious()) {
        	 //Removing at the first position
        	 first.next.previous = null;
        	 first = first.next;
        	 position = first;
         } else if (position.next == null) {
        	 //Removing the last element
        	 position.previous.next = null;
        	 last = position.previous;
        	 position = last.next;
         } else {
        	 //Removing in the middle
        	 position.previous.next = position.next;
        	 position.next.previous = position.previous;
         }
      }
   }

   /**
    * A Doubly-Linked Node
    */
   class Node
   {  
      Object data;
      Node next;
      Node previous;
   }

   /** 
      Constructs an empty linked list.
   */
   public LinkedList()
   {  
      first = null;
      last = null;
   }
   
   /**
      Returns a pointer for iterating through this list.
      @return a pointer for iterating through this list
   */
   public Pointer listPointer()
   {  
      return new Pointer();
   }
   
   /**
      Returns the first element in the linked list.
      @return the first element in the linked list
   */
   public Object getFirst()
   {  
      if (first == null) { throw new NoSuchElementException(); }
      return first.data;
   }

   /**
      Removes the first element in the linked list.
      @return the removed element
   */
   public Object removeFirst()
   {  
      if (first == null) { throw new NoSuchElementException(); }
      Object element = first.data;
      first = first.next;
      if (first == null) { last = null; } // List is now empty
      else { first.previous = null; }
      return element;
   }

   /**
      Adds an element to the front of the linked list.
      @param element the element to add
   */
   public void addFirst(Object element)
   {  
      Node newNode = new Node();
      newNode.data = element;
      newNode.next = first;
      newNode.previous = null;
      if (first == null) { last = newNode; }
      else { first.previous = newNode; }
      first = newNode;
   }
   
   /**
      Returns the last element in the linked list.
      @return the last element in the linked list
   */
   public Object getLast()
   {  
      if (last == null) { throw new NoSuchElementException(); }
      return last.data;
   }

   /**
      Removes the last element in the linked list.
      @return the removed element
   */
   public Object removeLast()
   {  
      if (last == null) { throw new NoSuchElementException(); }
      Object element = last.data;
      last = last.previous;
      if (last == null) { first = null; } // List is now empty
      else { last.next = null; }
      return element;
   }

   /**
      Adds an element to the back of the linked list.
      @param element the element to add
   */
   public void addLast(Object element)
   {  
      Node newNode = new Node();
      newNode.data = element;
      newNode.next = null;
      newNode.previous = last;
      if (last == null) { first = newNode; }
      else { last.next = newNode; }
      last = newNode;
   }
}
