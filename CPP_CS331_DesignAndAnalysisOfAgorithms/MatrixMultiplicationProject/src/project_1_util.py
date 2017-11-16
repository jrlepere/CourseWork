"""
project_1_util.py

Provides the following utility functions for the project.
- iterative_matrix_mult
- divide_and_conquer_matrix_mult
- strassen_matrix_mult
- matrix_addition
- matrix_subtraction
These functions are to be called from the main module to run the required iterations for the project.
Note that all of the matrices are expected to be n x n, where n is a multiple of 2. There is no error checking!

@Author: Jake Lepere
@Date: 10/25/2017
"""

def iterative_matrix_mult(A,B):
    '''
    Performs matrix multiplication A x B
    @param A the first matrix
    @param B the second matrix
    @return C = A x B
    '''
    r = len(A)
    C = [[0 for y in range(r)] for x in range(r)]
    for i in range(r):
        for j in range(r):
            v = 0
            for k in range(r):
                v += A[i][k] * B[k][j]
            C[i][j] = v
    return C

def divide_and_conquer_matrix_mult(A,B):
    '''
    Performs matrix multiplication recursively using a divide and conquer technique
    @param A the first matrix
    @param B the second matrix
    @return C = A x B
    '''
    if len(A) == len(B) == 2:
        return iterative_matrix_mult(A,B)
    else:
        l = len(A)
        k = l / 2
        A11 = [[A[r][c] for c in range(0,k)] for r in range(0,k)]
        A12 = [[A[r][c] for c in range(k,l)] for r in range(0,k)]
        A21 = [[A[r][c] for c in range(0,k)] for r in range(k,l)]
        A22 = [[A[r][c] for c in range(k,l)] for r in range(k,l)]
        B11 = [[B[r][c] for c in range(0,k)] for r in range(0,k)]
        B12 = [[B[r][c] for c in range(k,l)] for r in range(0,k)]
        B21 = [[B[r][c] for c in range(0,k)] for r in range(k,l)]
        B22 = [[B[r][c] for c in range(k,l)] for r in range(k,l)]
        C11 = matrix_addition(divide_and_conquer_matrix_mult(A11,B11),divide_and_conquer_matrix_mult(A12,B21)) 
        C12 = matrix_addition(divide_and_conquer_matrix_mult(A11,B12),divide_and_conquer_matrix_mult(A12,B22)) 
        C21 = matrix_addition(divide_and_conquer_matrix_mult(A21,B11),divide_and_conquer_matrix_mult(A22,B21)) 
        C22 = matrix_addition(divide_and_conquer_matrix_mult(A21,B12),divide_and_conquer_matrix_mult(A22,B22)) 
        C = [[0 for c in range(l)] for r in range(l)]
        for r in range(l):
            for c in range(l):
                if r < k and c < k:
                    C[r][c] = C11[r][c]
                elif r < k and c >= k:
                    C[r][c] = C12[r][c-k]
                elif r >= k and c < k:
                    C[r][c] = C21[r-k][c]
                elif r >= k and c >= k:
                    C[r][c] = C22[r-k][c-k]
        return C

def strassen_matrix_mult(A,B):
    '''
    Performs matrix multiplication recursively using strassen's method.
    @param A the first matrix
    @param B the second matrix
    @return C = A x B
    '''
    if len(A) == len(B) == 2:
        return iterative_matrix_mult(A,B)
    else:
        l = len(A)
        k = l / 2
        A11 = [[A[r][c] for c in range(0,k)] for r in range(0,k)]
        A12 = [[A[r][c] for c in range(k,l)] for r in range(0,k)]
        A21 = [[A[r][c] for c in range(0,k)] for r in range(k,l)]
        A22 = [[A[r][c] for c in range(k,l)] for r in range(k,l)]
        B11 = [[B[r][c] for c in range(0,k)] for r in range(0,k)]
        B12 = [[B[r][c] for c in range(k,l)] for r in range(0,k)]
        B21 = [[B[r][c] for c in range(0,k)] for r in range(k,l)]
        B22 = [[B[r][c] for c in range(k,l)] for r in range(k,l)]
        P = strassen_matrix_mult(matrix_addition(A11,A22),matrix_addition(B11,B22))
        Q = strassen_matrix_mult(matrix_addition(A21,A22),B11)
        R = strassen_matrix_mult(A11,matrix_subtraction(B12,B22))
        S = strassen_matrix_mult(A22,matrix_subtraction(B21,B11))
        T = strassen_matrix_mult(matrix_addition(A11,A12),B22)
        U = strassen_matrix_mult(matrix_subtraction(A21,A11),matrix_addition(B11,B12))
        V = strassen_matrix_mult(matrix_subtraction(A12,A22),matrix_addition(B21,B22))
        C11 = matrix_addition(matrix_subtraction(matrix_addition(P,S),T),V)
        C12 = matrix_addition(R,T)
        C21 = matrix_addition(Q,S)
        C22 = matrix_addition(matrix_subtraction(matrix_addition(P,R),Q),U)
        C = [[0 for c in range(l)] for r in range(l)]
        for r in range(l):
            for c in range(l):
                if r < k and c < k:
                    C[r][c] = C11[r][c]
                elif r < k and c >= k:
                    C[r][c] = C12[r][c-k]
                elif r >= k and c < k:
                    C[r][c] = C21[r-k][c]
                elif r >= k and c >= k:
                    C[r][c] = C22[r-k][c-k]
        return C

def matrix_addition(A,B):
    '''
    Adds two matrices.
    @param A the first matrix.
    @param B the second matrix.
    @return C = A + B
    '''
    l = len(A)
    C = [[A[r][c] + B[r][c] for c in range(0,l)] for r in range(0,l)]
    return C

def matrix_subtraction(A,B):
    '''
    Subtracts two matrices.
    @param A the first matrix.
    @param B the second matrix.
    @return C = A - B
    '''
    l = len(A)
    C = [[A[r][c] - B[r][c] for c in range(0,l)] for r in range(0,l)]
    return C

