package test.testcase;

import test.*;

/*

    insert and search every key
 */
public class TestCase03 extends TestCase{

    private int[] insertions = {
            1000,
            10000,
            100000
    };

    public TestCase03() {
        super("TestCase03.csv");

        bulkInsertTests(true);
    }

    public TestCase03(String filename, boolean three){
        super(filename);

        bulkInsertTests(three);
    }

    protected void bulkInsertTests(boolean three){
        for(HashType type : HashType.values()){
            for(int numOfInsertions : insertions){
                int count = 0;
                double duration = 0;
                for(long seed : SEEDS){
                    HashTableTest test = three ?
                            new HashTableTest3(numOfInsertions, seed, filename) :
                            new HashTableTest4(numOfInsertions, seed, filename);
                    duration += test.runTest(type);
                    count++;
                }
                writeToFile(duration/count, type, numOfInsertions);
            }

        }
    }
}
