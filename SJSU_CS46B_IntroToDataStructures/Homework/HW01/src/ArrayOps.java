import java.util.ArrayList;

public class ArrayOps
{
   /**
       This method goes through the given array of integers,
       yielding a new ArrayList from the array that contains the
       elements of the original array, with duplicates removed.
       @param theArray, an array of integer
       @return the new ArrayList

   */
   public static ArrayList<Integer> copyArray(int[] anArray)
   {
      ArrayList<Integer> newArrayList = new ArrayList<>();
      for (int i = 0; i < anArray.length; i ++) {
    	  int value = anArray[i];
    	  boolean duplicate = false;
    	  if (i != anArray.length-1) {
    		  for (int j = i+1; j < anArray.length; j ++) {
    			  if (value == anArray[j]) {
    				  duplicate = true;
    			  }
    		  }
    	  }
    	  if (!duplicate) { 
    		  newArrayList.add(anArray[i]);
    	  }
      }
      return newArrayList;
   }

   /**
       This method goes through the given array list of integers,
       yielding a new array from the array list that contains the
       elements of the original array list, with duplicates removed.
       @param theArrayList, an array list of integer
       @return the new array

   */
   public static int[] copyArrayList(ArrayList<Integer> anArrayList)
   {
	   ArrayList<Integer> nonDuplicates = new ArrayList<>();
	   for (int i = 0; i < anArrayList.size(); i ++) {
		   int value = anArrayList.get(i);
		   boolean duplicate = false;
		   if (i != anArrayList.size()-1) {
			   for (int j = i+1; j < anArrayList.size(); j ++) {
				   if (value == anArrayList.get(j)) {
					   duplicate = true;
				   }
			   }
		   }
		   if (!duplicate) {
			   nonDuplicates.add(anArrayList.get(i));
		   }
	   }
	   int[] newArray = new int[nonDuplicates.size()];
	   for (int i = 0; i < newArray.length; i ++) {
		   newArray[i] = nonDuplicates.get(i);
	   }
	   return newArray;
   }
}
