public class Item {
    // this item's value
    private int val;
    // this item's weight
    private int wt;

    /**
     * Constructor for a new Item object
     * @param val value of this item
     * @param wt weight of this item
     */
    public Item(int val, int wt) {
        this.val = val;
        this.wt = wt;
    }

    /**
     * Retrieves the value of this item
     * @return item value
     */
    public int getVal() {
        return val;
    }

    /**
     * Retrieves the weight of this item
     * @return item weight
     */
    public int getWt() {
        return wt;
    }
}
