package AlgorithmPackage;

import Utilities.Item;
import Utilities.Knapsack;

import java.util.ArrayList;
import java.util.Iterator;

public class DynamicKnapSack extends AlgorithmParent {

    private boolean fracBool;

    /**
     * Set S of n items with benefit b
     * and weight w, max weight mw
     */
    public DynamicKnapSack(){
        this(false);
    }

    /**
     * Set the boolean function for the fractional
     * part
     * @param fracBool
     */
    public DynamicKnapSack(boolean fracBool){
        this.fracBool = fracBool;
    }

    /**
     * Performs the knapsack algorithm on the given knapsack, and returns a SelectingAlgoPackage.TestResult
     * object containing the metadata and results. Should start timer at the beginning of method,
     * and return total time in SelectingAlgoPackage.TestResult.
     * @param sack is the Knapsack to be solved
     * @return the results of the experiment
     */
    @Override
    public TestResult solveKnapsack(Knapsack sack) {
        if(!this.fracBool) {
            ArrayList<Integer> itemsNumbers = new ArrayList<>();
            String sacName = "Dynamic Algorithm";
            //set the test up for right inputs
            int targetWeight = sack.getMaximumCapacity();
            int n = sack.getItems().size();
            int testNum = sack.getKnapsackNum();
            int[] profitArray = new int[n + 1];
            int[] weightArray = new int[n + 1];
            profitArray[0] = 0;
            weightArray[0] = 0;
            int index = 1;
            Iterator<Item> i = sack.iterator();
            //covert the items to arrays of values and weights
            while (i.hasNext()) {
                Item item = i.next();
                profitArray[index] = item.getVal();
                weightArray[index] = item.getWt();
                index++;
            }//

            //run the test
            super.startTimer();
            int[][] x = getxArray(n, targetWeight, profitArray, weightArray);
            itemsNumbers = getItems(x, weightArray, n, targetWeight);
            long timeEnd = super.endTimer();
            TestResult t = new TestResult(sacName, sack, Math.round(x[n][targetWeight] * 100.0)/100.0, endTimer());
            for (Integer num : itemsNumbers) {
                t.addItemsUsed(sack.getItems().get(num - 1), sack.getItems().get(num - 1).getWt());
            }
            return t;
        }else{
            TestResult t = fSolveKnapsack(sack);
            return t;
        }

    }//end class

    /**
     * O(17 + m(3) + mn) where m represents the number of items
     * (broken down to a single weight) and n is for the weight.
     * This method will break apart the items to a single
     * unit of wight and then run them thought the dynamic program to find the
     * optimal solution.
     * @param sack
     * @return
     */
    public TestResult fSolveKnapsack(Knapsack sack){
        ArrayList<fracItem> fItem= fractionalRebuild(sack);
        ArrayList<Integer> itemsNumbers = new ArrayList<>();
        double [] val = new double[fItem.size() + 1];
        int [] itemNum = new int[fItem.size() + 1];

        val[0] = 0;
        itemNum[0] = 0;
        int index = 1;
        for(fracItem item : fItem){
            val[index] = item.getval();
            itemNum [index] = item.getNumber();
            index++;
        }
        super.startTimer();
        double [][] x = getxFracArray(fItem.size(), sack.getMaximumCapacity(),val);
        //work backwards looking for the best values
        boolean done = false;
        int r = fItem.size();
        int c = sack.getMaximumCapacity();
        itemsNumbers = new ArrayList<>();
        while(!done){
            if(r <= 0 || c <= 0){
                done = true;
            }else if(x[r][c] == x[r - 1][c]){
                //move r and c to the next row above
                r--;
            }else{
                //we found a difference in to the higher number
                //find the items we used
                itemsNumbers.add(itemNum[r]);
                // move to the left the amount of weight we just added to sack and up one.
                c--;
                //move up one row
                r--;
            }//end if
        }//end while, assumed that either r or c are 0
        long t = super.endTimer();
        TestResult test = new TestResult("Dynamic Fractional Algorithm", sack, Math.round(x[fItem.size()][sack.getMaximumCapacity()] * 100.0)/100.0, t);

        //build the list for the items used
        int [] count = new int[sack.getItems().size()];
        for(Integer num : itemsNumbers){
            count[num]++;
        }

        //take the count and then add that to the test results
        for(int i = 0; i < count.length; i++){
            if(count[i] > 0){
                int weightUsed = count[i];
                test.addItemsUsed(sack.getItems().get(i),weightUsed );
            }//end if
        }//end for

        return test;

    }//end class

