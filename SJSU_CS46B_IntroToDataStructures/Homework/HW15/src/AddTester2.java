import java.util.*;

/**
   This program tests the AddBinarySearchTree class.
*/
public class AddTester2
{ 
   public static void main(String[] args)
   {  
      AddBinarySearchTree t = new AddBinarySearchTree();
      t.add("B");
      t.add("A");
      t.add("C");
      System.out.println(t.toString());
      System.out.println("Expected: ( ( . A . ) B ( . C . ) )");
   }
}