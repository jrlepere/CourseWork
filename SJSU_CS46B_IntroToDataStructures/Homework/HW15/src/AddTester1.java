import java.util.*;

/**
   This program tests the AddBinarySearchTree class.
*/
public class AddTester1
{ 
   public static void main(String[] args)
   {  
      AddBinarySearchTree t = new AddBinarySearchTree();
      t.add("A");
      System.out.println(t.toString());
      System.out.println("Expected: ( . A . )");
   }
}