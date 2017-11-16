import java.util.Arrays;

public class MergeSortIterative {
	
	public static void mergeSort(int[] a) {
		
		//i = the current size
		for (int i = 1; i < a.length; i = 2*i) {
			
			//j = the left most end of the partition
			for (int j = 0; j < a.length - 1; j += 2*i) {
				
				//mid = the middle of the partition
				int mid = j + i - 1;
				
				//k = the right most end of the partition
				int k = Math.min(j + 2*i - 1, a.length-1);
				
				//merges the array with the given parameters
				merge(a, j, mid, k); 
			}
		}
	}
	
	private static void merge(int[] a, int first, int mid, int last) {
		
		int[] tempArray = new int[a.length];
	
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;
		
		int index = 0;
		
		while ( (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2) ) {
			if (a[beginHalf1] <= a[beginHalf2]) {
				tempArray[index] = a[beginHalf1];
				beginHalf1 ++;
			} else {
				tempArray[index] = a[beginHalf2];
				beginHalf2 ++;
			}
			index ++;
		}
		
		while (beginHalf1 <= endHalf1) {
			tempArray[index] = a[beginHalf1];
			index ++;
			beginHalf1 ++;
		}
		
		while (beginHalf2 <= endHalf2) {
			tempArray[index] = a[beginHalf2];
			index ++;
			beginHalf2 ++;
		}
		
		for (int i = 0; i < index; i ++) {
			a[first] = tempArray[i];
			first ++;
		}
	}

	public static void main(String[] args) {
		int[] a = {12, 60, 32, 44, 30, 99, 3, 39, 15, 18, 100, 5};
		System.out.println(Arrays.toString(a));
		mergeSort(a);
		System.out.println(Arrays.toString(a));
	}
}
