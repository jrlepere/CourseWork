import java.util.*;

/**
   This program tests the AddBinarySearchTree class.
*/
public class AddTester3
{ 
   public static void main(String[] args)
   {  
      AddBinarySearchTree t = new AddBinarySearchTree();
      t.add("D");
      t.add("B");
      t.add("A");
      t.add("C");
      t.add("F");
      t.add("E");
      t.add("I");
      t.add("G");
      t.add("H");
      t.add("J");
      System.out.println(t.toString());
      System.out.println("Expected: ( ( ( . A . ) B ( . C . ) ) D ( ( . E . ) F ( ( . G ( . H . ) ) I ( . J . ) ) ) )");
   }
}