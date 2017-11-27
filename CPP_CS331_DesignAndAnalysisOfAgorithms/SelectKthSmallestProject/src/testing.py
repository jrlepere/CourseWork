'''
Tests the 4 algorithms for correctness.

@Author: Jake Lepere
@Date: 10/20/2017
'''

from algorithms import *
import random as random

if __name__ == '__main__':
    ITERATIONS = 250
    MIN_ARR_SIZE = 10
    MAX_ARR_SIZE = 100
    MIN_VALUE = 0
    MAX_VALUE = 100
    err = False
    for i in range(ITERATIONS):
        arr_size = random.randint(MIN_ARR_SIZE,MAX_ARR_SIZE)
        arr = [random.randint(MIN_VALUE,MAX_VALUE) for i in range(arr_size)]
        k = random.randint(0,arr_size - 1)

        v1 = select_kth_1([arr[i] for i in range(len(arr))],k)
        v2 = select_kth_2([arr[i] for i in range(len(arr))],k)
        v3 = select_kth_3([arr[i] for i in range(len(arr))],k)
        v4 = select_kth_4([arr[i] for i in range(len(arr))],k)

        if not (v1 == v2 == v3 == v4):
            err = True
            break
    if err:
        print "FAIL"
    else:
        print "PASS"
