import java.util.*;

/**
   This class implements a binary search tree whose
   nodes hold objects that implement the Comparable
   interface.
*/
public class SmallestBinarySearchTree
{  
   private Node root;

   /**
      Constructs an empty tree.
   */
   public SmallestBinarySearchTree()
   {  
      root = null;
   }
   
   /**
      Returns the smallest object in this tree
      @return the smallest object or null if the tree is empty
   */
   public Comparable smallest()
   { 
	   	if (root == null) {
	   		return null;
	   	}
	   	return root.smallest();
   }

   /**
      Inserts a new node into the tree.
      @param obj the object to insert
   */
   public void add(Comparable obj) 
   {  
      Node newNode = new Node();
      newNode.data = obj;
      newNode.left = null;
      newNode.right = null;
      if (root == null) root = newNode;
      else root.addNode(newNode);
   }

   /**
      A node of a tree stores a data item and references
      of the child nodes to the left and to the right.
   */

    // Add your Comparable smallest() method to this class...

   private class Node
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
         if (newNode.data.compareTo(data) < 0)
         {  
            if (left == null) left = newNode;
            else left.addNode(newNode);
         }
         else
         {  
            if (right == null) right = newNode;
            else right.addNode(newNode);
         }
      }
      
      public Comparable smallest()
      {
    	  if (this.left == null) {
    		  return this.data;
    	  } else {
    		  return left.smallest();
    	  }
      }
   }
}
