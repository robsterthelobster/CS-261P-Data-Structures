package hashtables;

import java.util.NoSuchElementException;
import java.util.Random;

public class CuckooHashTable extends AbstractArrayHashTable{

    private Random cuckooRandom;
    private double[] CUCKOO_CONSTANTS;
    private final int CUCKOO_SEED = 333;

    public CuckooHashTable(){
        super();
        cuckooRandom = new Random(CUCKOO_SEED);
        CUCKOO_CONSTANTS = getConstants(cuckooRandom);
    }

    public CuckooHashTable(int initialCapacity){
        super(initialCapacity);
        cuckooRandom = new Random(CUCKOO_SEED);
        CUCKOO_CONSTANTS = getConstants(cuckooRandom);
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
    public void set(int key, int value) {
        super.set(key, value);

        boolean isFirst = true;
        double threshold = 3 * Math.log((double) capacity ) / Math.log(2);
        HashNode node = new HashNode(key, value);

        int index = hash(node.key, true);
        if(foundKey(node.key, index)){
            hashTable[index].value = node.value;
        }else{
            index = hash(node.key, false);
            if(foundKey(node.key, index)){
                hashTable[index].value = node.value;
            }
        }

        for(int count = 0; node != null && count < threshold; ++count){
            index = hash(node.key, isFirst);
            HashNode temp = hashTable[index];
            hashTable[index] = node;
            node = temp;
            isFirst = !isFirst;
        }

        if(node != null){
            //System.out.println("thres: " + threshold);
            //resize(size);
            rehash();
            set(node.key, node.value);
        }
        ++size;
    }

    private void rehash(){
        CONSTANTS = getConstants(random);
        CUCKOO_CONSTANTS = getConstants(cuckooRandom);
        resize(size);
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
            --size;
            return true;
        }
        return false;
    }

    @Override
    public void printHashTable() {
        printTable(true);
        printTable(false);
    }

    private void printTable(boolean isFirst){
        System.out.println(isFirst ? "Table 1" : "Table 2");

        int i = isFirst ? 0 : capacity/2;
        int size = isFirst ? capacity/2 : capacity;
        for(; i < size; ++i){
            System.out.print((isFirst ? i : i - capacity/2) + ": ");
            if(hashTable[i] != null){
                System.out.println(hashTable[i].key + ":" + hash(hashTable[i].key, isFirst));
            }else{
                System.out.println("No element");
            }
        }
    }

//    private int hash(int key, boolean isFirst){
//        int _capacity = capacity / 2;
//        return isFirst ?
//                key % _capacity :
//                key / _capacity % _capacity + _capacity;
//    }

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
