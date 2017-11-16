public class Trees1
{
   public static void main(String[] args)
   {
      int n = Integer.parseInt(args[0]);
      System.out.println(shapes(n));
   }

   public static long shapes(int n)
   {
      if (n == 0) return 1;
      if (n == 1) return 1;
      long result = 0;
      for (int k = 0; k < n; k++)
      {
         result = result + shapes(k) * shapes(n-k-1);         
      }
      return result;
   }
}