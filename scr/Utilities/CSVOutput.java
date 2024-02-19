package Utilities;

import AlgorithmPackage.TestResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVOutput {

    private ArrayList<TestResult> testResults;

    public CSVOutput(ArrayList<TestResult> testResults) {
        this.testResults = testResults;
    }


    public void createFile() {
        File sackFile = new File("data/data_Results/Comparing_Knapsacks_Results");
        boolean success = false;

        try {
            boolean fileCreated = sackFile.createNewFile();
            if (fileCreated) {
                System.out.println("File has successfully been created");
            } else {
                System.out.println("File not created; it already exists. ");
            }

            try (FileWriter writeFile = new FileWriter(sackFile)) {
                success = writeResults(writeFile);
            }

        } catch (IOException e) {
            System.out.println("An error has occurred creating or writing to the file!");
            e.printStackTrace();
        }

        if (success) {
            System.out.println("Success! Check CSV for outputted results.");
        }

    }

    private boolean writeResults(FileWriter file) throws IOException {

        long[] timeResults = getTestResultsTime();
        double[] valueResults = getTestResultsValue();
        StringBuilder writtenResults = new StringBuilder();
        writtenResults.append("Time\nn, Brute Force, Dynamic, Greedy");


        for (int idx = 0; idx < timeResults.length; idx++) {

            if (idx % 3 == 0) {
                writtenResults.append("\n");
                writtenResults.append(testResults.get(idx).getKnapsack().getItems().size());
                writtenResults.append(", ");
            }

            writtenResults.append(timeResults[idx]);
            if ((idx + 1) % 3 != 0) {
                writtenResults.append(", ");
            }

        }


        writtenResults.append("\n\nValue\nn, Brute Force, Dynamic, Greedy");

        for (int idx = 0; idx < valueResults.length; idx++) {

            if (idx % 3 == 0) {
                writtenResults.append("\n");
                writtenResults.append(testResults.get(idx).getKnapsack().getItems().size());
                writtenResults.append(", ");
            }

            writtenResults.append(valueResults[idx]);
            if ((idx + 1) % 3 != 0) {
                writtenResults.append(", ");
            }

        }
        file.write(writtenResults.toString());

        return true;
    }

    private long[] getTestResultsTime() {
        long[] testResultsTime = new long[testResults.size()];


        for (int idx = 0; idx < testResultsTime.length; idx++ ) {
            testResultsTime[idx] = testResults.get(idx).getTime();
        }

        return testResultsTime;
    }

    private double[] getTestResultsValue() {
        double[] testResultsValue = new double[testResults.size()];

        for (int idx = 0; idx < testResultsValue.length; idx++) {
            testResultsValue[idx] = testResults.get(idx).getTotalValue();
        }

        return testResultsValue;
    }



    public ArrayList<TestResult> getTestResults() {
        return testResults;
    }

}
