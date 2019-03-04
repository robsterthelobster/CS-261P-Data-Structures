package test.testcase;

import test.CreateTest;
import test.HashType;
import test.InsertionCreateTest;
import test.InsertionTest;

/*
    create a bunch of tables tests
 */
public class TestCase00 extends TestCase{

    private int[] insertions = {
            1000,
            10000,
            100000
    };

    public TestCase00() {
        super("TestCase00.csv");

        bulkCreate();
    }
    private void bulkCreate(){
        for(HashType type : HashType.values()){
            //HashType type = HashType.CHAINED;
            for(int numOfInsertions : insertions){
                int count = 0;
                double duration = 0;
                for(long seed : SEEDS){
                    CreateTest test = new CreateTest(numOfInsertions, seed, filename);
                    duration += test.runTest(type);
                    count++;
                }
                writeToFile(duration/count, type, numOfInsertions);
            }
        }
    }
}
