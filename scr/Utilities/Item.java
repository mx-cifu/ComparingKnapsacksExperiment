package Utilities;

public class Item {
    // this item's value
    private int val;
    // this item's weight
    private int wt;
    private int itemNumber;

    /**
     * Constructor for a new KnapsackGen.Item object
     * @param val value of this item
     * @param wt weight of this item
     */
    public Item(int val, int wt, int itemNumber) {
        this.val = val;
        this.wt = wt;
        this.itemNumber = itemNumber;
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

    /**
     * return the item number for use with terminal output
     * @return
     */
    public int getItemNumber(){
        return itemNumber;
    }

}
