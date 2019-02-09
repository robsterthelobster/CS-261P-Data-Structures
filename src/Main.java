import hashtables.ChainedHashTable;
import hashtables.HashTable;
import hashtables.LinearHashTable;
import hashtables.QuadraticHashTable;
import test.testcase.TestCase1;

import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        //TestCase1 testCase1 = new TestCase1();
        testHashLinear();
        //System.out.println();
        //testQuadratic();
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

    static void testQuadratic(){
        QuadraticHashTable quadraticHashTable = new QuadraticHashTable(13);
        quadraticHashTable.set(41, 41);
        quadraticHashTable.set(18, 18);
        quadraticHashTable.set(44, 44);
        //quadraticHashTable.set(59, 59);
        //quadraticHashTable.set(32, 32);
        //quadraticHashTable.set(22, 22);
        quadraticHashTable.set(31, 31);
        quadraticHashTable.set(73, 73);
        quadraticHashTable.printHashTable();
        quadraticHashTable.delete(18);
        quadraticHashTable.printHashTable();
        hashSearch(quadraticHashTable, 31);
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

    static void hashSearch(HashTable hashTable, int key){
        try{
            int value = hashTable.search(key);
            System.out.println("Hashsearch(" + key + "): " + value);
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }
}
