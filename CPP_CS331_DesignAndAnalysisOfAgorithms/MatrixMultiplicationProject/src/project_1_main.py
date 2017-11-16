"""
project_1_main.py

This is the main module to run the project.
Run the module from the command line. Two options available.
    Pass a filename. Data will be appended to this file in a csv format.
    Do not pass a filename. Results will be printed.
Prompts will be provided for instructions to execute the different matrix multiplication algorithms.

@Author: Jake Lepere
@Date: 10/25/2017
"""

from project_1_util import *
import timeit
import sys
import os

file_passed = False
if len(sys.argv) > 1:
    file_passed = True
    f = open(sys.argv[1],'a+')
    if os.stat(sys.argv[1]).st_size == 0:
        f.write("Algorithm_Name,Matrix_Size,Execution_Time\n")

print
print "Algorithm Numbers:"
print "[1]: Iterative Matrix Multiplication"
print "[2]: Recursive Matrix Multiplication"
print "[3]: Strassen Matrix Multiplication"
print
print "Enter matrix size i s.t. matrix = (2^i x 2^i), where i is the input."
print

while (1):
    
    # GET ALGORITHM NUMBER
    algorithm_number = raw_input("Enter Algorithm Number: ").strip()
    if algorithm_number != '1' and algorithm_number != '2' and algorithm_number != '3':
        print "Unnaccepted Algorithm Number: " + algorithm_number
        break
    algo_name = ""
    if algorithm_number == '1':
        algo_name = "Iterative"
    elif algorithm_number == '2':
        algo_name = "Recursive"
    elif algorithm_number == '3':
        algo_name = "Strassen"
    
    # GET MATRIX SIZE
    matrix_size = raw_input("Enter Matrix Size: ").strip()
    if not matrix_size.isdigit():
        print "Unnaccepted Matrix Size: " + matrix_size
        break
    matrix_size = int(matrix_size)
    if matrix_size < 0:
        print "Unnaccepted Matrix Size: " + matrix_size
        break
    matrix_size = 2 ** matrix_size
    
    # GET NUMBER OF ITERATIONS
    n = raw_input("Enter Number Of Iterations: ")
    if not n.isdigit():
        print "Unnaccepted Number Of Iterations: " + n
        break
    n = int(n)
    if n <= 0:
        print "Unnaccepted Number Of Iterations: " + n
        break
    
    # PERFORM MATRIX MULTIPLICATIONS N TIMES USING PASSED ALGORITHM WITH MATRICES SIZE 2^I
    A = [[1 for a in range(matrix_size)] for b in range(matrix_size)] 
    B = [[1 for a in range(matrix_size)] for b in range(matrix_size)] 
    for i in range(n):
        if algorithm_number == '1':
            start = timeit.default_timer()
            iterative_matrix_mult(A,B)
            stop = timeit.default_timer()
        elif algorithm_number == '2':
            start = timeit.default_timer()
            divide_and_conquer_matrix_mult(A,B)
            stop = timeit.default_timer()
        elif algorithm_number == '3':
            start = timeit.default_timer()
            strassen_matrix_mult(A,B)
            stop = timeit.default_timer()
        else:
            raise Exception("Algorithm Number Error.")
        time = stop - start
        data = algo_name + "," + str(matrix_size)  + "," + str(time) + "\n"
        if file_passed:
            f.write(data)
        else:
            print data

    # CONTINUE ??
    control = raw_input("Continue? [Y/N]: ")
    if control == 'N' or control == 'n':
        break

