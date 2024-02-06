public abstract class AlgorithmParent {

    // Records the start time
    private long startTime;

    /**
     * Performs the knapsack algorithm on the given knapsack, and returns a TestResult
     * object containing the metadata and results
     * @param knapsack to be solved
     * @return the results of the experiment
     */
    public abstract TestResult solveKnapsack(Knapsack knapsack);

    /**
     * Records the start time in nanoseconds, and stores it in a instance variable
     */
    public void startTimer() {
        this.startTime = System.nanoTime();
    }

    /**
     * Records the end time in nanoseconds, take the difference from the start time,
     * and return the total time the algorithm ran
     * @return algorithm run time
     */
    public float endTimer() {
        return System.nanoTime() - this.startTime;
    }
}
