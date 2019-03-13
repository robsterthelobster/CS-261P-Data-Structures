package tests;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public abstract class TestCase {

    protected int[] insertions = {
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

    protected long SEEDS[];
    private long startTime;
    protected String filename;

    public TestCase(String filename){
        this.filename = filename;
        generateSeeds(333);
    }

    public abstract double runTest(TreeType type);

    void startTest(){
        startTime = System.nanoTime();
    }

    double finishTest(TreeType type, int size){
        double duration = nanoToSeconds(System.nanoTime() - startTime);
        writeToFile(duration, type, size);
        return duration;
    }

    abstract void writeToFile(double duration, TreeType type, int size);

    private double nanoToSeconds(long nanotime){
        return (double) nanotime / 1E9;
    }

    private void generateSeeds(long seed){
        Random random = new Random(seed);
        int SEED_LEN = 10;
        SEEDS = new long[SEED_LEN];
        for(int i = 0; i < SEED_LEN; ++i){
            SEEDS[i] = random.nextLong();
        }
    }

}
