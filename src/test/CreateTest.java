package test;

import hashtables.ChainedHashTable;
import hashtables.CuckooHashTable;
import hashtables.LinearHashTable;
import hashtables.QuadraticHashTable;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateTest extends HashTableTest{
    String filename;

    public CreateTest(int size, long seed, String filename) {
        super(size, seed);

        this.filename = filename;
    }

    @Override
    public double runTest(HashType type) {
        this.startTest();
        switch(type){
            case LINEAR:
                hashTable = new LinearHashTable(SIZE);
                break;
            case CHAINED:
                hashTable = new ChainedHashTable(SIZE);
                break;
            case CUCKOO:
                hashTable = new CuckooHashTable(SIZE);
                break;
            case QUADRATIC:
                hashTable = new QuadraticHashTable(SIZE);
                break;
            default:
                System.err.println("Not a valid type");
                System.exit(1);
        }
        return finishTest(type);
    }

    @Override
    void writeToFile(double duration, HashType type) {
        try {
            PrintWriter printWriter = new PrintWriter(
                    new FileWriter(filename, true));
            printWriter.printf("%s, %d, %f\n",
                    type.name(), SEED, duration);
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
