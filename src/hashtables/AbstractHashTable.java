package hashtables;

import java.util.NoSuchElementException;

abstract class AbstractHashTable implements HashTable{

    int size = 0;

    final int INITIAL_CAPACITY;

    final int A = 519;
    final int B = 336699;
    final int P = 1993;

    NoSuchElementException noSuchElementException
            = new NoSuchElementException("No such element in hash table");

    public AbstractHashTable(int size){
        INITIAL_CAPACITY = size;
        clear();
    }

    public AbstractHashTable(){
        INITIAL_CAPACITY = 1000;
        clear();
    }

    /*
        Universal Hashing Function
        From Wikipedia -- https://en.wikipedia.org/wiki/Universal_hashing
        h(x) = ((ax + b) mod p) mod m
        P is a prime > m picked from https://en.wikipedia.org/wiki/List_of_prime_numbers
     */
    int hash(int key){
        int index = ((A * key + B) % P ) % INITIAL_CAPACITY;
        return (index < 0) ? index * -1 : index;
    }

    @Override
    public int getSize(){
        return size;
    }
}