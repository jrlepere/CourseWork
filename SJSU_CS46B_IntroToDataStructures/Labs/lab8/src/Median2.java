import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Median2
{
   public static void main(String[] args)
   {
      int n = Integer.parseInt(args[0]);
      List<Double> lst = new ArrayList<Double>(n);
      for (int i = 1; i <= n; i++) lst.add(Math.random());
      System.out.println("Median: " + median(lst));
   }

   public static double median(List<Double> lst)
   {
	   Collections.sort(lst);
      while (lst.size() > 2) {
    	  lst.remove(0);
    	  lst.remove(lst.size()-1);
      }
      if (lst.size() == 2) {
    	  return (lst.get(0) + lst.get(1)) / 2;
      }
      else if (lst.size() == 1) {
      return lst.get(0);
      }
      else return 0;
   }
}