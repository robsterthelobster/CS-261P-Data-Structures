package tests;

import binary_trees.TreeType;

public class TestCase7 extends TestCase{

    private int[] insertions = {
            2000,
            4000,
            6000,
            8000,
            10000
    };

    TestCase7(int test) {
        super(7);
    }

    @Override
    double runTest(TreeType type, long seed, int size) {

        return 0;
    }

    public void bulkTests() {
        for (TreeType type : TreeType.values()) {
            int count = 0;
            double duration = 0;
            for (int insertion : insertions) {
                for (long seed : SEEDS) {
                    duration += runTest(type, seed, insertion);
                    count++;
                }
                writeToFile(duration / count, type, insertion);
            }
        }
    }
}
