package SelectingAlgoPackage;

import KnapsackGenPackage.Item;
import KnapsackGenPackage.Knapsack;

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

        List<Item> selectedItems = new ArrayList<>();
        TestResult result = new TestResult(algorithmName, knapsack, 0, 0);
        super.startTimer();
        int totalValue = solveKnapsack(knapsack, knapsack.getMaximumCapacity(),
                knapsack.getItems().size(), selectedItems);
        result.adjustTime(super.endTimer());

        for (Item item : selectedItems) {
            result.addItemsUsed(item, 1);
        }
        result.adjustVal(totalValue);
        return result;
    }

    private int solveKnapsack(Knapsack knapsack, int weight, int number, List<Item> selectedItems) {
        // base case for recursive algorithm
        if (number == 0 || weight == 0) {
            return 0;
        }

        Item currentItem = knapsack.getItems().get(number - 1);

        if (currentItem.getWt() > weight) {
            return solveKnapsack(knapsack, weight, number - 1, selectedItems);
        } else {
            List<Item> withItem = new ArrayList<>(selectedItems);
            withItem.add(currentItem);

            int valueWithItem = currentItem.getVal() + solveKnapsack(knapsack, weight - currentItem.getWt(), number - 1, temp);

            List<Item> withoutItem = new ArrayList<>(selectedItems);
            int valueWithoutItem = solveKnapsack(knapsack, weight, number - 1, withoutItem);

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
