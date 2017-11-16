Jacob Lepere
CS 431
11/09/2017
VIRTUAL MEMORY SIMULATOR

-- INTRODUCTION --
The goal of this project is to implement a simulator for virtual memory.
The program consists of an OS, CPU, MMU, RAM, VirtualPageTable and TLB.
The TLB entries are evicted based on the FIFO algorithm while the pages are eviceted based on the Clock algorithm.

-- SETUP --
To restore original pg files, a copy of each .pg is created for each run.
This is done by copying original pg files from the /original_hard_disk dir to the /temp_hard_disk dir.
This is a requirement to run the program, so please verify that both directories are present.
This should already be done, but please verify just in case.

-- RUNNING THE PROGRAM --
The program is ran from the command line with a text file as input.
The input file should contain a series of records, where each record is in one of the following two types:
   1) READ INSTRUCTION
      0      <- indicates a read instruction.
      0000   <- address to read in hex.
   2) WRITE INSTRUCTION
      1      <- indicates a write instruction.
      0000   <- address to write in hex.
      value  <- integer value to write.
To run, execute the following from the command line:
   $ javac *.java
   $ java Main path_to_input_file

-- PROGRAM OUTPUT --
The program will read and execute each instruction in the input file and output a csv containing the execution data.
For each read instruction, the data read will be printed to the screen.
The csv consists of the following:
  ADDRESS,READ_OR_WRITE,SOFT_MISS,HARD_MISS,HIT,DIRTY_EVICTION,DATA
  data for instruction 1
  data for instruction 2
  data for instruction 3
  ...
  TOTAL_INSTRUCTIONS,SOFT_MISS_COUNT,HARD_MISS_COUNT,HIT_COUNT,TOTAL_DISK_READ,TOTAL_DISK_WRITE,TOTAL_DISK_ACCESSES
  final data
This absolute path to the csv output file is (absolute path to input file).csv

-- REMARKS --
The most difficult part of this project was the communication between hardware components.
Due to the way the hardway communicates with the os, an OS object was passed to most components in order to 'trap' to the OS.
This allows the OS to handle the necessary flags and perform the necessary procedures.
Also, actually more difficult, was keeping the tlb entries in sync with the virtual page table entries.
Initially, I wanted to make each tlb entry have an index pointing to the actual virtual page table entry,
 but I felt that this went away from the design constraints and what is actually done.
This made it difficult because I then needed to check whether or not bits were set in the virtual page table, and not the tlb,
 and vice versa.
Regardless, I think this was resolved, although it made things a little messy.
Also, another note, it was explained in the program specs that the TLB entries were to be set on a soft miss.
This was done, but I also initialized the TLB entries for the first 8, the size of the tlb, hard misses.
Therefore, the tlb was set for the first 8 possible entries.
I think this is what is done in practice.
Overall, I found this project more difficult than the first, but more rewarding because it help understand the material.
If I were to do the project over, I would spend more time than I did thinking about the classes and how to make them interect.
I made index cards to understand the classes and the has a relationships, but I could have spent more time with flow charts
 for the read and write instructions.
This could have cleaned up the code and helped resolve bugs, because there were definitely a few.

