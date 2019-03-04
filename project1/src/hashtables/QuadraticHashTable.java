package hashtables;

public class QuadraticHashTable extends LinearHashTable{

    public QuadraticHashTable(){
        super();
    }

    public QuadraticHashTable(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    protected int nextIndex(int index, int count){
        return (index + count * count) % capacity;
    }
}
