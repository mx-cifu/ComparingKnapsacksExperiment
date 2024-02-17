package SelectingAlgoPackage;

import KnapsackGenPackage.Knapsack;

public class TestResult {

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
}
