package AlgorithmPackage;

import Utilities.Knapsack;

public abstract class AlgorithmParent {

    /**
     * The start time for the timer
     */
    private long startTime;

    /**
     * Performs the knapsack algorithm on the given knapsack, and returns a SelectingAlgoPackage.TestResult
     * object containing the metadata and results. Should start timer at beginning of method,
     * and return total time in SelectingAlgoPackage.TestResult.
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
    public long endTimer() {
        return System.nanoTime() - this.startTime;
    }
}
