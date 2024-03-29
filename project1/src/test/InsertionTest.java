package test;

import hashtables.HashNode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class InsertionTest extends HashTableTest {

    private static String FILENAME;
    private final int NUM_OF_INSERTIONS;

    public InsertionTest(int numOfInsertions, long seed, String filename) {
        super(numOfInsertions, seed);

        NUM_OF_INSERTIONS = numOfInsertions;
        FILENAME = filename;
    }

    @Override
    public double runTest(HashType type) {
        this.createHashTable(type);
        this.startTest();

        insertData();
        return finishTest(type);
    }

    @Override
    void writeToFile(double duration, HashType type) {
        try {
            PrintWriter printWriter = new PrintWriter(
                    new FileWriter(FILENAME, true));
            printWriter.printf("%s, %d, %d, %f\n",
                    type.name(), SEED, NUM_OF_INSERTIONS, duration);
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void insertData(){
        for(HashNode node : data){
            hashTable.set(node.key, node.value);
        }
    }
}
