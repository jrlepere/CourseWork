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
      // Complete this for the draft

      return null;
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
	   ArrayList<Integer> copyOfArrayList = new ArrayList<>();
	   for (int i = 0; i < anArrayList.size(); i ++) {
		   copyOfArrayList.add(anArrayList.get(i));
	   }
	   for (int i = 0; i < copyOfArrayList.size(); i ++) {
		   int value = copyOfArrayList.get(i);
		   for (int j = i + 1; j < copyOfArrayList.size(); j ++) {
			   if (value == copyOfArrayList.get(j)) {
				   copyOfArrayList.remove(j);
			   }
		   }
	   }
	   int[] newArray = new int[copyOfArrayList.size()];
	   for(int i = 0; i < newArray.length; i ++){
		   newArray[i] = copyOfArrayList.get(i);
	   }
	   return newArray;
   }
}
