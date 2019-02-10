import hashtables.*;
import test.testcase.*;

import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        TestCase001 testCase1 = new TestCase001();
        //testHashLinear();
        //System.out.println();
        //testQuadratic();
        //testCuckoo();
        //testHashChained();
    }

    static void testCuckoo(){
        CuckooHashTable hashTable = new CuckooHashTable(22);

//        hashTable.set(12, 12);
//        hashTable.set(26, 26);
//        hashTable.set(92, 92);
//        hashTable.set(23, 23);
//        hashTable.set(28, 28);
//        hashTable.set(94, 94);
//        hashTable.set(15, 15);
        bulkInsert(hashTable, 1000);
        System.out.println(hashTable.getLoadFactor());

    }

    static void testHashChained(){

        System.out.println("------Chained Hash Table-----");

        ChainedHashTable chainedHashTable = new ChainedHashTable();
        System.out.println(chainedHashTable.getSize());
        for(int i = 0; i < 11; ++i){
            chainedHashTable.set(i, i);
        }
        System.out.println("size: " + chainedHashTable.getSize());
        chainedHashTable.printHashTable();
        chainedHashTable.set(12,12);
        System.out.println("size: " + chainedHashTable.getSize());
        chainedHashTable.printHashTable();


        System.out.println("collisions: " + chainedHashTable.getCollisionCount());
    }

    static void testQuadratic(){
        QuadraticHashTable hashTable = new QuadraticHashTable();
        bulkInsert(hashTable, 1000);
    }

    static void testHashLinear(){
        LinearHashTable linearHashTable = new LinearHashTable(13);
        linearHashTable.set(41, 41);
        linearHashTable.set(18, 18);
        linearHashTable.set(44, 44);
        linearHashTable.set(59, 59);
        linearHashTable.set(32, 32);
        linearHashTable.set(22, 22);
        linearHashTable.set(31, 31);
        linearHashTable.set(73, 73);
        linearHashTable.printHashTable();
        linearHashTable.delete(18);
        linearHashTable.printHashTable();
    }

    static void bulkInsert(AbstractHashTable hashTable, int numOfInserts){
        for(int i = 0; i < numOfInserts; ++i){
            hashTable.set(i, i);
        }

        hashTable.printHashTable();
        for(int i = 0; i < numOfInserts; ++i){
            hashSearch(hashTable, i);
        }
    }

    static void hashSearch(HashTable hashTable, int key){
        try{
            int value = hashTable.search(key);
            System.out.println("Hashsearch(" + key + "): " + value);
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }
}
