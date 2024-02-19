package AlgorithmPackage;

import Utilities.Item;
import Utilities.Knapsack;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TestResult {
    /**
     * will hold the item and how many of the item was used
     * in the final talley
     */
    LinkedHashMap<Item, Integer> itemsUsed;

    /**
     * Name of algorithm being used
     */
    private String algorithmName;
    /**
     * KnapsackGen.Knapsack number being solved
     */
    private Knapsack knapsack;
    /**
     * Value of all items in knapsack after solving
     */
    private double totalValue;
    /**
     * Total time in nanoseconds to perform algorithm
     */
    private long time;

    public TestResult(String algorithmName, Knapsack knapsack, double totalValue, long time) {
        this.algorithmName = algorithmName;
        this.knapsack = knapsack;
        this.totalValue = totalValue;
        this.time = time;
        this.itemsUsed = new LinkedHashMap<>();
    }

    /**
     * O(1)
     * Will add results to the test result for the items used and
     * the number of items for that item
     * @param item is the item that was used
     * @param numberUsed how many of that item were used.
     */
    public void addItemsUsed(Item item, int numberUsed){
        this.itemsUsed.put(item, numberUsed);
    }

    /**
     * Return algorithm name
     * @return algorithm name
     */
    public String getAlgorithmName() {
        return algorithmName;
    }

    /**
     * Return knapsack number
     * @return knapsack number
     */
    public Knapsack getKnapsack() {
        return knapsack;
    }

    /**
     * Return total value calculated by algorithm
     * @return total value
     */
    public double getTotalValue() {
        return totalValue;
    }

    /**
     * Return the time in nanoseconds it took for the algorithm to perform
     * @return time in nanoseconds
     */
    public long getTime() {
        return time;
    }

    /**
     * (O(1)
     * This will hold the items used for the test
     * @return
     */
    public LinkedHashMap<Item, Integer> getItemsUsed(){
        return this.itemsUsed;
    }

    public void adjustTime(long newTime){
        time = newTime;
    }

    public void adjustVal(double newVal) {
        totalValue = newVal;
    }
}
