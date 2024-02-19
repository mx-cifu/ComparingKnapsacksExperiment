import KnapsackGenPackage.Knapsack;
import KnapsackGenPackage.KnapsackGenMain;
import KnapsackGenPackage.TerminalOutput;
import SelectingAlgoPackage.DynamicKnapSack;
import SelectingAlgoPackage.FractionalBruteForce;
import SelectingAlgoPackage.FractionalGreedy;
import SelectingAlgoPackage.TestResult;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TerminalOutput term = new TerminalOutput();
        term.printIntro();
        KnapsackGenMain m = new KnapsackGenMain(true);
        ArrayList<Knapsack> k = m.getKnapList();

        //01knapSack
        DynamicKnapSack dynamic = new DynamicKnapSack();


        //fractional
        DynamicKnapSack fractionalDynamic = new DynamicKnapSack(true);
        FractionalBruteForce fractionalBruteForce = new FractionalBruteForce();
        FractionalGreedy fractionalGreedy = new FractionalGreedy();

        //test arrays
        ArrayList<TestResult> dynamicTest = new ArrayList<>();

        ArrayList<TestResult> fractionalDynamicTest = new ArrayList<>();
        ArrayList<TestResult> fractionalBruteForceTest = new ArrayList<>();
        ArrayList<TestResult> fractionalGreedyTest = new ArrayList<>();
        //o1 knapsack
        for(Knapsack sack : k){
            dynamicTest.add(dynamic.solveKnapsack(sack));

            fractionalDynamicTest.add(fractionalDynamic.solveKnapsack(sack));
            fractionalBruteForceTest.add(fractionalBruteForce.solveKnapsack(sack));
            fractionalGreedyTest.add(fractionalGreedy.solveKnapsack(sack));
        }

        //print results for 01Knapsack
        int index = 0;
        for(Knapsack sack : k){
            term.printProblemSet(sack,index + 1,4);
            term.printTestResults(dynamicTest.get(index));
            //two other test go here
            index ++;
        }

        index = 0;
        for(Knapsack sack : k) {
            term.printProblemSet(sack, index + 1, 4);
            term.printTestResults(fractionalDynamicTest.get(index));
            term.printTestResults(fractionalGreedyTest.get(index));
            term.printTestResults(fractionalBruteForceTest.get(index));
            index++;
        }
    }
}
