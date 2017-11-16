/**
   A bag is an unordered collection of possibly repeated elements.
*/

import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class Bag
{
   Node first;
   int modCount;

   /**
      A node holds an elements in this bag.
   */
   class Node
   {
      Object data;
      int count;
      Node next;
   }

   /**
      Constructs an empty bag.
   */
   public Bag()
   {
      first = null;
      modCount = 0;
   }

   /**
      Adds an object to this bag.
      @param obj the object to add
   */
   public void add(Object obj)
   {
      Node n = find(obj);
      if (n == null)
      {
         n = new Node();
         n.data = obj;
         n.count = 1;
         n.next = first;
         first = n;
      }
      else
      {
         n.count++;
      }
      modCount ++;
   }

   /**
      Removes an object from this bag.
      @param obj the object to remove
   */
   public void remove(Object obj)
   {
      if (first == null) return;
      if (first.data.equals(obj))
      {
         if (first.count > 1) first.count--;
         else first = first.next;
      }
      else
      {
         Node n = first;
         while (n != null)
         {
            if (n.next != null && n.next.data.equals(obj))
            {
               if (n.next.count > 1) n.next.count--;
               else n.next = n.next.next;
               return;
            }
            else n = n.next;
         }
      }
      modCount ++;
   }

   /**
      Counts how many times an object is contained in this bag.
      @param obj the object to find
      @return the number of times obj is in the bag
   */
   public int contains(Object obj)
   {
      Node n = find(obj);
      if (n == null) return 0;
      else return n.count;
   }

   /**
      Finds an object in this bag.
      @param obj the node containing obj or null
   */
   private Node find(Object obj)
   {
      Node n = first;
      while (n != null)
      {
         if (n.data.equals(obj)) return n;
         n = n.next;
      }
      return n;
   }

   /**
      Yields a string representation of this bag
      @return obj the string representation
   */   
   public String toString()
   {
      String result = "[";
      Node n = first;
      while (n != null)
      {
         for (int i = 0; i < n.count; i++)
         {
            if (result.length() > 1) result += ", ";
            result += n.data;
         }
         n = n.next;
      }
      return result + "]";
   }
   
   private int returnModCount()
   {
	   return this.modCount;
   }

   public BagIterator iterator() { return new BagIterator(); }

   class BagIterator implements Iterator
   {
      private Node previous;
      private Node current;
      private int count;
      private boolean isAfterNext;
      private int modCount; 
      
      public BagIterator()
      {
    	  current = null;
    	  previous = null;
    	  count = 0;
    	  isAfterNext = false;
    	  this.modCount = returnModCount();
      }

      public boolean hasNext()
      {
    	  if (current == null && previous == null) {
    		  return true;
    	  }
    	  if (current.next == null) {
    		  if (count < current.count) {
    			  return true;
    		  }
    		  return false;
    	  } 
    	  return true;
      }

      public Object next()
      {
    	  if (hasNext()) {
    		  if (count == 0 && current == null) {
    			  current = first;
    			  count ++;
    			  return current.data;
    		  } else {
    			  if (count < current.count) {
    				  count ++;
    			  } else {
    				  count = 0;
    			  }
    			  if (count == 0) {
    				  current = current.next;
    				  count ++;
    			  }
    			  return current.data;
    		  }
    	  }
    	  throw new NoSuchElementException();
      }
   
      public void remove()
      {
    	  if (hasNext()) {
    		  if (current == null) {
    			  current.count --;
    			  if (count == current.count) {
    				  first = first.next;
    				  current = first;
    			  }
    		  } else {
    			  current.count --;
    			  if (count == current.count) {
    				  previous.next = current.next;
    				  current = previous.next;
    			  }
    		  }
    	  } else {
    		  throw new NoSuchElementException();
    	  }
      }
   }
}
