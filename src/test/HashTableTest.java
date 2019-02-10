package test;

import hashtables.*;

import java.util.ArrayList;
import java.util.Random;

public abstract class HashTableTest {

    private final int SIZE;
    final long SEED;

    ArrayList<HashNode> data;
    private long startTime;
    HashTable hashTable;

    HashTableTest(int size, long seed){
        SEED = seed;
        SIZE = size;
        generateData();
    }

    public abstract double runTest(HashType type);

    void startTest(){
        startTime = System.nanoTime();
    }

    double finishTest(HashType type){
        double duration = nanoToSeconds(System.nanoTime() - startTime);
        writeToFile(duration, type);
        return duration;
    }

    abstract void writeToFile(double duration, HashType type);

    void createHashTable(HashType type){
        switch(type){
            case LINEAR:
                hashTable = new LinearHashTable();
                break;
            case CHAINED:
                hashTable = new ChainedHashTable();
                break;
            case CUCKOO:
                hashTable = new CuckooHashTable();
                break;
            case QUADRATIC:
                hashTable = new QuadraticHashTable();
                break;
            default:
                System.err.println("Not a valid type");
                System.exit(1);
        }
    }

    private void generateData(){
        data = new ArrayList<>();
        Random random = new Random(SEED);
        for(int i = 0; i < SIZE; ++i){
            HashNode hashNode = new HashNode(random.nextInt(), i);
            data.add(hashNode);
        }
    }

    private double nanoToSeconds(long nanotime){
        return (double) nanotime / 1E9;
    }
}
