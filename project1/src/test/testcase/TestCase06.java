package test.testcase;

import test.HashType;
import test.RandomTest;

public class TestCase06 extends TestCase{

    private int[] insertions = {
            500,
            1000,
            2000
    };

    public TestCase06() {
        super("TestCase06.csv");

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
