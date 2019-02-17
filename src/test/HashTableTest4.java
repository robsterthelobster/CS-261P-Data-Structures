package test;

public class HashTableTest4 extends HashTableTest3{
    public HashTableTest4(int size, long seed, String filename) {
        super(size, seed, filename);
    }

    @Override
    public double runTest(HashType type) {
        this.createHashTable(type);
        insertData();
        startTest();
        searchData();
        return finishTest(type);
    }
}
