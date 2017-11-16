import java.util.*;

/**
   This class implements a binary search tree whose
   nodes hold objects that implement the Comparable
   interface.
*/
public class AddBinarySearchTree
{  
   private Node root;

   /**
      Constructs an empty tree.
   */
   public AddBinarySearchTree()
   {  
      root = null;
   }
   
   /**
      Inserts a new node into the tree.
      @param obj the object to insert
   */
   public void add(Comparable obj) 
   {  
	   if (find(obj)) {return;}
	   Node newNode = new Node();
	   newNode.data = obj;
	   newNode.left = null;
	   newNode.right = null;
	   Node parent = addNode(root, newNode);
	   if (parent == newNode) {
		   root = newNode;
	   }
   }

   /**
      Add the given new node to the given parent node.
      @param parent The parent node to be added to.
      @param newNode The node to be added.
   */
   public static Node addNode(Node parent, Node newNode)
   {
      if (parent == null) 
      {
    	  return newNode;
      }
      else
      {
    	  int comp = newNode.data.compareTo(parent.data);
    	  if (comp < 0) {
    		  Node n = addNode(parent.left, newNode);
    		  if (n == newNode) {
    			  parent.left = newNode;
    		  }
    	  } else {
    		  //comp can not be 0 because I have guaranteed obj is not already in the tree
    		  // from calling the find method in the original add method. Therefor, comp
    		  // must be > 0 if comp is not < 0
    		  Node n = addNode(parent.right, newNode);
    		  if (n == newNode) {
    			  parent.right = newNode;
    		  }
    	  } 
    	  return parent;
      }
   }

   public String toString()
   {  
      return toString(root);
   }  

   /**
      Produces a string of a node and all of its descendants in sorted order.
      @param parent the root of the subtree to process
   */
   private static String toString(Node parent)
   {  
      if (parent == null) { return ". "; }
      else 
      { 
         return "( " + toString(parent.left) 
            + parent.data + " " 
            + toString(parent.right) + ") "; 
      }
   }

   public boolean find(Comparable obj)
   {
	   Node current = root;
	   while (current != null) {
		   int comp = obj.compareTo(current.data);
		   if (comp < 0) {
			   current = current.left;
		   } else if (comp > 0) {
			   current = current.right;
		   } else {
			   return true;
		   }
	   }
	   return false;
   }
   
   /**
      A node of a tree stores a data item and references
      to the left and right child nodes.
   */
   class Node
   {  
      public Comparable data;
      public Node left;
      public Node right;
   }
}

