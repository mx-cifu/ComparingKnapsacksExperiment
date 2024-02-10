import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Knapsack implements Iterable<Item>{

    // items in this knapsack
    private ArrayList<Item> items;
    // capacity of this knapsack
    private int maximumCapacity;
    // order (number) of this knapsack
    private int knapsackNum;

    /**
     * Initializes a new Knapsack item
     * @param maxCapacity maximum capacity of this knapsack
     * @param knapsackNum the number of this knapsack
     */
    public Knapsack(int maxCapacity, int knapsackNum) {
        maximumCapacity = maxCapacity;
        this.knapsackNum = knapsackNum;
        items = new ArrayList<>();

    }

    /**
     * Retrieves the maximum capacity of this knapsack
     * @return maximum capacity
     */
    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    /**
     * Retreives this knapsack's numbers
     * @return knapsack number
     */
    public int getKnapsackNum() {
        return knapsackNum;
    }

    /**
     * Adds a new item into the knapsack's item list
     * @param newItem item to be added
     */
    public void addItem(Item newItem){
        items.add(newItem);
    }

    /**
     * Retrieves the array list of items found within the knapsack
     * @return ArrayList of Items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Item> iterator() {
        return new ItemsIterator();
    }


    private class ItemsIterator implements Iterator<Item> {

        // maintains current position
        private int currentIdx;

        /**
         * initializes the current idx to zero
         */
        public ItemsIterator(){
            currentIdx = 0;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return currentIdx < items.size();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Item next() {
            return items.get(currentIdx++);
        }
    }
}
