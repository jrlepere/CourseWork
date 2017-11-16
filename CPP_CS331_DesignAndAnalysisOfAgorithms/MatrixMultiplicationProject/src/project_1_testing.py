"""
project_1_testing.py

Tests that the three matrix multiplication algorithms are implemented correctly.

@Author: Jake Lepere
@Date: 10/25/2017
"""

from project_1_util import *

A = []
A.append([3,0,3,1])
A.append([5,3,1,0])
A.append([3,0,3,1])
A.append([1,2,2,5])

B = []
B.append([2,4,5,2])
B.append([1,3,2,1])
B.append([3,0,5,0])
B.append([0,4,1,4])

C = []
C.append([15,16,31,10])
C.append([16,29,36,13])
C.append([15,16,31,10])
C.append([10,30,24,24])

C1 = iterative_matrix_mult(A,B)
C2 = divide_and_conquer_matrix_mult(A,B)
C3 = strassen_matrix_mult(A,B)

if (C == C1 == C2 == C3):
    print "PASS"
else:
    print "FAIL"

