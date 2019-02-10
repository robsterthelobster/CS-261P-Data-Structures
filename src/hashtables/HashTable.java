package hashtables;

import java.util.NoSuchElementException;

public interface HashTable {

    // Search(key): Return associated value. Throw exception if no associated value.
    int search(int key) throws NoSuchElementException;

    // Set(key,value): Add new pair to data. If key already has an associated value, replace it with the new value.
    void set(int key, int value);

    // Delete(key): Remove the pair associated with the given key. If no such pair, throw an exception.
    void delete(int key);

}
