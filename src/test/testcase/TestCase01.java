package test.testcase;

import test.HashType;
import test.InsertionCreateTest;
import test.InsertionTest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
    TESTING INSERTION

    tests lots of inserts
    table creation is counted towards time
 */

public class TestCase01 extends TestCase{

    private int[] insertions = {
            //1000,
            //10000,
            //100000,
            //1000000,
            10000000
    };

    public TestCase01(){
        super("TestCase01.csv");

        bulkInsertTests(false);
    }

    public TestCase01(String filename, boolean create){
        super(filename);

        bulkInsertTests(create);
    }

    private void writeToFile(double duration, HashType type, int numOfInsertions) {
        try {
            PrintWriter printWriter = new PrintWriter(
                    new FileWriter(averageFilename, true));
            printWriter.printf("%s, %d, %f\n",
                    type.name(), numOfInsertions, duration);
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void bulkInsertTests(boolean create){
        //for(HashType type : HashType.values()){
        HashType type = HashType.CHAINED;
            for(int numOfInsertions : insertions){
                int count = 0;
                double duration = 0;
                for(long seed : SEEDS){
                    InsertionTest test = create ?
                                new InsertionCreateTest(numOfInsertions, seed, filename) :
                                new InsertionTest(numOfInsertions, seed, filename);
                    duration += test.runTest(type);
                    count++;
                }
                writeToFile(duration/count, type, numOfInsertions);
            }
        //}
    }
}
