import java.util.Arrays;

public class MergeSort {

	public static void mergeSort(int[] a, int[] tempArray, int first, int last) {
		
		if (first < last) {
			int mid = (first + last) / 2;
			mergeSort(a, tempArray, first, mid);
			mergeSort(a, tempArray, mid + 1, last);
			merge(a, tempArray, first, mid, last);
		}
		
	}
	
	private static void merge(int[] a, int[] tempArray, int first, int mid, int last) {
		
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
		int[] a = {3,4,1,0,0,4,5,6,9,8,10,4};
		System.out.println(Arrays.toString(a));
		int[] temp = new int[a.length];
		mergeSort(a,temp, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
	}
}
