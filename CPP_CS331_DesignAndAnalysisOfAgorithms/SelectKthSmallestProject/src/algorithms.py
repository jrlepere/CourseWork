'''
This module implements the algorithms required for the project.
The algorithms are:
    1) Sort the array, using merge sort, and return the kth element.
    2) Quick sort the array iteratively and return when the pivot position equals k.
    3) Quick sort the array recursively and return when the pivot position equals k.
    4) ...
@Author: Jake Lepere
@Data: 10/20/2017
'''

from merge_sort import *

def select_kth_1(arr,k):
    '''
    Selection Problem Algorithm 1.
    Sorts the input array, using merge sort, and returns the kth smallest element.
    @param arr the input array.
    @param k defines the kth smallest element that we want to find.
    @return the kth smallest element
    '''
    
    def merge_sort(arr):
        '''
        Sorts the input array using merge sort.
        @param arr the input array.
        @return the merged array.
        '''

        # Base case
        if len(arr) <= 1:
            return arr
        
        # Sort the left and right arrays
        k = len(arr)/2
        left_arr = merge_sort([arr[i] for i in range(k)])
        right_arr = merge_sort([arr[i] for i in range(k,len(arr))])
        
        # Merge the sorted arrays
        left_index = 0
        right_index = 0
        arr_index = 0
        while ((left_index < len(left_arr)) and (right_index < len(right_arr))):
            if (left_arr[left_index] <= right_arr[right_index]):
                arr[arr_index] = left_arr[left_index]
                left_index += 1
            else:
                arr[arr_index] = right_arr[right_index]
                right_index += 1
            arr_index += 1
        while (left_index < len(left_arr)):
            arr[arr_index] = left_arr[left_index]
            arr_index += 1
            left_index += 1
        while (right_index < len(right_arr)):
            arr[arr_index] = right_arr[right_index]
            arr_index += 1
            right_index += 1

        # Return the merged array
        return arr

    arr = merge_sort(arr)
    return arr[k]

def select_kth_2(arr,k):
    '''
    Selection Problem Algorithm 1.
    Performs quick sort ITERATIVELY and return the pivot value when the pivot position is k.
    @param arr the input array.
    @param k defines the kth smallest element that we want to find.
    @return the kth smallest element
    '''
   
    def quick_sort(arr,low,high):
        '''
        Performs quick sort on the array. Returns when out pivot is in the kth position.
        @param arr the array.
        @param low the low index.
        @param high the high index.
        @return Throws a QuickSortException when the kth smallest is found. Catch this exception in the calling statement.
        '''
        if (high >= low):
            pivot_point = __partition(arr,low,high)
            if pivot_point == k:
                raise QuickSortException(arr[pivot_point])
            if k < pivot_point:
                quick_sort(arr,low,pivot_point - 1)
            else:
                quick_sort(arr,pivot_point + 1,high)

    try:
        quick_sort(arr,0,len(arr)-1) 
    except QuickSortException as e:
        return e.value
    return None

def select_kth_3(arr,k):
    '''
    Selection Problem Algorithm 3.
    Performs quick sort RECURSIVELY and return the pivot value when the pivot position is k.
    @param arr the input array.
    @param k defines the kth smallest element that we want to find.
    @return the kth smallest element
    '''

    def quick_sort(arr,k):
        '''
        Performs quick sort on the array. Returns when out pivot is in the kth position.
        @param arr the array.
        @param low the low index.
        @param high the high index.
        @return Throws a QuickSortException when the kth smallest is found. Catch this exception in the calling statement.
        '''
        if (len(arr) > 0):
            pivot_point = __partition(arr,0,len(arr)-1)
            if pivot_point == k:
                value = arr[pivot_point]
                raise QuickSortException(value)
            if k < pivot_point:
                quick_sort([arr[i] for i in range(0,pivot_point)],k)
            else:
                quick_sort([arr[i] for i in range(pivot_point + 1,len(arr))],k - (pivot_point + 1))
    
    try:
        quick_sort(arr,k) 
    except QuickSortException as e:
        return e.value
    return None

def select_kth_4(arr,k):
    '''
    Selection Problem Algorithm 1.
    Performs quick sort ITERATIVELY and return the pivot value when the pivot position is k.
    @param arr the input array.
    @param k defines the kth smallest element that we want to find.
    @return the kth smallest element
    '''
   
    def quick_sort(arr,low,high):
        '''
        Performs quick sort on the array. Returns when out pivot is in the kth position.
        @param arr the array.
        @param low the low index.
        @param high the high index.
        @return Throws a QuickSortException when the kth smallest is found. Catch this exception in the calling statement.
        '''
        if (high >= low):
            pivot_point = __mm_partition(arr,low,high)
            if pivot_point == k:
                raise QuickSortException(arr[pivot_point])
            if k < pivot_point:
                quick_sort(arr,low,pivot_point - 1)
            else:
                quick_sort(arr,pivot_point + 1,high)

    try:
        quick_sort(arr,0,len(arr)-1) 
    except QuickSortException as e:
        return e.value
    return None

def __partition(arr,low,high):
    '''
    Partition function for quick sort.
    Used for algorithm 2 and 3.
    Uses the first index as the pivot.
    Arranges the array such that all items to the left of the pivot
     are less than the pivot and all items greater than the pivot 
     are to the right of the pivot.
    @param arr the array.
    @param low the low index.
    @param high the high index.
    @return the index of the pivot after arranging the array.
    '''
    pivot_item = arr[low]
    j = low
    i = low + 1
    while (i <= high):
        if arr[i] < pivot_item:
            j += 1
            helper = arr[i]
            arr[i] = arr[j]
            arr[j] = helper
        i += 1
    helper = arr[low]
    arr[low] = arr[j]
    arr[j] = helper
    return j

def __mm_partition(arr,low,high):
    '''
    Partition using mm rule.
    @param arr the array.
    @param low the low index.
    @param high the high index.
    @return the index of the pivot after arranging the array.
    '''

    n = high - low + 1
    r = 5
    s = n / r
    if n < r:
        return __partition(arr,low,high)

    else:
        # Initialize the subsets
        subsets = []
        for i in range(s):
            subsets.append([])
        for i in range(n):
            if i < s * r:
                subsets[i%s].append((low+i,arr[low+i]))

        # Sort the subsets
        for subset in range(len(subsets)):
            subsets[subset] = merge_sort(subsets[subset])

        # Get the medians
        medians = []
        for subset in subsets:
            medians.append(subset[r/2])
        
        # Sort the medians
        medians = merge_sort_2(medians)

        # Get the median of the medians
        mm = medians[s/2]
        mm_index = mm[0]
    
        # Swap the first and the median for regular partition function
        x = arr[mm_index]
        arr[mm_index] = arr[low]
        arr[low] = x

        # Call regular partition
        return __partition(arr,low,high)

class QuickSortException(Exception):
    '''
    Custom exception to stop running quick sort and get the kth smallest element.
    @attr value the kth smallest value in the array when found
    '''
    def __init__(self, value):
        self.value = value
