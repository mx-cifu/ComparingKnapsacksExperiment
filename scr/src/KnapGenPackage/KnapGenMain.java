package KnapGenPackage;

public class KnapGenMain {
    /**
     * This class will be the main handler for intake of CSV files, output of test objects,
     * Passing input files to test generator and receiving an object that will be used to test
     * our KnapSacks. This main will also house the command ot crate new CSV files on demand for
     * further testing.
     */

    public KnapGenMain(boolean newCSVFile){
        //generator 3 csv files on top of the currently supplied three
        if(newCSVFile){
            generateCSV();
        }//
        //initiate the test generator
        //get scv files
        //pass csv files to generator
        //get test object returned
        //pass test object to theTester
        //get testResults object retuned
        //pass test object to TestMatrix
    }

    /**
     * This will creat the 3 extra required CSV files to be added to our test
     */
    private void generateCSV(){

    }
}
