Jacob Lepere
02/09/2018
8-PUZZLE PROJECT

INTRODUCTION
The goal of this project is to solve the 8-Puzzle problem from any viable starting instance using the A* algorithm with 2 unique heuristic functions. The program is interactive with the user and can perform the following:
[1]: Program generates a random starting instance and solves.
[2]: User enters a starting instance and program solves.
[3]: Program generates and solves three instances of the problem such that the depth of the solution is >= 10.
For each, the user has the ability to solve the problem at hand using the sum of misplace tiles heuristic or the Manhattan distance heuristic.
The program then outputs the depth of the problem followed a step by step sequence representing the minimum path from initial state to goal state.

RUNNING THE PROJECT
cd src
javac *.java
java Main

TESTING
To test the program, unit tests were generated to solve problems where the depth was known for a small number of initial states. Also, 100 initial states with known depths 2 <= i <= 20 for even i, for each depth (1000 in total), were ran through the A* algorithm with both heuristics. The average number of nodes generated was then calculated by analyzing an output csv from this experiment. This was done on by unit test file located in src. This is documented in the Report.docx file.
