package test.testcase;

import test.HashType;
import test.RandomTest;

public class TestCase07 extends TestCase{
    private int[] insertions = {
            500,
            1000,
            2000,
            5000,
            10000,
            15000,
            20000
    };

    public TestCase07(String filename) {
        super(filename);

        bulkTests();
    }

    public TestCase07() {
        super("TestCase07.csv");

        bulkTests();
    }

    protected void bulkTests(){
        for(HashType type : HashType.values()){
            for(int numOfInsertions : insertions){
                int count = 0;
                double duration = 0;
                for(long seed : SEEDS){
                    RandomTest test = new RandomTest(numOfInsertions, seed, filename);
                    duration += test.runTest(type);
                    count++;
                }
                writeToFile(duration/count, type, numOfInsertions);
            }

        }
    }
}
