package tests;

import binary_trees.*;

import java.io.File;
import java.io.IOException;
import java.util.Random;

abstract class TestCase {

    int[] insertions = {
            5000,
//            10000,
//            15000,
//            20000,
//            25000,
//            30000,
//            35000,
//            40000,
//            45000,
//            50000
    };

    long[] SEEDS;
    private long startTime;
    String filename;
    Tree tree;

    TestCase(int test){
        this.filename = "TestCase" + test + ".csv";
        createNewFile(filename);
        generateSeeds(333);
    }

    abstract double runTest(TreeType type, long seed, int size);

    void startTest(){
        startTime = System.nanoTime();
    }

    double finishTest(){
        return nanoToSeconds(System.nanoTime() - startTime);
    }

    abstract void writeToFile(double duration, TreeType type, int size);
    abstract void writeFirstLine(String filename);

    private double nanoToSeconds(long nanotime){
        return (double) nanotime / 1E9;
    }

    private void generateSeeds(long seed){
        Random random = new Random(seed);
        int SEED_LEN = 5;
        SEEDS = new long[SEED_LEN];
        for(int i = 0; i < SEED_LEN; ++i){
            SEEDS[i] = random.nextLong();
        }
    }

    void createNewFile(String filename){
        File file = new File(filename);
        if(file.exists()){
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeFirstLine(filename);
    }

    void createHashTable(TreeType type){
        switch(type){
            case BST:
                tree = new BinarySearchTree();
                break;
            case AVL:
                tree = new AVLTree();
                break;
            case TREAP:
                tree = new Treap();
                break;
            case SKIP:
                tree = new SkipList();
                break;
            default:
                System.err.println("Not a valid type");
                System.exit(1);
        }
    }

    public void bulkTests(){
        for(TreeType type : TreeType.values()){
            for(int numOfInsertions : insertions){
                int count = 0;
                double duration = 0;
                for(long seed : SEEDS){
                    duration += runTest(type, seed, numOfInsertions);
                    count++;
                }
                writeToFile(duration/count, type, numOfInsertions);
            }
        }
    }
}
