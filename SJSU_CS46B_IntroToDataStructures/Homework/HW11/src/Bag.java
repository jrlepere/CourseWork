
/**
 * This list represents a bag containing multiple objects and the amount per object
 */
public class Bag
{
   Node first;

   /**
    * A Node with data and a count
    */
   class Node
   {
      Object data;
      int count;
      Node next;
   }

   /**
    * Constructs an empty bag
    */
   public Bag()
   {
      first = null;
   }

   /**
    * Adds an object to bag
    * @param obj the object
    */
   public void add(Object obj)
   {
	   if (contains(obj) > 0) {
		   Node node = new Node();
		   node = first;
		   boolean found = false;
		   while (!found) {
			   if (node.data.equals(obj)) {
				   node.count ++;
				   found = true;
			   } else {
				   node = node.next;
			   }
		   }
	   } else {
		  Node newNode = new Node();
		  newNode.data = obj;
		  newNode.next = first;
		  newNode.count = 1;
		  first = newNode;
	   }
   }

   /**
    * Removes an object from the bag
    * @param obj the object
    */
   public void remove(Object obj)
   {
	   if (contains(obj) > 0) {
		   Node node = new Node();
		   node = first;
		   Node previous = new Node();
		   boolean found = false;
		   while (!found) {
			   if (node.data.equals(obj)) {
				   node.count --;
				   found = true;
				   if (node.count == 0) {
					   //Removes the node
				       previous.next = node.next;
				   }
			   } else {
				   previous = node;
				   node = node.next;
			   }
		   }
	   }
   }

   /**
    * Gets the amount of times the object is in the bag
    * @param obj the object
    * @return the count of the object
    */
   public int contains(Object obj)
   {
	   Node node = new Node();
	   node = first;
	   while (node != null) {
		   if (node.data.equals(obj)) {
			   return node.count;
		   }
		   node = node.next;
	   }
	   return 0;
   }

   /**
    * Returns the bag as a string
    * @return the bag as a string
    */
   public String toString()
   {
      String result = "[";
      Node node = new Node();
      node = first;
      boolean first = true;
      while (node != null) {
    	  for (int i = 1; i <= node.count; i ++) {
    		  if (first) {
    			  result += node.data;
        		  first = false;
        	  } else {
        		  result += ", " + node.data; 
        	  }
    	  }
    	  
    	  node = node.next;
      }
      return result + "]";
   }
}
