import KnapsackGenPackage.Knapsack;
import KnapsackGenPackage.KnapsackGenMain;
import KnapsackGenPackage.TerminalOutput;
import SelectingAlgoPackage.Dynamic01KnapSack;
import SelectingAlgoPackage.FractionalBruteForce;
import SelectingAlgoPackage.FractionalGreedy;
import SelectingAlgoPackage.TestResult;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TerminalOutput term = new TerminalOutput();
        term.printIntro();
        KnapsackGenMain m = new KnapsackGenMain(false);
        ArrayList<Knapsack> k = m.getKnapList();
        Dynamic01KnapSack d = new Dynamic01KnapSack();
        FractionalBruteForce fbf = new FractionalBruteForce();
        FractionalGreedy fg = new FractionalGreedy();
        ArrayList<TestResult> dynamicTest = new ArrayList<>();
        ArrayList<TestResult> fbfTest = new ArrayList<>();
        ArrayList<TestResult> fgTest = new ArrayList<>();

        for(Knapsack sack : k){
            dynamicTest.add(d.solveKnapsack(sack));
            fbfTest.add(fbf.solveKnapsack(sack));
            fgTest.add(fg.solveKnapsack(sack));
        }
        int index = 0;
        for(TestResult dt : dynamicTest){
            term.printProblemSet(k.get(index), index + 1, 4);
            term.printTestResults(dt);
            term.printTestResults(fbfTest.get(index));
            term.printTestResults(fgTest.get(index));
            index++;

        }

    }
}
