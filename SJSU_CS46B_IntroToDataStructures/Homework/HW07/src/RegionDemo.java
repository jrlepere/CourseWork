public class RegionDemo
{
   public static void main(String[] args)
   {
      System.out.println("Array of length 4: ");
      MergeSorter.sort(new int[4], true);
      System.out.println("Array of length 16: ");
      MergeSorter.sort(new int[16], true);
   }
}
