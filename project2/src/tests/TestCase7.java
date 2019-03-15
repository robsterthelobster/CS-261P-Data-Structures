package tests;

import binary_trees.TreeType;

// Perfectly balanced, as all things should be
public class TestCase7 extends TestCase{

    private final int max_level = 13;

    public TestCase7() {
        super(7);
    }

    @Override
    double runTest(TreeType type, long seed, int size) {
        createTree(type);

        int total = (int) Math.pow(2, size) - 1;
        int start = (int) Math.pow(2, size - 2);
        int diff = (int) Math.pow(2, size - 3);
        startTest();

        tree.insert(0);
        insert(start, diff);

        for(int i = 0; i < total/2; ++i){
            tree.search(i);
            tree.search(-i);
        }

        return finishTest();
    }

    public void bulkTests() {
        for (TreeType type : TreeType.values()) {
            double duration = 0;
            for (int insertion = 3; insertion < max_level; ++insertion) {
                for(int i = 0; i < 5; ++i){
                    duration += runTest(type, 0, insertion);
                }
                writeToFile(duration/5, type, insertion);
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
