package test.testcase;

import test.*;

public class TestCase05 extends TestCase{

    private int[] insertions = {
            1000,
            25000,
            50000,
            75000,
            100000,
            //1000000,
            //10000000
    };

    public TestCase05() {
        super("TestCase05.csv");

        bulkTests();
    }

    protected void bulkTests(){
        for(HashType type : HashType.values()){
            for(int numOfInsertions : insertions){
                int count = 0;
                double duration = 0;
                for(long seed : SEEDS){
                    DeleteTest test = new DeleteTest(numOfInsertions, seed, filename);
                    duration += test.runTest(type);
                    count++;
                }
                writeToFile(duration/count, type, numOfInsertions);
            }

        }
    }
}
