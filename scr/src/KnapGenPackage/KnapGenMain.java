package KnapGenPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class KnapGenMain {

    /**
     * This class will be the main handler for intake of CSV files, output of test objects,
     * Passing input files to test generator and receiving an object that will be used to test
     * our KnapSacks. This main will also house the command ot crate new CSV files on demand for
     * further testing.
     */
    public KnapGenMain() throws FileNotFoundException {
        this(false);
    }

    /**
     * This is the main constructor for the knapGenMain
     * @param newCSVFile Ture if you want to create new test.
     */
    public KnapGenMain(boolean newCSVFile) throws FileNotFoundException {
        //generator 3 csv files on top of the currently supplied three
        if(newCSVFile){
            generateCSV(3);
        }//

        //initiate the test generator
        //get scv files
        ArrayList<csvNode> testList = new ArrayList<>();
        getCSVList(testList);
        //pass csv files to generator
        //get a test object returned
        //a pass test object to theTester
        //get testResults object returned
        //pass test object to TestMatrix
    }

    /**
     * BigO(n)
     * O(m * (2n + 16)) where m is number of tests and n
     * is the quanity of items in each test.
     * method to get files from the knapsack file
     * @param testList array of csvNode objects
     */
    private void getCSVList(ArrayList<csvNode> testList) throws FileNotFoundException {
        File dir = new File("TestInputFolder");
        if(dir.isDirectory()) {
            File[] fileList = dir.listFiles();
            String delimiter = ",";
            for (File f : fileList) {
                if (f.getName() != null && f.getName().contains("inputs")) {
                    Scanner s = new Scanner(f);
                    String firstLine = s.nextLine();
                    String secondLine = s.nextLine();
                    String thirdLine = s.nextLine();
                    s.close();
                    String[] f1 = firstLine.split(delimiter);
                    String[] f2 = secondLine.split(delimiter);
                    String[] f3 = thirdLine.split(delimiter);
                    int testNumber = Integer.parseInt(f1[0]);
                    int weight = Integer.parseInt(f1[1]);
                    int[] row2 = new int[f2.length];
                    int[] row3 = new int[f3.length];
                    for (int i = 0; i < f2.length; i++) {
                        row2[i] = Integer.parseInt(f2[i]);
                        row3[i] = Integer.parseInt(f3[i]);
                    }//end for loop
                    csvNode node = new csvNode(testNumber, weight, row2, row3);
                    testList.add(node);
                }
            }
        }else{
            System.out.println("Your file is not a directory");
        }
    }

    /**
     * This will create the three extra required CSV files to be added to our test
     * If the files are already created it will overwrite them with new test.
     */
    private void generateCSV(int numberOfExtraTest){
        //get a file object

        Random r = new Random();

        for(int i = 0; i < numberOfExtraTest; i++){
            String fName = "TestInputFolder/inputs" + (i + 4) + ".csv";
            String name =  String.valueOf(i + 4);
            StringWriter csvInput = new StringWriter();
            int weight = 100;// the highest weight will be 30
            //first line test number and weight allowed
            csvInput.append(name + "," + weight + ",\n");
            //get the random number for the first line

            for(int l1 = 0; l1 < i * 5 + 20; l1 ++){
                String l1Num = String.valueOf(r.nextInt(weight+ 10));
                csvInput.append(l1Num + ",");
            }//end for
            csvInput.append("\n");
            for(int l2 = 0; l2 < i * 5 + 20; l2++){
                String l2Num = String.valueOf(r.nextInt(weight + 10));
                csvInput.append(l2Num + ",");
            }
            try{
                FileWriter file = new FileWriter(fName);
                file.write(csvInput.toString());
                file.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }//end try
            System.out.println("File " + name + " write successfully");
        }//end String

    }

    public class csvNode{
        private int numberName;
        private int maxiumWeight;
        private int [] profits;
        private int [] weights;

        /**
         * O(1)
         * This method will create a new csvNode class that is to
         * be given to the test for creation of test.
         *
         * @param numberName test number name
         * @param maxiumWeight  the max weight, on first line of csv
         * @param profits that can be expected from each item
         * @param weights that can be expected from each item
         */
        public csvNode (int numberName, int maxiumWeight, int [] profits, int [] weights){
            this.numberName = numberName;
            this.maxiumWeight = maxiumWeight;
            this.profits = profits;
            this.weights = weights;
        }//end constructior

        /**
         * O(1)
         * Method to get the test number
         * @return the test number
         */
        public int getNumberName() {
            return numberName;
        }//end get number

        /**
         * O(1)
         * Method to get the max weight
         * @return max weight as an integer
         */
        public int getMaxiumWeight(){
            return maxiumWeight;
        }

        /**
         * O(1)
         * Method for getting the arrays containing item weights
         * @return the array of weights
         */
        public int[] getWeights(){
            return weights;
        }//end get weights

        /**
         * O(1)
         * Method for getting the array of profits.
         * @return profits
         */
        public int[] getProfits(){
            return profits;
        }
    }
}
