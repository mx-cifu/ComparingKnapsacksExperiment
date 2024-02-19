package AlgorithmPackage;

import AlgorithmPackage.AlgorithmParent;
import AlgorithmPackage.TestResult;
import Utilities.Item;
import Utilities.Knapsack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Greedy01KnapSack extends AlgorithmParent {

    private String name;

    public Greedy01KnapSack() {
        name = "Knapsack 01 Greedy Algorithm";
    }

    /**
     * Performs the knapsack algorithm on the given knapsack, and returns a SelectingAlgoPackage.TestResult
     * object containing the metadata and results. Should start timer at beginning of method,
     * and return total time in SelectingAlgoPackage.TestResult.
     *
     * @param knapsack to be solved
     * @return the results of the experiment
     */
    @Override
    public TestResult solveKnapsack(Knapsack knapsack) {
        // set weight, total value, and list of items
        int knapsackWeight = knapsack.getMaximumCapacity();
        int totalValue = 0;
        ArrayList<Item> itemList = knapsack.getItems();

        // begin timer on algorithm
        super.startTimer();

        // sort item list using comparator, create iterator to iterate through list of sorted items,
        // then create a test result object to return
        Collections.sort(itemList, itemComparator());
        Iterator<Item> iterator = itemList.iterator();
        TestResult result = new TestResult(name, knapsack, 0, 0);

        // iterate through each item, and add if you still have remaining weight and remaining items
        while (knapsackWeight > 0 && iterator.hasNext()) {
            Item item = iterator.next();

            if (knapsackWeight - item.getWt() > 0) {
                knapsackWeight -= item.getWt();
                totalValue += item.getVal();
                result.addItemsUsed(item, item.getWt());
            }
        }

        // add results to test result object, and stop timer
        result.adjustVal(Math.round(totalValue * 100.0) / 100.0);
        result.adjustTime(super.endTimer());
        return result;
    }

    public static Comparator<Item> itemComparator() {

        return new Comparator<Item>() {

            /**
             * Compares its two arguments for order.  Returns a negative integer,
             * zero, or a positive integer as the first argument is less than, equal
             * to, or greater than the second.
             * @param o1 the first object to be compared.
             * @param o2 the second object to be compared.
             * @return a negative integer, zero, or a positive integer as the
             * first argument is less than, equal to, or greater than the
             * second.
             */
            @Override
            public int compare(Item o1, Item o2) {
                return Double.compare((double)o2.getVal() / o2.getWt(), (double)o1.getVal() / o1.getWt());
            }
        };
    }
}
