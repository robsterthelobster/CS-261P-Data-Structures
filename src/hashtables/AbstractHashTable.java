package hashtables;

import java.util.NoSuchElementException;
import java.util.Random;

public abstract class AbstractHashTable implements HashTable{

    Random random;
    double[] CONSTANTS;
    private final int CONSTANTS_SIZE = 50;
    private final int SEED = 999;

    private int prime;
    private final int K = 5;
    private final float UPPER_THRESHOLD = 0.75f;
    final float LOWER_THRESHOLD = 0.375f;

    int size = 0;
    int capacity;

    NoSuchElementException noSuchElementException
            = new NoSuchElementException("No such element in hash table");

    AbstractHashTable(int initialCapacity){
        capacity = initialCapacity;
        random = new Random(SEED);
        CONSTANTS = getConstants(random);
        clear();
    }

    AbstractHashTable(){
        capacity = 16;
        random = new Random(SEED);
        CONSTANTS = getConstants(random);
        clear();
    }

    double[] getConstants(Random random){

        double[] arr = new double[CONSTANTS_SIZE];

        for(int i = 0; i < CONSTANTS_SIZE; ++i){
            arr[i] = random.nextDouble();
        }
        return arr;
    }

    @Override
    public void set(int key, int value){
        if(isNotInRange(size + 1)){
            resize(capacity);
        }
    }

    protected abstract void resize(int newSize);

    private boolean isNotInRange(int newSize){
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
        prime = getPrime(capacity);
    }

    public int getSize(){
        return size;
    }

    /*
        debugging purposes
     */
    public abstract void printHashTable();

    public float getLoadFactor(){
        return getLoadFactor(size);
    }

    private float getLoadFactor(int newSize){
        return newSize * 1.0f / capacity;
    }
}