package hashtables;

public abstract class AbstractArrayHashTable extends AbstractHashTable{

    HashNode[] hashTable;

    AbstractArrayHashTable(){
        super();
    }

    AbstractArrayHashTable(int capacity){
        super(capacity);
    }

    @Override
    public void clear(){
        super.clear();
        hashTable = new HashNode[capacity];
    }

    @Override
    protected void resize(int newSize) {
        capacity = (int) (newSize / LOWER_THRESHOLD);
        HashNode[] newTable = hashTable;
        clear();

        for(HashNode node : newTable){
            if(node != null)
                set(node.key, node.value);
        }
    }
}
