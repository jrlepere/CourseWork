import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Median1
{
   public static void main(String[] args)
   {
      int n = Integer.parseInt(args[0]);
      List<Double> lst = new ArrayList<Double>(n);
      for (int i = 1; i <= n; i++) lst.add(Math.random());
      while (lst.size() > 2) {
    	  Double smallest = Collections.min(lst);
    	  Double largest = Collections.max(lst);
    	  lst.remove(smallest);
    	  lst.remove(largest);
      }
      double median = 0;
      if (lst.size() == 2) {
    	  median = (lst.get(0) + lst.get(1)) / 2;
      }
      else if (lst.size() == 1)
      {
	 median = lst.get(0);
      }

      System.out.println("Median: " + median);
   }
}