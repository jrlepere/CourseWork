SUBMISSION DETAILS
I have included the following in my submissions
- An Excel file documenting the time spent on the project vs the estimated time, bugs found, design/analysis work, GUI sketch.
- Pictures of the GUI Calculator at work.
- Source code. Although I recall this not being a requirement, I felt that it cannot hurt to include this to prove that I did not take pictures of someone elses GUI.

TIME SPENT VS TIME ESTIMATED SUMMARY
I estimated that I would spent 1 hours on design/analysis, 2.5 hours on development and .75 hours on testing, giving me a total of 4.25 hours devoted to the project. In reality, over roughly 3 separate days, I totaled .7667 hours on design/analysis, 2.5 hours on development and .5 on testing, giving me a total time spent of 3.7667. This resulted in an overestimate of .2333 hours in desing/analysis and .25 hours in testing. In total, I overestimated .4833 hours for the project. I could have been influenced by the fact that in lecture it was stated that many times students underestimate their work, so maybe there was unconscious bias to allocate more time. Regardless, it was a unique and rewarding exercise.

BUGS FOUND
- Dividing by 0
- Operator entered before first operand
- 2 periods entered in either operand
- Equals entered before equation complete

RUNNING THE CODE (IF RELEVANT)
From the command line, navigate to the source directory and enter the following commands.
javac *.java
java Calculator
Enjoy

NOTES
Just some notes about my design. I decided that it was quite more complicated than I anticipated to store numbers and continue evaluating expressions with the previously computed value. Therefore, I went with an easier approach where the user entered one operand, than one of 4 operators (+,-,/,*) and the second operand followed by equals. This then computed and displayed the result. After pressing any button (recommended to press CLEAR) the screen will then be cleared of all input and the user can proceed to enter a new equation. This may have been an easy way out in some way, but regardless I think it hit every requirement.

