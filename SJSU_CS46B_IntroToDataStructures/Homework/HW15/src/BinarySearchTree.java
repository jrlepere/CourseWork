import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTree 
{
   private Node root;
	
   public BinarySearchTree() 
   {  
      root = null;
   }
	
   public void add(Comparable obj) 
   {  
      Node newNode = new Node();
      newNode.data = obj;
      newNode.left = null;
      newNode.right = null;
      if (root == null) { root = newNode; }
      else { root.addNode(newNode); }
   }
	 
   class Node
   {  
      public Comparable data;
      public Node left;
      public Node right;

      /**
         Inserts a new node as a descendant of this node.
         @param newNode the node to insert
      */
      public void addNode(Node newNode)
      {  
         int comp = newNode.data.compareTo(data);
         if (comp < 0)
         {  
            if (left == null) { left = newNode; }
            else { left.addNode(newNode); }
         }
         else if (comp > 0)
         {  
            if (right == null) { right = newNode; }
            else { right.addNode(newNode); }
         }
      }
   }
	 
   class SortedIterator implements Iterator 
   {
      private Stack<Node> stack;

      public SortedIterator(Node root) 
      {
         stack = new Stack<>();
         Node current = root;
         while (current != null) {
        	 stack.push(current);
        	 current = current.left;
         }
      }
		
      public boolean hasNext() 
      {
    	  if (stack.isEmpty()) {
    		  return false;
    	  }
    	  return true;
      }

      public Object next() 
      {
    	  if (hasNext()) {
    		  Node n = stack.pop();
    		  Object data = n.data;
    		  n = n.right;
    		  while (n != null) {
    			  stack.push(n);
    			  n = n.left;
    		  }
    		  return data;
    	  } else {
    		  throw new IllegalStateException();
    	  }
      }

      public void remove() 
      {
         throw new UnsupportedOperationException();
      }
		 
   }
	 
   public Iterator iterator() 
   {
      return new SortedIterator(root);
   }
}
