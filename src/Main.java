import hashtables.ChainedHashTable;
import hashtables.HashTable;
import hashtables.LinearHashTable;
import test.testcase.TestCase1;

import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        //TestCase1 testCase1 = new TestCase1();
        testHashLinear();
    }

    static void testHashChained(){

        System.out.println("------Chained Hash Table-----");

        ChainedHashTable chainedHashTable = new ChainedHashTable();
        System.out.println(chainedHashTable.getSize());
        for(int i = 0; i < 2000; ++i){
            chainedHashTable.set(i, i);
        }

        System.out.println(chainedHashTable.search(1));
        chainedHashTable.set(1, 444);
        System.out.println(chainedHashTable.search(1));

        System.out.println(chainedHashTable.getSize());

        System.out.println("collisions: " + chainedHashTable.getCollisionCount());
    }

    static void testHashLinear(){
        LinearHashTable linearHashTable = new LinearHashTable(13);
        linearHashTable.set(41, 41);
        linearHashTable.printArray();
    }

    static void hashSearch(HashTable hashTable, int key){
        try{
            int value = hashTable.search(key);
            System.out.println(value);
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }
}
