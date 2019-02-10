package hashtables;

import java.util.NoSuchElementException;

public class LinearHashTable extends AbstractArrayHashTable{

    public LinearHashTable(){
        super();
    }

    public LinearHashTable(int initialCapacity){
        super(initialCapacity);
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
        super.set(key, value);

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
        boolean[] alreadyVisited = new boolean[capacity];
        alreadyVisited[index] = true;

        if(hashTable[index] == null) return;

        int _index = nextIndex(index, 1);
        for(int i = 2; hashTable[_index] != null && !alreadyVisited[_index]; ++i){
            alreadyVisited[_index] = true;
            int hash = hash(hashTable[_index].key);
            if(hash <= index || hash > _index){
                hashTable[index] = hashTable[_index];
                index = _index;
            }
            _index = nextIndex(_index, i);
        }
        hashTable[index] = null;
        --size;
    }

    private int getIndexOfKey(int key){
        int index = hash(key);
        for(int i = 0; hashTable[index] != null; ++i){

            if(hashTable[index].key == key){
                return index;
            }
            index = nextIndex(index, i);
        }
        return index;
    }

    // debugging purposes
    public void printHashTable(){
        for(int i = 0; i < capacity; ++i){
            System.out.print(i + ": ");
            if(hashTable[i] == null){
                System.out.println("no element");
            }else{
                System.out.println(hashTable[i].key + ":" + hash(hashTable[i].key));
            }
        }
    }

    protected int nextIndex(int index, int count){
        return (index + 1) % capacity;
    }
}
