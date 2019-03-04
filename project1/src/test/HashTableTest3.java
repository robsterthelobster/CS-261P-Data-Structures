package test;

import hashtables.HashNode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HashTableTest3 extends HashTableTest{

    String filename;

    public HashTableTest3(int size, long seed, String filename) {
        super(size, seed);

        this.filename = filename;
    }

    @Override
    public double runTest(HashType type) {
        this.createHashTable(type);
        startTest();
        insertData();
        searchData();
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

    void searchData(){
        for(HashNode node : data){
            hashTable.search(node.key);
        }
    }

    void insertData(){
        for(HashNode node : data){
            hashTable.set(node.key, node.value);
        }
    }
}
