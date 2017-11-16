PROCESS SCHEDULING
The motivation for this project was to grasp the concept of process scheduling by implementing some of the key scheduling algorithms. The algorithms implemented for the project are: First Come First Serve, Shortest Time First, Round Robin with 25 and 50 time quantum, and Lottery. Java was chosen as the language to implement the project, with an emphasis on OO design techniques such as polymorphism and abstract classes for algorithm scalability.

RUNNING THE PROGRAM
The program is ran through the command line with one file passed as input. The input file must be in the following format:
process1_id
process1_burstTime
process1_priority
process2_id
process2_burstTime
process2_priority
.
.
.
Compile all .java files using the ‘$ javac *.java’ command. Next, run the Main class with the ‘$ java Main input.txt’ command, where input.txt is the formatted input file.

OUTPUT
The ‘OS’ runs the processes through each algorithm while extracting data after each transition between processes, as well as before running the scheduler and after all process have completed. These rows make up a matrix of data for each scheduling algorithm.  The transition times for each process are also included at the end of the matrix for each scheduling algorithm, as well as total and average turnaround time. The output csv file has the following format:
input_filename:
cpu_switch_time:
system_time:
scheduling_algorithm_1_name
scheduling_algorithm_1_matrix
scheduling_algorithm_1_turnaround
scheduling_algorithm_2_name
scheduling_algorithm_2_matrix
scheduling_algorithm_2_turnaround
.
.
.
The filename of the output file is input.txt.output.txt, where input.txt is the name of the input file.

ANALYSIS
It is recommended that the output csv file be opened with a spreadsheet application such as Microsoft Excel. One of the main reasons for the matrix representation of the data is for visualization. To take full advantage of this, create a chart of the time vs burst_time for each process. This shows the transitioning between processes quite well, as well as the overall workings of the algorithm. A sample Excel spreadsheet has been provided documenting the recommend approach to analyze the data. A testdata.txt file is present as well.

