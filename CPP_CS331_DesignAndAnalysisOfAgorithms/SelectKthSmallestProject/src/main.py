'''
The main module for running the project.

n <- reguest array size from user
arr <- size n array of random numbers
for each algorithm:
    for each k = {1,n/4,n/2,3n/4,n}:
        run algorithm 3 times with (arr, k) as input and print the following: "ALGORITHM, N, K, EXECUTION TIME" for each.

@Author: Jake Lepere
@Date: 10/20/2017
'''

from algorithms import *
import random
import timeit

if __name__ == '__main__':
    ITERATIONS_PER_K = 3
    NUMBER_OF_Ks = 5
    NUMBER_OF_ALGORITHMS = 4
    MIN_VALUE = 0
    MAX_VALUE = 4095
    n = int(raw_input("n = "))    
    arr_orig = [random.randint(MIN_VALUE,MAX_VALUE) for i in range(n)]
    print "ALGORITHM,N,K,EXECUTION_TIME"
    for algo_num in range(NUMBER_OF_ALGORITHMS):
        for i in range(NUMBER_OF_Ks):
            k = ((i * n) / (NUMBER_OF_Ks - 1)) - 1
            if k < 0:
                k = 0
            for j in range(ITERATIONS_PER_K):
                arr = [arr_orig[i] for i in range(len(arr_orig))]
                if algo_num == 0:
                    start = timeit.default_timer()
                    select_kth_1(arr,k)
                    stop = timeit.default_timer()
                elif algo_num == 1:
                    start = timeit.default_timer()
                    select_kth_2(arr,k)
                    stop = timeit.default_timer()
                elif algo_num == 2:
                    start = timeit.default_timer()
                    select_kth_3(arr,k)
                    stop = timeit.default_timer()
                elif algo_num == 3:
                    start = timeit.default_timer()
                    select_kth_4(arr,k)
                    stop = timeit.default_timer()
                time = stop - start
                print "select_kth_" + str(algo_num + 1) + "," + str(n) + "," + str(k+1) + "," + str(time)
