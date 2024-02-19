package AlgorithmPackage;

import Utilities.Item;
import Utilities.Knapsack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BruteForce01Knapsack extends AlgorithmParent{

    private String algorithmName;

    public BruteForce01Knapsack() {
        algorithmName = "Knapsack 01 Brute Force Algorithm";
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
        // set up selected items list, test result object, and begin timer
        List<Item> selectedItems = new ArrayList<>();
        TestResult result = new TestResult(algorithmName, knapsack, 0, 0);
        super.startTimer();

        // begin recursive algorithm to find maximum value
        int totalValue = solveKnapsack(knapsack, knapsack.getMaximumCapacity(),
                knapsack.getItems().size(), selectedItems);
        // stop timer
        result.adjustTime(super.endTimer());

        // add all items from selected items to results, set maximum value, and return results
        for (Item item : selectedItems) {
            result.addItemsUsed(item, 1);
        }
        result.adjustVal(totalValue);
        return result;
    }

    /**
     * Recursive method that checks every possible combination of items in the knapsack, and
     * returns the maximum value of all possible knapsacks with the items used.
     * @param knapsack knapsack object
     * @param weight remaining weight in knapsack
     * @param number number of items in knapsack
     * @param selectedItems items that were used in the knapsack
     * @return maximum value for a knapsack configuration
     */
    private int solveKnapsack(Knapsack knapsack, int weight, int number, List<Item> selectedItems) {
        // base case for recursive algorithm if no more items to check
        if (number == 0 || weight == 0) {
            return 0;
        }

        // set current item as last item in knapsack
        Item currentItem = knapsack.getItems().get(number - 1);

        // if item does not fit in knapsack, check all other items in knapsack
        if (currentItem.getWt() > weight) {
            return solveKnapsack(knapsack, weight, number - 1, selectedItems);
        // if item does fit, check if combinations with the item or without the item produce greater value
        } else {
            // see maximum value that selected items and current item can produce
            List<Item> withItem = new ArrayList<>(selectedItems);
            withItem.add(currentItem);
            int valueWithItem = currentItem.getVal() + solveKnapsack(knapsack, weight - currentItem.getWt(), number - 1, withItem);

            // see maximum value that selected items without current item produce
            List<Item> withoutItem = new ArrayList<>(selectedItems);
            int valueWithoutItem = solveKnapsack(knapsack, weight, number - 1, withoutItem);

            // set the selected items to the combination that returned the maximum value, and return the maximum value
            if (valueWithItem > valueWithoutItem) {
                selectedItems.clear();
                selectedItems.addAll(withItem);
                return valueWithItem;
            } else {
                selectedItems.clear();
                selectedItems.addAll(withoutItem);
                return valueWithoutItem;
            }
        }
    }
}
