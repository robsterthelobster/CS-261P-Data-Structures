package test.testcase;

import test.HashType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

class TestCase {

    final int SEED_LEN = 10;
    long SEEDS[];

    String filename;
    String averageFilename;

    TestCase(String filename){
        this.filename = filename;
        averageFilename = "Average" + filename;
        createNewFile(filename);
        createNewFile(averageFilename);

        generateSeeds(333);
    }

    private void createNewFile(String filename){
        File file = new File(filename);
        if(file.exists()){
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateSeeds(long seed){
        Random random = new Random(seed);
        SEEDS = new long[SEED_LEN];
        for(int i = 0; i < SEED_LEN; ++i){
            SEEDS[i] = random.nextLong();
        }
    }

    protected void writeToFile(double duration, HashType type, int numOfInsertions) {
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
