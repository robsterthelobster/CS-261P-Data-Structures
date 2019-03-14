package tests;

import binary_trees.TreeType;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class TestCase4 extends TestCase{

    private int[] insertions = {
            2000,
            4000,
            6000,
            8000,
            10000
    };
    private int deleteOp = 0;
    private int setOp = 0;
    private int searchOp = 0;

    private Random setRandom;
    private Random deleteRandom;
    private Random searchRandom;
    private Random opRandom;

    private int deleteCount = 0;
    private int insertCount = 0;
    private int searchCount = 0;

    public TestCase4() {
        super(4);
    }

    public void bulkTests() {
        for (TreeType type : TreeType.values()) {
            for(int insertion : insertions){
                int count = 0;
                double duration = 0;
                for (long seed : SEEDS) {
                    opRandom = new Random(seed);
                    setRandom = new Random(seed);
                    deleteRandom = new Random(seed);
                    searchRandom = new Random(seed);
                    deleteCount = 0;
                    insertCount = 0;
                    searchCount = 0;
                    deleteOp = 0;
                    setOp = 0;
                    searchOp = 0;
                    duration += runTest(type, seed, insertion);
                    count++;
                }
                writeToFile(duration / count, type, insertion);
            }
        }
    }

    @Override
    double runTest(TreeType type, long seed, int size) {
        createTree(type);
        insertData(size);
        startTest();

        for(int i = 0; i < size; ++i){
            randomOp(getOp());
        }

        return finishTest();
    }

    @Override
    void writeToFile(double duration, TreeType type, int size) {
        try {
            PrintWriter printWriter = new PrintWriter(
                    new FileWriter(filename, true));
            printWriter.printf("%s, %d, %d, %d, %d, %f\n",
                    type.name(), size, setOp, searchOp, deleteOp, duration);
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void writeFirstLine(String filename) {
        try {
            PrintWriter printWriter = new PrintWriter(
                    new FileWriter(filename, true));
            printWriter.printf("%s, %s, %s, %s, %s, %s\n",
                    "Type", "Operations", "Inserts", "Searches", "Deletes", "Time");
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 0 is set
    // 1 is search
    // 2 is delete
    private int getOp(){
        return opRandom.nextInt(3);
    }

    private void insertData(int size){
        for(int i = 0; i < size/2; ++i){
            int key = setRandom.nextInt();
            tree.insert(key);
            ++insertCount;
            ++setOp;
        }
    }

    private void randomOp(int op){
        int key;
        switch(op){
            case 0:
                key = setRandom.nextInt();
                ++insertCount;
                tree.insert(key);
                ++setOp;
                break;
            case 1:
                if(searchCount < insertCount && searchCount > deleteCount){
                    key = searchRandom.nextInt();
                    search(key);
                }else if(insertCount < searchCount){
                    while(insertCount < searchCount){
                        int newKey = setRandom.nextInt();
                        tree.insert(newKey);
                        ++setOp;
                        ++insertCount;
                    }
                }else if(deleteCount > searchCount){
                    while(deleteCount > searchCount){
                        searchRandom.nextInt();
                        ++searchCount;
                    }
                    key = searchRandom.nextInt();
                    search(key);
                }
                break;
            case 2:
                if(deleteCount < insertCount){
                    key = deleteRandom.nextInt();
                    tree.delete(key);
                    ++deleteCount;
                    ++deleteOp;
                }
                break;
            default:
                //just don't do anything
                System.out.println("default");
        }
    }

    private void search(int key){
        tree.search(key);
        ++searchCount;
        ++searchOp;
    }
}
