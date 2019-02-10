package test.testcase;

import test.HashType;
import test.InsertionTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
    TESTING INSERTION

    Tests up to a load factor of 0.5
 */

public class TestCase001 extends TestCase{

    ArrayList<InsertionTest> tests;
    int[] sizes = {
            1000,
            10000,
            100000,
            1000000,
            10000000
    };

    public TestCase001(){
        super("TestCase001.csv");
        tests = new ArrayList<>();

        InsertionTest test;

        for(HashType type : HashType.values()){
            for(int size : sizes){
                int count = 0;
                double duration = 0;
                for(long seed : SEEDS){
                     test = new InsertionTest(size, size/2, seed, filename);
                     duration += test.runTest(type);
                     count++;
                }
                writeToFile(duration/count, type,size/2);
            }
        }
    }

    void writeToFile(double duration, HashType type, int numOfInsertions) {
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
}
