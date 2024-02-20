import AlgorithmPackage.*;
import Utilities.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // create classes for performing algorithm tests and producing output
        KnapsackGenMain knapsackGenMain = new KnapsackGenMain(false);
        TerminalOutput terminalOutput = new TerminalOutput();
        CSVOutput csvOutput = new CSVOutput();

        // create algorithm tester class and run tests
        AlgorithmTester algorithmTester = new AlgorithmTester(knapsackGenMain, terminalOutput, csvOutput);
        algorithmTester.runAlgorithms();
    }
}
