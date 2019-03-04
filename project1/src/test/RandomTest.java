package test;

import hashtables.HashNode;

import javax.print.attribute.HashAttributeSet;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomTest extends HashTableTest{

    String filename;
    final int NUM_OPS = 1000;
    long random_seed = 1234;

    Random setRandom;
    Random deleteRandom;
    Random searchRandom;
    Random opRandom;

    int deleteCount = 0, setCount = 0, searchCount = 0;

    int deleteOp = 0;
    int setOp = 0;
    int searchOp = 0;

    public RandomTest(int size, long seed, String filename) {
        super(size, seed);
        this.filename = filename;

        setRandom = new Random(random_seed);
        deleteRandom = new Random(random_seed);
        searchRandom = new Random(random_seed);

        opRandom = new Random(seed);
    }

    @Override
    public double runTest(HashType type) {
        createHashTable(type);
        insertData();
        startTest();

        for(int i = 0; i < SIZE; ++i){
            randomOp(getOp());
        }

        return finishTest(type);
    }

    @Override
    void writeToFile(double duration, HashType type) {
        try {
            PrintWriter printWriter = new PrintWriter(
                    new FileWriter(filename, true));
            printWriter.printf("%s, %d, %d, %d, %d, %d, %f\n",
                    type.name(), SIZE, SEED, setOp, searchOp, deleteOp, duration);
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void insertData(){
        for(int i = 0; i < SIZE/2; ++i){
            int key = setRandom.nextInt();
            hashTable.set(key, key);
            ++setCount;
            ++setOp;
        }
    }

    // 0 is set
    // 1 is search
    // 2 is delete
    int getOp(){
        return opRandom.nextInt(3);
    }

    void randomOp(int op){
        int key = 0;
        switch(op){
            case 0:
                key = setRandom.nextInt();
                ++setCount;
                hashTable.set(key, key);
                ++setOp;
                break;
            case 1:
                if(searchCount < setCount && searchCount > deleteCount){
                    key = searchRandom.nextInt();
                    hashSearch(key);
                }else if(setCount < searchCount){
                    while(setCount < searchCount){
                        int newKey = setRandom.nextInt();
                        hashTable.set(newKey, newKey);
                        ++setOp;
                        ++setCount;
                    }
                }else if(deleteCount > searchCount){
                    while(deleteCount > searchCount){
                        searchRandom.nextInt();
                        ++searchCount;
                    }
                    key = searchRandom.nextInt();
                    hashSearch(key);
                }
                break;
            case 2:
                if(deleteCount < setCount){
                    key = deleteRandom.nextInt();
                    hashTable.delete(key);
                    ++deleteCount;
                    ++deleteOp;
                }
                break;
            default:
                //just don't do anything
                System.out.println("default");
        }
    }

    void hashSearch(int key){
        try{
            hashTable.search(key);
            ++searchCount;
            ++searchOp;
        }catch (NoSuchElementException e){

        }
    }
}
