package tests;

import binary_trees.TreeType;

// 5 - Extreme Left
// 6 - Extreme Right
public class TestCase5_6 extends TestCase{
    private int[] insertions = {
            1000,
            2000,
            3000,
            4000,
            5000
    };

    private String test5 = "TestCase5.csv";
    private double duration5;

    public TestCase5_6() {
        super(6);
        createNewFile(test5);
        createNewFile(test5);
    }

    @Override
    double runTest(TreeType type, long seed, int size) {
        createTree(type);

        startTest();
        for(int i = size - 1; i >= 0; --i){
            tree.insert(i);
        }
        duration5 += finishTest();

        createTree(type);
        startTest();
        for(int i = 0; i < size; ++i){
            tree.insert(i);
        }
        return finishTest();
    }

    public void bulkTests() {
        for (TreeType type : TreeType.values()) {
            int count = 0;
            double duration = 0;
            duration5 = 0;

            for (int insertion : insertions) {
                for (long seed : SEEDS) {
                    duration += runTest(type, seed, insertion);
                    count++;
                }
                writeToFile(duration / count, type, insertion);
                writeToFile(duration5 / count, type, insertion, test5);
            }
        }
    }
}
