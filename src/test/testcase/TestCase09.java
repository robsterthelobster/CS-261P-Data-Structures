package test.testcase;


import test.HashType;
import test.RandomTest;

public class TestCase09 extends TestCase{

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

    public TestCase09() {
        super("TestCase09.csv");
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
