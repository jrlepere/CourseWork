import java.util.ArrayList;
import java.util.List;

public class Tree
{  
   private Node root;
   
   /**
      Constructs a tree with one node and no children.
      @param rootData the data for the root
   */
   public Tree(Object rootData)
   {
      root = new Node();
      root.data = rootData;
      root.children = new ArrayList<>();
   }
   
   /**
      Adds a subtree as the last child of the root.
   */
   public void addSubtree(Tree subtree)
   {
      root.children.add(subtree.root);
   }
   
   public int interiorNodes()
   {
	   return root.interiorNodes();
   }
   
   public void print()
   {
      if (root != null) root.print("");
   }
   
   class Node
   {
      public Object data;
      public List<Node> children;
       
      public int interiorNodes()
      {
         if (children.isEmpty()) {
        	 return 0;
         } else {
        	 int total = 0;
        	 for (Node child : children) {
        		 total += child.interiorNodes();
        	 }
        	 return 1 + total;
         }
      }
      
      public void print(String prefix) 
      {
    	  System.out.println(prefix + data.toString());
    	  if (prefix.startsWith("+") || prefix.startsWith("|")) {
    		  prefix = "|  " + prefix;
    	  } else if (!prefix.startsWith("   ")) {
    		  prefix += "+--";
    	  }
    	  for (Node child : children) {
    		  if (children.indexOf(child) != children.size() - 1) {
    			  child.print(prefix);
    		  } else {
    			  child.print("   " + prefix);
    		  }
    	  }
      }
   }
}
