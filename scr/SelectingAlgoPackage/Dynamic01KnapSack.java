package SelectingAlgoPackage;

import KnapsackGenPackage.Item;
import KnapsackGenPackage.Knapsack;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Dynamic01KnapSack extends AlgorithmParent {
    private ArrayList<Integer> itemsNumbers;


    /**
     * Set S of n items with benefit b
     * and weight w, max weight mw
     */
    public void Dynamic01KnapSack(){

    }

    public ArrayList<Integer> getItemsNumbers(){
        return this.itemsNumbers;
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
        String sacName = "Dynamic Algorithm";
        //set the test up for right inputs
        int targetWeight = sack.getMaximumCapacity();
        int n = sack.getItems().size();
        int testNum = sack.getKnapsackNum();
        int [] profitArray = new int [n + 1];
        int [] weightArray = new int [n + 1];
        profitArray[0] = 0;
        weightArray[0] = 0;
        int index = 1;
        Iterator<Item> i = sack.iterator();
        //covert the items to arrays of values and weights
        while(i.hasNext()){
            Item item = i.next();
            profitArray[index] = item.getVal();
            weightArray[index] = item.getWt();
            index++;
        }//

        //run the test
        super.startTimer();
        int [][] x = getxArray(n, targetWeight, profitArray, weightArray);
        this.itemsNumbers = getItems(x, weightArray, n, targetWeight);
        long timeEnd = super.endTimer();
        TestResult t = new TestResult(sacName, sack.getKnapsackNum(), x[n][targetWeight],endTimer());
        return t;
    }//end class


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

}
