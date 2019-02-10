package hashtables;

import java.util.NoSuchElementException;

public class CuckooHashTable extends AbstractHashTable{

    private double[] CUCKOO_CONSTANTS = {
            118,
            999,
            881,
            999,
            119,
            7253
    };

    private HashNode[] hashTable;

    public CuckooHashTable(int initialCapacity){
        super(initialCapacity);
    }

    @Override
    public int search(int key) throws NoSuchElementException {
        int index = hash(key, true);
        if(foundKey(key, index)){
            return hashTable[index].value;
        }

        index = hash(key, false);
        if(foundKey(key, index)){
            return hashTable[index].value;
        }
        throw noSuchElementException;
    }

    @Override
    protected void resize() {

    }

    @Override
    public void set(int key, int value) {
        boolean isFirst = true;
        double threshold = 2 * Math.log((double) capacity /2) / Math.log(2);
        HashNode node = new HashNode(key, value);

        for(int count = 0; node != null && count < threshold; ++count){
            int index = hash(node.key, isFirst);
            HashNode temp = hashTable[index];
            hashTable[index] = node;
            node = temp;
            isFirst = !isFirst;
        }
        if(node == null){
            // TODO
        }
    }

    @Override
    public void delete(int key) {
        if(delete(key, true)) return;
        delete(key, false);
    }

    private boolean delete(int key, boolean isFirst){
        int index = hash(key, isFirst);
        if(foundKey(key, index)){
            hashTable[index] = null;
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        super.clear();
        hashTable = new HashNode[capacity];
    }

    @Override
    public void printHashTable() {
        printTable(true);
        printTable(false);
    }

    private void printTable(boolean isFirst){
        System.out.println(isFirst ? "Table 1" : "Table 2");

        int i = isFirst ? 0 : capacity /2;
        int size = isFirst ? capacity /2 : capacity;
        for(; i < size; ++i){
            System.out.print(i + ": ");
            if(hashTable[i] != null){
                System.out.println(hashTable[i].key + ":" + hash(hashTable[i].key, isFirst));
            }else{
                System.out.println("No element");
            }
        }
    }

    private int hash(int key, boolean isFirst){
        return isFirst ?
                hash(key, CONSTANTS, capacity /2) :
                secondaryHash(key);
    }

    private int secondaryHash(int key){
        return hash(key, CUCKOO_CONSTANTS, capacity - capacity /2) + capacity /2;
    }

    private boolean foundKey(int key, int index){
        return hashTable[index] != null && hashTable[index].key == key;
    }
}
