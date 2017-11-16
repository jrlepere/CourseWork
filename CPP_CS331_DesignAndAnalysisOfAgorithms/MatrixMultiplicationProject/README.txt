Jake Lepere
10/25/2017

--INTRODUCTION--
The purpose of this project is to test the running time of different matrix multiplication algorithms. The algorithms of interest are:
1) Classic Iterative Matrix Mult
2) Recursive Matrix Mult
3) Strassen Matrix Mult
Each of these algorithms is implemented in python, in the project_1_util.py module. The input to the algorithms is two nxn matrices, where n is a power of two. The design of the main program enforces this requirement.

--RUNNING THE PROGRAM--
The program is run from the command line using the "$ python project_1_main.py <filename>" command, where a filename is optional. If a filename is passed, the data will be written to this file in a csv format. Otherwise, data will be printed on the terminal.
The user will be prompted with instrucions to run the program. They are as follows:
1) Which algorthm to run?
      1 -> Iterative
      2 -> Recursive
      3 -> Strassen
2) What is the instance size i? i defines the size of the matrices as 2^ix2^i for both A and B (AxB=C).
3) How many times to execute?
4) Continue> [y/n]

--RESULTS--
The results are in a csv format, containing the name of the algorithm, the instance size i, and the time taken to execute. It is recommended that this data be opened in a spreadsheet viewer, such as Microsoft Excel. This enables the user to easily create graphs for their work, which is a useful tool to visualize the data over many instance sizes.
