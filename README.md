# Comparing Knapsacks Experiment

Purpose: To practice and demonstrate proficiency in creating brute force, greedy and dynamic programming algorithms for the fractional knapsack problem and the 01 Knapsack problem.

Skills Used: Java programming

Knowledge Goals: Greedy, Dynamic Programming and their applications




Summary

In this project, your team will implement the following Knapsack problems in these formats and compare their execution times.

01 Knapsack Problem
Brute Force
Greedy
Dynamic Programming
Fractional Knapsack Problem
Brute Force
Greedy
Dynamic Programming (Optional - this is difficult)

To better understand the terminology used, we provide the following definitions to our strategies: 

1: Brute Force: A trial and error method used to and solutions through exhaustive effort. In other words, running through all possible choices to and the most optimal solution. 

2: Greedy Solution: We can assemble a globally optimal solution by making a locally optimal solution, called the greedy choice. In other words, choosing the option that looks best in the current position, without consideration to the results from subproblems. 

3: Dynamic Programming: Like divide-and-conquer, dynamic programming solves problems by combining the solutions to sub-problems. This is especially useful when sub-problems share sub-problems.

In addition to the coding, a Word document final report will be created that will detail the design and experimental results.




Part 1: Designing the Experiment

When you are designing your experiment, you and your team will determine the following:

Design a UML document for your software's organization.
Your inputs will be given to you in the form of a CSV file. You will be expected to ingest the CSV file for each iteration you are testing. You will create 3 more additional input files with random values of sizes 20, 25, and 30.
Decide on reasonable metrics for complexity measurement.
Execution time in microseconds (nanoseconds / 1000)
Number of elements
Decide on how the results will be displayed
Plan on using a plot chart to display these results. You can do this by either using Java libraries or outputting a file and generating them in Excel. Other forms of data visualization may be approved with permission.
Add Experiment Design details into Final Report Document. Include the UML document, specifications on metrics chosen as well as the chosen results display. Discuss reasoning for organization of code.
SUGGESTION for Design: Designing the software, you should either have 7 classes or 2 packages with 4 classes each (preferred). In other words, the latter would be a package called FractionalKnapsack with BruteForce, Dynamic, Greedy, and Main (the executable) in it. The other package would be Knapsack01 with BruteForce, Dynamic, Greedy, and Main in it.
If you don't want to use packages, then have 7 classes: FracK-Brute, FracK-Dynamic, FracK-Greedy, K01-Brute, K01-Dynamic, K01-Greedy, and Main (executable).
There are alternate ways to set this up. Some of you may want to have a class for Item, that is okay. Please discuss with your team about the best design and implement it. Whatever you decide on please make it so I can understand where the relevant code is when grading. Use appropriate names and organization.




Part 2: Java Implementation

Implement the algorithms previously mentioned in Java and perform the experiments with the chosen input lists. Results should be evaluated in terms of the metrics.

The implementation of this is largely up to you. Here are a few things I look for in well-designed implementations:

Follows principles of OOP: Inheritance, Polymorphism, Abstraction, Encapsulation
I'm not checking for each one of these. Obviously, there are situations where none or very few will be used and there are situations where all may be used. Use your discretion.
Well-organized project structure.
This could include multiple packages or possibly not. 
Use of Interfaces.
For any classes where it will be relevant, use of interfaces is a great idea. Not required for this project, but optional.
Easy-to-understand output for testing.
Output for this project will contain two parts: 
Part 1: Evidence of successful execution by printing output from main into Terminal. This should show the following:
Knapsack Number
Knapsack Capacity
Printout of Values
Printout of Weights
Best value for Brute Force
Best value for Dynamic Programming (where applicable)
Best value for Greedy Algorithm
Part 2: Output printed to files that will be used to create charts OR separate Chart visualizations by using Java libraries.
If you output the files to CSV, you can do it in a way so that you can open the CSV files, convert them XLSX files and create your charts directly from what you printed. If you want to print out your values and manipulate their locations to create charts that's fine too.
Use of junit tests is optional but encouraged as well.
Because you will be using charts in this project, some of your output will be in charts. You will either need to do your research around how to create charts in Java or output to CSV files and create charts separately.
Be aware, at some point, the runtime for the Brute Force algorithm may become very long. If you find it will not finish for a threshold of number of items in the knapsack, record this in your report and discuss why. If you cannot produce values for these high knapsack numbers, they will not be expected in your charts. I would suggest waiting at least 5 minutes before considering giving up but it may even take longer.




Part 3: Illustrating and Analyzing Results

In this step, you will provide charts to illustrate the performance of the algorithms. Action items:

Compare the performance of all of the algorithms using plot charts.
Final Report: Include sections on the design, experimental results (charts) and provide comments in your program that discuss the empirical results with the theoretical results. Do your findings meet theoretical expectations?




Examples for charts, yours may looks very different:







Some other items of importance
Use javadoc comments on your functions to explain what they do. You do not have to do this in the class implementations where there is an interface, you can use @inheritDoc.
Be sure your code is clean, legible and easy to read.
Feel free to go above and beyond!  Add new methods and functionality, test in different ways in addition to what you are required to do, be creative! The top grades go to those who go above and beyond. Strive for excellence.
You are not required to create unit tests but are welcome to do so if you wish.
Including the runtime as a comment on algorithms with loops in them will be seen as going above and beyond as well.




Format Requirements
Please ensure you follow the structure as listed above. Use IntelliJ to do your work. Use javadoc comments where appropriate.
Your submissions will be by providing a link to your Team's Github repo where this code is stored.
For your final report, be as professional as possible. I am not putting direct limitations on its length.




Assessment

Please see the Rubric attached for assessment.




Deliverables
Github repo link (includes code and report file)
