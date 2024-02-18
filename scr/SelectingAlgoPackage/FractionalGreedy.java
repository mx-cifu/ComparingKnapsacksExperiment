package SelectingAlgoPackage;

import KnapsackGenPackage.Item;
import KnapsackGenPackage.Knapsack;

import java.util.*;

public class FractionalGreedy extends AlgorithmParent {



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

        //set up
        String name = "Fractional Greedy Algorithm";
        int knapsackWtLeft = knapsack.getMaximumCapacity();
        double totalVal = 0;
        ArrayList<Item> itemsList = knapsack.getItems();

        // start timer
        super.startTimer();
        Collections.sort(itemsList, createRatioComparator());
        Iterator<Item> iterator = itemsList.iterator();
        TestResult results = new TestResult(name, knapsack,0, 0);

        // while there are still items in the iterator, continue below
        while (iterator.hasNext() && knapsackWtLeft > 0) {
            Item oneItem = iterator.next();
            int oneItemVal = oneItem.getVal();
            int oneItemWt = oneItem.getWt();

            // if the next item can be fit entirely, add it, otherwise add a fraction
            if (knapsackWtLeft - oneItemWt >= 0) {
                knapsackWtLeft -= oneItemWt;
                totalVal += oneItemVal;
                results.addItemsUsed(oneItem, oneItemWt);
            } else {
                double amtToTake = (double)knapsackWtLeft/oneItemWt;
                totalVal += oneItemVal * amtToTake;
                results.addItemsUsed(oneItem, knapsackWtLeft);
                knapsackWtLeft = 0;
            }
        }
        results.adjustVal(Math.round(totalVal * 100.0) / 100.0);
        results.adjustTime(super.endTimer());
        return results;
    }

    /**
     * Retrieves a comparator that can be used for Item comparison
     * @return Comparator for item
     */
    public static Comparator<Item> createRatioComparator() {
        return new Comparator<Item>() {

            /**
             * method to perform a comparison on two Item objects
             * @param o1 the first object to be compared.
             * @param o2 the second object to be compared.
             * @return integer indicating position
             */
            @Override
            public int compare(Item o1, Item o2) {
                return Double.compare((double)o2.getVal()/o2.getWt(), (double)o1.getVal()/o1.getWt());
            }
        };
    }
}
