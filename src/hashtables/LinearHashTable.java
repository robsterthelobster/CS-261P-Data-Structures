package hashtables;

import java.util.NoSuchElementException;

public class LinearHashTable extends AbstractHashTable{

    private HashNode[] hashTable;

    @Override
    int hash(int key) {
        return (key < 0) ? (key * -1) % 13 : key % 13;
    }

    public LinearHashTable(int size){
        super(size);
    }

    @Override
    public int search(int key) throws NoSuchElementException {
        int index = getIndexOfKey(key);
        if(hashTable[index] != null){
            return hashTable[index].value;
        }
        throw noSuchElementException;
    }

    @Override
    public void set(int key, int value) {

        int index = getIndexOfKey(key);
        if(hashTable[index] == null){
            ++size;
            hashTable[index] = new HashNode(key, value);
        }else{
            hashTable[index].value = value;
        }
    }

    @Override
    public void delete(int key) {
        int index = getIndexOfKey(key);
        if(hashTable[index] == null) return;

        int _index = (index + 1) % INITIAL_CAPACITY;
        while(hashTable[_index] != null){
            int hash = hash(hashTable[_index].key);
            if(hash < index || hash > _index){
                hashTable[index] = hashTable[_index];
                index = _index;
            }
            _index = (_index + 1) % INITIAL_CAPACITY;
        }
        hashTable[index] = null;
        --size;
    }

    @Override
    public void clear() {
        hashTable = new HashNode[INITIAL_CAPACITY];
        size = 0;
    }

    private int getIndexOfKey(int key){
        int index = hash(key);
        while(hashTable[index] != null){
            if(hashTable[index].key == key){
                return index;
            }
            index = (index + 1) % INITIAL_CAPACITY;
        }
        return index;
    }

    public void printArray(){
        for(int i = 0; i < INITIAL_CAPACITY; ++i){
            System.out.print(i + ": ");
            if(hashTable[i] == null){
                System.out.println("no element");
            }else{
                System.out.println(hashTable[i].key + ":" + hash(hashTable[i].key));
            }
        }
    }
}
