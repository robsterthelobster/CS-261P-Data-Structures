package test;

/*
    i create table outside timer
 */
public class InsertionCreateTest extends InsertionTest{
    public InsertionCreateTest(int numOfInsertions, long seed, String filename) {
        super(numOfInsertions, seed, filename);
    }

    @Override
    public double runTest(HashType type) {
        this.createHashTable(type);

        this.startTest();
        insertData();
        return finishTest(type);
    }
}
