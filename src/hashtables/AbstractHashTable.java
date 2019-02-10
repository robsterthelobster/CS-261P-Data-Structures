package hashtables;

import java.util.NoSuchElementException;

abstract class AbstractHashTable implements HashTable{

    final int K = 5;

    protected final double[] CONSTANTS = {
            111,
            333,
            666,
            999,
            519
    };
    protected int prime;

    //private final float LOWER_THRESHOLD = 0.25f;
    private final float UPPER_THRESHOLD = 0.75f;

    int size = 0;
    int capacity;

    NoSuchElementException noSuchElementException
            = new NoSuchElementException("No such element in hash table");

    public AbstractHashTable(int initialCapacity){
        capacity = initialCapacity;
        prime = getPrime(capacity);
        clear();
    }

    public AbstractHashTable(){
        capacity = 16;
        prime = getPrime(capacity);
        clear();
    }

    protected abstract void resize();

    protected boolean isNotInRange(int newSize){
        float load_factor = getLoadFactor(newSize);
        //return load_factor > UPPER_THRESHOLD || load_factor < LOWER_THRESHOLD;
        return load_factor >= UPPER_THRESHOLD;
    }

    /*
        https://stackoverflow.com/questions/47407251/optimal-way-to-find-next-prime-number-java
        Find next largest prime factor
        Taken from stack-overflow but modified for efficiency
     */
    private int getPrime(int num){
        num = (num % 2 == 0) ? num + 1 : num + 2;

        for(boolean isPrime = false; !isPrime; num += 2){
            double threshold = Math.sqrt(num);

            isPrime = true;
            for(int i = 3; i <= threshold; i += 2){
                if(num % i == 0){
                    isPrime = false;
                    break;
                }
            }
        }

        return num;
    }

    /*
        Universal Hashing Function
        From Wikipedia -- https://en.wikipedia.org/wiki/Universal_hashing
        h(x) = ((ax + b) mod p) mod m
        P is a prime > m picked from https://en.wikipedia.org/wiki/List_of_prime_numbers
     */
    int hash(int key){
        return hash(key, CONSTANTS, capacity);
    }

    int hash(int key, double[] constants, int size){
        double index = constants[0];
        for(int i = 1; i < K; ++i){
            index += key * Math.pow(constants[i], i);
        }
        index = (index % prime) % size;
        return (index < 0) ? (int)index * -1 : (int)index;
    }

    public void clear(){
        size = 0;
    }

    public int getSize(){
        return size;
    }

    /*
        debugging purposes
     */
    public abstract void printHashTable();

    private float getLoadFactor(){
        return getLoadFactor(size);
    }

    private float getLoadFactor(int newSize){
        return newSize * 1.0f / capacity;
    }
}