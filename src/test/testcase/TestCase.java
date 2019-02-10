package test.testcase;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class TestCase {

    final int SEED_LEN = 10;
    long SEEDS[];

    String filename;
    String averageFilename;

    public TestCase(String filename){
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
}
