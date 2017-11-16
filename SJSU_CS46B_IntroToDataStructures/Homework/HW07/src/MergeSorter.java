/**
   This class sorts an array, using the merge sort 
   algorithm nonrecursively.
*/
public class MergeSorter
{
   /**
      ...
   */
   public static void sort(int[] a, boolean debug)
   {
      int length = 1; // The size of the sorted regions

      // while the entire array hasn't yet been sorted
      while (length <= a.length)
      {
         int start = 0; // sort starts at the beginning of the array

         // for each pair of adjacent regions of the given length
         while (start < a.length-length)
         {
        	 if (start <= a.length-2*length) {
            // merge the pair into a sorted region of size 2 * length
        	 merge(start,start+length-1,start+length*2-1, a, debug);
        	 } else {
        		 // merge all of a together when a is not of power 2
        		merge(start,start+length-1,a.length-1,a,debug);
        	 }
            start += 2*length;
         }
         length *= 2;
      }
   }

   /**
      Merges the array from the given inputs
   */
   public static void merge(int from, int mid, int to, int[] a, boolean debug)
   {
	   if (debug) {
	    	  System.out.println("Merging " + from + "..." + mid 
					  + " and " + (mid + 1) + "..." + to);
	   }
	   
	   // Creates the first and second arrays to merge
	   int[] first = new int[mid - from + 1];
	   int[] second = new int[to - mid];
	   
	   // Sets the values of a to the arrays from the specific merging indexes
	   int iFirst = 0;
	   for (int i = from; i <= mid; i ++) {
		   first[iFirst] = a[i];
		   iFirst ++;
	   }
	   int iSecond = 0;
	   for (int i = mid+1; i <= to; i ++) {
		   second[iSecond] = a[i];
		   iSecond ++;
	   }
	   
	   // Adds the values to a from first and second corresponding to the lowest values
	   iFirst = 0;
	   iSecond = 0;
	   int j = from;
	   while (iFirst < first.length && iSecond < second.length) // stops when one array has ran out of indexes to add to a
	   {
		   //Finds the next smallest of first or second and adds to a
		   if (first[iFirst] < second[iSecond]) {
			   a[j] = first[iFirst];
			   iFirst ++; // increments iFirst
		   } else {
			   a[j] = second[iSecond];
			   iSecond ++; // increments iSecond
		   }
		   j ++; // increments j (index of a)
	   }
	      
	   // Adds the rest of the values to a
	   // One loop will execute while one will not because one index had exceded the length of the array
	   while (iFirst < first.length) {
		   a[j] = first[iFirst];
		   iFirst ++;
		   j ++;
	   }
	   while (iSecond < second.length) {
		   a[j] = second[iSecond];
		   iSecond ++;
		   j ++;
	   }
   }
}

