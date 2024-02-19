package Utilities;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class KnapsackGenMain {

        private ArrayList<Knapsack> knapList;


        /**
         * This class will be the main handler for intake of CSV files, output of test objects,
         * Passing input files to test generator and receiving an object that will be used to test
         * our KnapSacks. This main will also house the command ot crate new CSV files on demand for
         * further testing.
         */
        public KnapsackGenMain() throws FileNotFoundException {
            this(false);
        }

        /**
         * This is the main constructor for the knapGenMain
         * @param newCSVFile if true, then it will create new csv files for testing.
         */
        public KnapsackGenMain(boolean newCSVFile) throws FileNotFoundException {
            //generator 3 csv files on top of the currently supplied three
            if (newCSVFile) {
                generateCSV(3);
            }//

            this.knapList = getKnapSackListFromCSV();
        }

    /**
     * O(1)
     * will return a Arraylist of knapsack objects when called
     * @return the knapList object in a arraylist
     */
    public ArrayList<Knapsack> getKnapList(){
            return this.knapList;
        }


        /**
         * BigO(n)
         * O(m * (2n + 17)) where m is the number of tests and n
         * is the quality of items in each test.
         * method to get files from the knapsack file and put them into a Knapsack
         * object to be used by the tester
         * @return an ArrayList full of knapsack test items.
         */
        private ArrayList<Knapsack> getKnapSackListFromCSV() throws FileNotFoundException {
            File dir = new File("data/data_input");
            ArrayList<Knapsack> knapList = new ArrayList<Knapsack>();
            if (dir.isDirectory()) {
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
                        Knapsack knap = new Knapsack(weight, testNumber);
                        for (int i = 0; i < f2.length; i++) {
                            Item item = new Item(Integer.parseInt(f2[i]), Integer.parseInt(f3[i]), i);
                            knap.addItem(item);
                        }//end for loop
                        knapList.add(knap);
                    }
                }
            } else {
                System.out.println("Your file is not a directory");
            }
            return knapList;
        }

        /**
         * O(n * (11 + (20 + 5n)(4))
         * This will create the three extra required CSV files to be added to our test
         * If the files are already created, it will overwrite them with new test.
         */
        private void generateCSV(int numberOfExtraTest) {
            //get a file object

            Random r = new Random();

            for (int i = 0; i < numberOfExtraTest; i++) {
                String fName = "data/data_input/inputs" + (i + 4) + ".csv";
                String name = String.valueOf(i + 4);
                StringWriter csvInput = new StringWriter();
                int weight = 100;// the highest weight will be 30
                //first line test number and weight allowed
                csvInput.append(name + "," + weight + ",\n");
                //get the random number for the first line

                for (int l1 = 0; l1 < i * 5 + 20; l1++) {
                    String l1Num = String.valueOf(r.nextInt(weight + 10));
                    csvInput.append(l1Num + ",");
                }//end for
                csvInput.append("\n");
                for (int l2 = 0; l2 < i * 5 + 20; l2++) {
                    String l2Num = String.valueOf(r.nextInt(weight + 10));
                    csvInput.append(l2Num + ",");
                }
                try {
                    FileWriter file = new FileWriter(fName);
                    file.write(csvInput.toString());
                    file.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }//end try
                System.out.println("File " + name + " write successfully");
            }//end String
    }//end class

}
