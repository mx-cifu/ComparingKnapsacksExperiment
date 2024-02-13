import KnapsackGenPackage.Knapsack;
import KnapsackGenPackage.KnapsackGenMain;
import SelectingAlgoPackage.Dynamic01KnapSack;
import SelectingAlgoPackage.TestResult;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        KnapsackGenMain m = new KnapsackGenMain(false);
        ArrayList<Knapsack> k = m.getKnapList();
        Dynamic01KnapSack d = new Dynamic01KnapSack();
        ArrayList<TestResult> trList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> il = new ArrayList<>();
        for(Knapsack sack : k){
                    trList.add(d.solveKnapsack(sack));
                    il.add(d.getItemsNumbers());
        }
        int index = 0;
        for(TestResult tr: trList){
            System.out.println(tr.getAlgorithmName());
            System.out.println(tr.getTime() + " was the time it took to solve");
            System.out.println(tr.getTotalValue() + " was the total value in the end");
            System.out.println(il.get(index));
            index++;
        }
    }
}
