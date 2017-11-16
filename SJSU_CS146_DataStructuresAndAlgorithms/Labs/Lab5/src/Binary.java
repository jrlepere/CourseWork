
public class Binary {

	/**
	 * Performs the standard binary search.
	 * @return index where item is found, or -1 if not found.
	 */
	public static <AnyType extends Comparable<? super AnyType>> int binarySearch( AnyType [ ] a, AnyType x) {
		int low = 0, high = a.length - 1;
		while( low <= high )
		{
			int mid=(low+high)/2;
			if( a[ mid ].compareTo( x ) < 0 ) {
				low=mid+1;
			} else if( a[ mid ].compareTo( x ) > 0 ) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	 }
	
	public static void main(String[] args) {
		Integer[] a = {0,1,2,3,4,5,6,7,8,9,10};
		for (Integer i = 0; i < a.length; i ++) {
			System.out.println(Binary.binarySearch(a, i));
		}
	}
}
