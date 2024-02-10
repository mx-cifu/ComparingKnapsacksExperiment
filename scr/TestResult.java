public class TestResult {

    /**
     * Name of algorithm being used
     */
    private String algorithmName;
    /**
     * KnapsackGen.Knapsack number being solved
     */
    private int knapsackNumber;
    /**
     * Value of all items in knapsack after solving
     */
    private double totalValue;
    /**
     * Total time in nanoseconds to perform algorithm
     */
    private long time;

    public TestResult(String algorithmName, int knapsackNumber, double totalValue, long time) {
        this.algorithmName = algorithmName;
        this.knapsackNumber = knapsackNumber;
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
    public int getKnapsackNumber() {
        return knapsackNumber;
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
