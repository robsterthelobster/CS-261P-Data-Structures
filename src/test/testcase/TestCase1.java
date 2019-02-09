package test.testcase;

import test.HashType;
import test.InsertionTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
    TESTING INSERTION

    Tests up to a load factor of 0.5
 */

public class TestCase1 extends TestCase{

    ArrayList<InsertionTest> tests;
    int[] sizes = {
            1000,
            10000,
            //100000,
            //1000000,
            //10000000
    };

    public TestCase1(){
        super("TestCase1.csv");
        tests = new ArrayList<>();

        for(long seed : SEEDS){
            for(int size : sizes){
                tests.add(new InsertionTest(size, size/2, seed, filename));
            }
        }

        for(InsertionTest test : tests){
            //for(HashType type : HashType.values()){
                test.runTest(HashType.LINEAR);
            //}
        }
    }
}
