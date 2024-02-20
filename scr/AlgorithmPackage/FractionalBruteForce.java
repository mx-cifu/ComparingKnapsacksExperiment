package AlgorithmPackage;

import Utilities.Item;
import Utilities.Knapsack;

import java.util.ArrayList;

public class FractionalBruteForce extends AlgorithmParent{

    // keeps track of the best max value so far
    private double bestMax;
    private ArrayList<Item> bestCombo;
    private Item fractItem;
    private int amtTaken;



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
        String name = "Fractional Brute Force Algorithm";
        int knapsackWtLeft = knapsack.getMaximumCapacity();
        ArrayList<Item> items = knapsack.getItems();

        bestMax = 0;
        amtTaken = 0;
        fractItem = null;
        // start time
        startTimer();
        TestResult results = new TestResult(name,knapsack,  0, 0);
        // explore the different item combinations possible to retrieve the best max value
        ArrayList<ArrayList<Item>> combinations = new ArrayList<>();
        findBestCombo(items, 0, new ArrayList<>(), 0, knapsackWtLeft, 0);
        for (Item oneItem : bestCombo) {
            results.addItemsUsed(oneItem, oneItem.getWt());
        }
        if(fractItem != null) {
            results.addItemsUsed(fractItem, amtTaken);
        }
        results.adjustVal(Math.round(bestMax * 100.0)/100.0);
        results.adjustTime(super.endTimer());
        return results;
    }

    /**
     * Method to explore recursively the different possible item combinations that can be created while following the
     * knapsack limitations and using the required items
     * NOTE: Due to fractions being infinite, I made the choice to only use fractions to explore the LAST item in each
     * combination as a fraction
     * @param itemsList list of items in the knapsack
     * @param startIdx beginning index to try
     * @param currentCombo current combination of items being used
     * @param currentWt amount of weight that the knapsack is currently holding
     * @param maxCapacity max limit of the knapsack
     */
    private void findBestCombo(ArrayList<Item> itemsList, int startIdx, ArrayList<Item> currentCombo, int currentWt, int maxCapacity, double currentVal) {
        // Establish what the current value of the current combination is
        double currentValue = calculateValue(currentCombo);
        if (currentWt <= maxCapacity) {
            // Check to see what the current value could be when adding a fractional item as the last item
            double bestValueWithFraction = addFractionalItem(itemsList, currentCombo, currentWt, maxCapacity, currentValue);
            if (bestValueWithFraction > bestMax) {
                bestMax = bestValueWithFraction; // Update bestMax with a new better max
                bestCombo = new ArrayList<>(currentCombo); // Update bestCombo with the newest best combo
            }
        }

        // enter into a loop exploring the different item
        for (int idx = startIdx; idx < itemsList.size(); idx++) {
            Item item = itemsList.get(idx);

            if (currentWt + item.getWt() <= maxCapacity) {
                currentCombo.add(item);
                findBestCombo(itemsList, idx + 1, currentCombo, currentWt + item.getWt(),  maxCapacity,
                        currentValue + item.getVal()); // explore what the best val would look like with that item
                currentCombo.remove(currentCombo.size() - 1); // remove the item you just tested
            }

            double potentialVal = calculateFractionalVal(item, maxCapacity-currentWt);
            if (potentialVal > 0) {
                findBestCombo(itemsList, idx + 1, currentCombo, maxCapacity, maxCapacity,currentVal + potentialVal);
            }
        }
    }

    /**
     * Finds the potential fractional value of a given object, along when given the remaining capacity
     * @param item Item to find the value with the capacity limit
     * @param remainCapacity remaining capacity left to fill
     * @return the potential fractional value of the item
     */
    private double calculateFractionalVal(Item item, int remainCapacity) {
        if (item.getWt() <= remainCapacity) {
            return 0;
        }
        double fraction = Math.min(item.getWt(), remainCapacity / (double) item.getWt());
        return fraction * item.getVal();
    }

    /**
     * Private helper method to add a fraction of an item as the last item in a knapsack, finding the best one to add
     * @param items ArrayList of Items associated with the knapsack
     * @param currentCombo current combination of items that is being considered
     * @param currentWt the current weight of the entirety of the knapsack
     * @param maxCapacity the maximum weight capacity of the knapsack
     * @param currentComboValue current total value of the combination of items being considered
     * @return new total value of the ArrayList including the added fractional component
     */
    private double addFractionalItem(ArrayList<Item> items, ArrayList<Item> currentCombo, int currentWt, int maxCapacity, double currentComboValue) {
        double remainingCapacity = maxCapacity - currentWt;
        double bestFractionalValue = 0;
        Item tempFractItem = null;
        int tempAmtTaken = 0;
        // iterate through the items of the knapsack, and exclude consideration of items already present within the
        // current combination of items
        for (Item item : items) {
            if (!currentCombo.contains(item)) {
                double potentialFractionalValue = 0;
                if (item.getWt() > remainingCapacity) {
                    // Get the fractional value of this item if it was added as the last item to the combination
                    potentialFractionalValue = (remainingCapacity / item.getWt()) * item.getVal();
                }
                if (potentialFractionalValue > bestFractionalValue) {
                    bestFractionalValue = potentialFractionalValue; // update the best fractional item value if needed
                    tempFractItem = item;
                    tempAmtTaken = maxCapacity - currentWt;
                }
            }
        }

        if (currentComboValue + bestFractionalValue > bestMax) {
            fractItem = tempFractItem;
            amtTaken = tempAmtTaken;
        }

        return currentComboValue + bestFractionalValue; // Return the total value including the best fractional addition
    }

    /**
     * Private helper method to calculate the total value held by the combination of Items passed in
     * @param items ArrayList of Item objects
     * @return total value of the ArrayList of Items
     */
    private double calculateValue(ArrayList<Item> items) {
        double totalVal = 0;
        for (Item item : items) {
            totalVal += item.getVal();
        }
        return totalVal;
    }


    }



