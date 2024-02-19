import AlgorithmPackage.*;
import Utilities.CSVOutput;
import Utilities.Knapsack;
import Utilities.KnapsackGenMain;
import Utilities.TerminalOutput;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TerminalOutput term = new TerminalOutput();
        term.printIntro();
        KnapsackGenMain m = new KnapsackGenMain(false);
        ArrayList<Knapsack> k = m.getKnapList();

        //01knapSack
        DynamicKnapSack dynamic = new DynamicKnapSack();
        Greedy01KnapSack greedy01 = new Greedy01KnapSack();
        ArrayList<TestResult> O1Results = new ArrayList<>();

        CSVoutput csv = new CSVOutput();


        //fractional
        DynamicKnapSack fractionalDynamic = new DynamicKnapSack(true);
        FractionalBruteForce fractionalBruteForce = new FractionalBruteForce();
        FractionalGreedy fractionalGreedy = new FractionalGreedy();
        ArrayList<TestResult> fracResults = new ArrayList<>();

        //test arrays
        ArrayList<TestResult> dynamicTest = new ArrayList<>();
        ArrayList<TestResult> greedyTest01 = new ArrayList<>();

        ArrayList<TestResult> fractionalDynamicTest = new ArrayList<>();
        ArrayList<TestResult> fractionalBruteForceTest = new ArrayList<>();
        ArrayList<TestResult> fractionalGreedyTest = new ArrayList<>();
        //o1 knapsack
        for(Knapsack sack : k){
            dynamicTest.add(dynamic.solveKnapsack(sack));
            greedyTest01.add(greedy01.solveKnapsack(sack));

            fractionalDynamicTest.add(fractionalDynamic.solveKnapsack(sack));
            fractionalBruteForceTest.add(fractionalBruteForce.solveKnapsack(sack));
            fractionalGreedyTest.add(fractionalGreedy.solveKnapsack(sack));
        }
        csv.addO1(dynamicTest);
        csv.add01(greedyTest01);
        csv.add01(bruteForce);

        csv.addFrac(fractionalBruteForce);
        csv.addFrac(fractionalDynamic);
        csv.addFrac(fractionalGreedyTest);

        csv.createFile();



        term.printTitle("01Knapsack Algorithms");


        //print results for 01Knapsack
        int index = 0;
        for(Knapsack sack : k){
            term.printProblemSet(sack,index + 1,4);
            term.printTestResults(dynamicTest.get(index));
            term.printTestResults(greedyTest01.get(index));
            //two other test go here

            index ++;
        }

        term.printTitle("Fractional Knapsack Algorithms");

        index = 0;
        for(Knapsack sack : k) {
            term.printProblemSet(sack, index + 1, 4);
            term.printTestResults(fractionalDynamicTest.get(index));
            term.printTestResults(fractionalGreedyTest.get(index));
            term.printTestResults(fractionalBruteForceTest.get(index));
                    index++;
        }

        CSVOutput

    }
}
