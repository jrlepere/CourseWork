import java.util.Random;

public class Trees2
{
   private static Random generator = new Random();
   
   public static void main(String[] args)
   {
       BinaryTree t = randomTree(6);
       t.print();
   }

   public static BinaryTree randomTree(int n)
   {
      if (n == 0) return new BinaryTree();
      if (n == 1) return new BinaryTree(n);
      int k = generator.nextInt(n);
      return new BinaryTree(n, randomTree(n-1), randomTree(n-1-k));
   }
}