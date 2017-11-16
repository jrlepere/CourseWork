import java.util.*;

public class ArrayOpsTester
{
   public static ArrayList<Integer> listOfIntegers(int... values)
   {
      ArrayList<Integer> result = new ArrayList<>();
      for (int v : values) result.add(v);
      return result;
   }

   public static void main(String[] args)
   {
      ArrayList<Integer> input = listOfIntegers(2, 14, 9, 15, 9, 7, 3, 17, 14);
      int[] result = ArrayOps.copyArrayList(input);
      System.out.println(input + " -> " + Arrays.toString(result));
      System.out.println("Expected: [2, 14, 9, 15, 9, 7, 3, 17, 14] -> [2, 14, 9, 15, 7, 3, 17]");
      
      input = listOfIntegers(61, 61);
      result = ArrayOps.copyArrayList(input);
      System.out.println(input + " -> " + Arrays.toString(result));
      System.out.println("Expected: [61, 61] -> [61]");

      input = listOfIntegers(143);
      result = ArrayOps.copyArrayList(input);
      System.out.println(input + " -> " + Arrays.toString(result));
      System.out.println("Expected: [143] -> [143]");

      input = new ArrayList<>();
      result = ArrayOps.copyArrayList(input);
      System.out.println(input + " -> " + Arrays.toString(result));
      System.out.println("Expected: [] -> []");
   }
}