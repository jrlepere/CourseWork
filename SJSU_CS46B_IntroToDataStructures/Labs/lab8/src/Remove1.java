import java.util.ArrayList;
import java.util.LinkedList;

public class Remove1
{
   public static void main(String[] args)
   {
      int n = Integer.parseInt(args[0]);
      LinkedList<Integer> lst = new LinkedList<>();
      for (int i = 1; i <= n; i++) lst.add(i);
      for (int i = 1; i < n; i++) lst.remove(0);
      System.out.println("Last element remaining: " + lst.get(0));
   }
}