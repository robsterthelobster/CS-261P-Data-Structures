package tests;

import binary_trees.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

abstract class TestCase {

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

    void writeToFile(double duration, TreeType type, int size) {
        writeToFile(duration, type, size, filename);
    }

    void writeFirstLine(String name){
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(name, true));
            printWriter.printf("%s, %s, %s\n", "Type", "Insertions", "Time");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    void createTree(TreeType type){
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

    void writeToFile(double duration, TreeType type, int size, String name) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(name, true));
            printWriter.printf("%s, %d, %f\n", type.name(), size, duration);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
