package hashtables;

public class QuadraticHashTable extends LinearHashTable{

    public QuadraticHashTable(int size) {
        super(size);
    }

    @Override
    protected int nextIndex(int index, int count){
        return (index + count * count) % INITIAL_CAPACITY;
    }
}
