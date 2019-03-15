package tests;

import binary_trees.TreeType;

// Perfectly balanced, as all things should be
public class TestCase7 extends TestCase{

    private int[] insertions = {
            10, 11, 12, 13, 14
    };

    public TestCase7() {
        super(7);
    }

    @Override
    double runTest(TreeType type, long seed, int size) {
        createTree(type);

        startTest();

        tree.insert(0);
        insert((int) Math.pow(2, size - 2), (int) Math.pow(2, size - 3));

        return finishTest();
    }

    public void bulkTests() {
        for (TreeType type : TreeType.values()) {
            double duration = 0;
            for (int insertion : insertions) {
                duration += runTest(type, 0, insertion);
                writeToFile(duration, type, insertion);
            }
        }
    }

    private void insert(int key, int diff){
        tree.insert(key);
        tree.insert(-key);
        if(diff == 0) return;
        insert(key + diff, diff/2);
        insert(-(key + diff), diff/2);
        insert(key - diff, diff/2);
        insert(-(key - diff), diff/2);
    }
}
