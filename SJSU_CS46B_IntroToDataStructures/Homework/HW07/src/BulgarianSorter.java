public class BulgarianSorter
{
   public static void sort(int[] a)
   {
	   while (!isSorted(a)) {
		   for (int i = 0; i < a.length; i ++) {
			   sortThree(a,i);
		   }
	   }
   }

   /**
      Sorts the three elements a[start], a[start + 1], a[start + 2].
      If start > a.length - 3, sorts the one or two remaining elements.
   */
   public static void sortThree(int[] a, int start)
   {	
	   if (start <= a.length-3) {
		   if (a[start+1] < a[start]) {
			   swap(a,start,start+1);
		   }
		   if (a[start+2] < a[start+1]) {
			   swap(a,start+2,start+1);
		   }
		   if (a[start+1] < a[start]) {
			   swap(a,start,start+1);
		   }
	   } else if (start == a.length-2) {
		   if (a[start+1] < a[start]) {
			   swap(a,start,start+1);
		   }
	   }
   }

   /**
      Checks whether the given array is sorted.
      @param a an array
      @return true if the array is sorted
   */
   public static boolean isSorted(int[] a)
   {
	   if (a.length == 0) {
		   return true;
	   } else {
		   int value = a[0];
		   for (int i = 1; i < a.length; i ++) {
			   if (value > a[i]) {
				   return false;
			   }
			   value = a[i];
		   }
		   return true;
	   }
   }

   /**
      Swaps two entries of the array.
      @param a an array
      @param i the first position to swap
      @param j the second position to swap
   */
   private static void swap(int[] a, int i, int j)
   {
      int temp = a[i];
      a[i] = a[j];
      a[j] = temp;
   }
}
