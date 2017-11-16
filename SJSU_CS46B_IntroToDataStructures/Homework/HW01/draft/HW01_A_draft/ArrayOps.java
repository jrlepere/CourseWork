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
        ArrayList<Integer> copyArray = new ArrayList<>();
        for (int value : anArray)
        {
            copyArray.add(value);
        }
        for (int i = 0; i < copyArray.size(); i ++)
        {
            int value = copyArray.get(i);
            for (int k = i + 1; k < copyArray.size(); k ++)
            {
                if (value == copyArray.get(k))
                {
                    copyArray.remove(k);
                    k = i;
                }
            }
        }
        return copyArray;
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
        // Complete this for the final version

        return null;
    }
}
