package hashtables;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ChainedHashTable extends AbstractHashTable{

    private ArrayList<ArrayList<HashNode>> hashTable;

    public ChainedHashTable(){
        super();
    }

    @Override
    public void printHashTable() {

    }

    public ChainedHashTable(int size){
        super(size);
    }

    @Override
    public int search(int key) throws NoSuchElementException{
        int index = hash(key);

        for (HashNode node : hashTable.get(index)) {
            if (node.key == key) {
                return node.value;
            }
        }
        throw noSuchElementException;
    }

    @Override
    public void set(int key, int value) {
        HashNode node = new HashNode(key, value);
        int index = hash(key);
        int nodeIndex = hashTable.get(index).indexOf(node);

        if(nodeIndex == -1){
            hashTable.get(index).add(node);
            ++size;
        }else{
            hashTable.get(index).get(nodeIndex).value = value;
        }
    }

    @Override
    public void delete(int key) {
        int index = hash(key);
        HashNode node = new HashNode(key, 0);
        if(hashTable.get(index).indexOf(node) != -1){
            hashTable.get(index).remove(node);
            --size;
        }
    }

    @Override
    public void clear() {

        hashTable = new ArrayList<>();
        for(int i = 0; i < INITIAL_CAPACITY; ++i){
            hashTable.add(new ArrayList<>());
        }
    }

    public int getCollisionCount(){
        int count = 0;
        for(ArrayList<HashNode> arrayList : hashTable){
            if(arrayList.size() > 1)
                count += arrayList.size() - 1;
        }
        return count;
    }
}
