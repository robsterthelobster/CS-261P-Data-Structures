package hashtables;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ChainedHashTable extends AbstractHashTable{

    private ArrayList<ArrayList<HashNode>> hashTable;

    public ChainedHashTable(){
        super();
    }

    public ChainedHashTable(int initialCapacity){
        super(initialCapacity);
    }

    @Override
    protected void resize() {
        capacity = capacity * 2;
        ArrayList<ArrayList<HashNode>> newTable = hashTable;
        clear();

        for(ArrayList<HashNode> list : newTable){
            if(!list.isEmpty()){
                for(HashNode node : list){
                    set(node.key, node.value);
                }
            }
        }
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
        if(isNotInRange(size + 1)){
            resize();
        }

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
        super.clear();
        hashTable = new ArrayList<>();
        for(int i = 0; i < capacity; ++i){
            hashTable.add(new ArrayList<>());
        }
    }

    @Override
    public void printHashTable() {
        for(int i = 0; i < capacity; ++i){
            System.out.print("index " + i + ": ");
            ArrayList<HashNode> list = hashTable.get(i);
            if(list.isEmpty()){
                System.out.println("no elements");
            }else{
                for(HashNode node : list){
                    System.out.print(node.key + " ");
                }
                System.out.println();
            }
        }
    }

    // debugging
    public int getCollisionCount(){
        int count = 0;
        for(ArrayList<HashNode> arrayList : hashTable){
            if(arrayList.size() > 1)
                count += arrayList.size() - 1;
        }
        return count;
    }
}
