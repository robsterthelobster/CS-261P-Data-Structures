package tests;

import binary_trees.TreeType;

import java.util.Random;

// 0 delete
// 1 create
// 2 insert
// 3 search
public class TestCase0_3 extends TestCase{

    private int[] insertions = {
            5000,
            10000,
            15000,
            20000,
            25000,
            30000,
            35000,
            40000,
            45000,
            50000
    };

    private double duration1, duration2, duration3;
    private String test1 = "TestCase1.csv";
    private String test2 = "TestCase2.csv";
    private String test3 = "TestCase3.csv";

    public TestCase0_3() {
        super(0);
        createNewFile(test1);
        createNewFile(test2);
        createNewFile(test3);
    }

    @Override
    double runTest(TreeType type, long seed, int size) {
        startTest();
        createTree(type);
        duration1 += finishTest();

        Random random = new Random(seed);
        startTest();
        for(int i = 0; i < size; ++i){
            tree.insert(random.nextInt());
        }
        duration2 += finishTest();

        random = new Random(seed);
        startTest();
        for(int i = 0; i < size; ++i){
            tree.search(random.nextInt());
        }
        duration3 += finishTest();

        random = new Random(seed);
        startTest();
        for(int i = 0; i < size; ++i){
            tree.delete(random.nextInt());
        }
        return finishTest();
    }

    public void bulkTests() {
        for (TreeType type : TreeType.values()) {
            for (int numOfInsertions : insertions) {
                int count = 0;
                double duration = 0;
                duration1 = 0;
                duration2 = 0;
                duration3 = 0;
                for (long seed : SEEDS) {
                    duration += runTest(type, seed, numOfInsertions);
                    count++;
                }
                writeToFile(duration1 / count, type, numOfInsertions, test1);
                writeToFile(duration2 / count, type, numOfInsertions, test2);
                writeToFile(duration3 / count, type, numOfInsertions, test3);
                writeToFile(duration / count, type, numOfInsertions);
            }
        }
    }
}
