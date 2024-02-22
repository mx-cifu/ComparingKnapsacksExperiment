package Utilities;

import AlgorithmPackage.TestResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVOutput {

    private ArrayList<TestResult> testResults01;
    // holds test results of the fract knapsack problem
    private ArrayList<TestResult> testResultsFract;


    /**
     * Creates and initializes the fields of a CSVOutput object
     */
    public CSVOutput() {
        this.testResults01 = new ArrayList<>();
        this.testResultsFract = new ArrayList<>();

    }



    /**
     * Creates the file, and writes into it. Calling other classes as needed to perform its work. This method must be
     * directly called by the user through the CSVOutput object in order to start the work of writing.
     */
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
                success = writeResults01(writeFile);
                writeFile.write("\n\n");
                success = writeResultsFract(writeFile);
            }

        } catch (IOException e) {
            System.out.println("An error has occurred creating or writing to the file!");
            e.printStackTrace();
        }

        if (success) {
            System.out.println("Success! Check CSV for outputted results.");
        }

    }

    /**
     * Writes the results of the 01 knapsack into the file, formatting it properly and cleanly
     * @param file FileWriter object to perform the writing
     * @return true if successfully written into the file; otherwise, false
     * @throws IOException if the FileWriter object is invalid, this method will throw an exception
     */

    private boolean writeResults01(FileWriter file) throws IOException {

        long[] timeResults = getTestResultsTime(testResults01);
        double[] valueResults = getTestResultsValue(testResults01);
        StringBuilder writtenResults = new StringBuilder();
        writtenResults.append("Time, ");
        for (int idx = 0; idx < 3; idx++) {
            writtenResults.append(testResults01.get(idx).getAlgorithmName());
            writtenResults.append(", ");
        }

        for (int idx = 0; idx < timeResults.length; idx++) {

            if (idx % 3 == 0) {
                writtenResults.append("\n");
                writtenResults.append(testResults01.get(idx).getKnapsack().getItems().size());
                writtenResults.append(", ");
            }

            writtenResults.append(timeResults[idx]);
            if ((idx + 1) % 3 != 0) {
                writtenResults.append(", ");
            }

        }
        writtenResults.append("\n\nValue, ");
        for (int idx = 0; idx < 3; idx++) {
            writtenResults.append(testResults01.get(idx).getAlgorithmName());
            writtenResults.append(", ");
        }

        for (int idx = 0; idx < valueResults.length; idx++) {

            if (idx % 3 == 0) {
                writtenResults.append("\n");
                writtenResults.append(testResults01.get(idx).getKnapsack().getItems().size());
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

    /**
     * Writes into a file the results of the fractional knapsacks, and the different algorithm's results
     * @param file FileWriter object to perform the writing
     * @return true if successfully written; otherwise, false
     * @throws IOException If the FileWriter is invalid, this method will throw an exception
     */
    private boolean writeResultsFract(FileWriter file) throws IOException {

        long[] timeResults = getTestResultsTime(testResultsFract);
        double[] valueResults = getTestResultsValue(testResultsFract);
        StringBuilder writtenResults = new StringBuilder();
        writtenResults.append("Time, ");
        for (int idx = 0; idx < 3; idx++) {
            writtenResults.append(testResultsFract.get(idx).getAlgorithmName());
            writtenResults.append(", ");
        }

        for (int idx = 0; idx < timeResults.length; idx++) {

            if (idx % 3 == 0) {
                writtenResults.append("\n");
                writtenResults.append(testResultsFract.get(idx).getKnapsack().getItems().size());
                writtenResults.append(", ");
            }

            writtenResults.append(timeResults[idx]);
            if ((idx + 1) % 3 != 0) {
                writtenResults.append(", ");
            }

        }

        writtenResults.append("\n\nValue, ");
        for (int idx = 0; idx < 3; idx++) {
            writtenResults.append(testResultsFract.get(idx).getAlgorithmName());
            writtenResults.append(", ");
        }


        for (int idx = 0; idx < valueResults.length; idx++) {

            if (idx % 3 == 0) {
                writtenResults.append("\n");
                writtenResults.append(testResultsFract.get(idx).getKnapsack().getItems().size());
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

    /**
     * Retrieves a long array of the time taken beach of the different algorithms to complete their sortings
     * @param results ArrayList of TestResult
     * @return an array of runtimes
     */

    private long[] getTestResultsTime(ArrayList<TestResult> results) {
        long[] testResultsTime = new long[results.size()];

        for (int idx = 0; idx < testResultsTime.length; idx++ ) {
            testResultsTime[idx] = results.get(idx).getTime();
        }

        return testResultsTime;
    }

    /**
     * Retrieves an array of the profits gained by each of the different algorithms via an ArrayList of TestResults
     * @param results ArrayList of TestResult
     * @return an array of profit
     */

    private double[] getTestResultsValue(ArrayList<TestResult> results) {
        double[] testResultsValue = new double[results.size()];

        for (int idx = 0; idx < testResultsValue.length; idx++) {
            testResultsValue[idx] = results.get(idx).getTotalValue();
        }

        return testResultsValue;
    }

    /**
     * Adds another TestResult object from the 01 Knapsack problems to the algorithm's ArrayList of 01 TestResults
     * @param results 01 TestResult object to add
     */
    public void add01(TestResult results) {
        testResults01.add(results);
    }

    /**
     * Adds another TestResult object from the fractional knapsack problem to the algorithm's ArrayList of fractional TestResults
     * @param results fractional TestResult object to add
     */

    public void addFract(TestResult results) {
        testResultsFract.add(results);
    }


}