    /**
     * Will return double in the x array instead of integers, then we go thought the painfully process of
     * back tracing looking for the optional solution ugg.
     * @param n number of items broken down single units of weight
     * @param maxWeight the max capacity of the container
     * @param profitArray an array of double profits, this will be the unified per weight
     * @return double matrix with values
     */
    private double [][] getxFracArray(int n, int maxWeight, double [] profitArray) {
        double[][] x = new double[n + 1][ maxWeight + 1];
        for (int row = 1; row < n + 1; row++) {
            for (int column = 1; column < maxWeight + 1; column++) {
                double profit = profitArray[row];

                //just in case the weight pushes us to negative
                //indexes

                if (column - 1 >= 0 ) {
                    double rowUpWeightBack_profit = x[row - 1][column - 1];
                    x[row][column] = Math.max(x[row - 1][column], rowUpWeightBack_profit + profit);
                }else {
                    x[row][column] = x[row - 1][column];
                }
            }//end for
        }//end for
        return x;
    }//end getxArray


    /**
     * O(4 + n/m) where n is the number of items and m is capacity,
     * best case would be O(1) with only one value :)
     * Method will return an arraylist with the item used for making the most profitable
     * sack
     * @param x solved matrix of profits
     * @param weightArray array of weights
     * @param n number of items
     * @param targetWeight capacity
     * @return an array list with the items that are going to be returned.
     */
    private ArrayList<Integer> getItems(int [][] x, int [] weightArray, int n, int targetWeight){
        //choose our numbers
        boolean done = false;
        int r = n;
        int c = targetWeight;
        ArrayList<Integer> includeList = new ArrayList<>();
        while(!done){
            if(r <= 0 || c <= 0){
                done = true;
            }else if(x[r][c] == x[r - 1][c]){
                //move r and c to the next row above
                r--;
            }else{
                //we found a difference in to the higher number
                includeList.add(r); //this is the item/row number to be added

                // move to the left the amount of weight we just added to sack and up one.
                c = c - weightArray[r];
                //move up one row
                r--;
            }//end if
        }//end while, assumed that either r or c are 0
        return includeList;
    }//end getItems

    /**
     * O( 2 + n( n * 4))
     * O( 2 + n( n * 5 (worst case))
     * Big O(n^2)
     * This class will build the matrix array that can be used to solve the
     * 01 knapsack problem
     * @param n is the number of items
     * @param maxWeight is the capacity of the sack
     * @param profitArray is an array of profits
     * @param weightArray is an array of weights
     * @return the matrix
     */
    private int [][] getxArray(int n, int maxWeight, int [] profitArray, int [] weightArray) {
        int[][] x = new int[n + 1][ maxWeight + 1];
        for (int row = 1; row < n + 1; row++) {
            for (int column = 1; column < maxWeight + 1; column++) {
                int profit = profitArray[row];
                int current_weight = weightArray[row];
                //just in case the weight pushes us to negative
                //indexes

                if (column - current_weight >= 0 ) {
                    int rowUpWeightBack_profit = x[row - 1][column - current_weight];
                    x[row][column] = Math.max(x[row - 1][column], rowUpWeightBack_profit + profit);
                }else {
                    x[row][column] = x[row - 1][column];
                }
            }//end for
        }//end for
        return x;
    }//end getxArray


    private ArrayList<fracItem> fractionalRebuild(Knapsack k){
        ArrayList<Item> item = k.getItems();
        ArrayList<fracItem> fItem = new ArrayList<>();
        int itemNumber = 0;
        for(Item i : item){
            int weight = i.getWt();
            for(int j = 0; j < weight; j++){
                fracItem fi = new fracItem();
                fi.setWt(1);
                fi.setVal((double)i.getVal()/weight);
                fi.setNumber(itemNumber);
                fItem.add(fi);
            }//end for
            itemNumber++;
        }//end for
        return fItem;
    }


    /**
     * this class is for breaking out the items for the fractional knapsack problem and solving it dynamically.
     *
     */
    private class fracItem{
        private double val;
        private int wt;
        private int number;

        public fracItem(){
            this.val = 0;
            this.wt = 0;
            this.number = -1;
        }//end farcItem

        /**
         * The method to set the wt
         * @param weight is the item weight
         */
        public void setWt(int weight){
            this.wt = weight;
        }// end setWt

        /**
         * Set the value of the object
         * @param val value of object
         */
        public void setVal(double val){
            this.val = val;
        }//end setVal


        /**
         * Set the number of the object
         * @param number of object
         */
        public void setNumber(int number){
            this.number = number;
        }

        /**
         * The method to get the number of the
         * item
         * @return item number
         */
        public int getNumber() {
            return this.number;
        }//end getNumber

        /**
         * The method to get the weight of the item
         * @return weight
         */
        public int getWt(){
            return this.wt;
        }//end getWt

        /**
         * the method to get the value of the item
         * @return value of the item
         */
        public double getval(){
            return this.val;
        }//end getVal


    }

}


