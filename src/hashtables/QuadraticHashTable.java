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
        index = (index + count * count) % capacity;
        return (index < 0) ? index * -1 : index;
    }
}
