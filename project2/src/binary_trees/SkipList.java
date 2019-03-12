package binary_trees;

import java.util.ArrayList;
import java.util.Random;

public class SkipList implements Tree{

    class SkipNode{
        double key; // has to be double to use INFINITY

        ArrayList<SkipNode> next;

        SkipNode(double key){
            this.key = key;
            next = new ArrayList<>();
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof SkipNode && ((SkipNode) obj).key == this.key;
        }
    }

    private SkipNode head;
    private SkipNode tail;
    private ArrayList<SkipNode> heads;
    private int numOfLevels;
    private Random random;
    private int count;

    public SkipList(){
        this.create();
    }

    @Override
    public void create() {
        random = new Random(333);
        heads = new ArrayList<>();
        head = new SkipNode(Double.NEGATIVE_INFINITY);
        tail = new SkipNode(Double.POSITIVE_INFINITY);
        head.next.add(tail);
        heads.add(head);
        numOfLevels = 1;
        count = 0;
    }

    @Override
    public TreeNode search(double key) {
        return null;
    }

    @Override
    public void insert(double key) {
        if(count == 0){
            SkipNode node = new SkipNode(key);
            node.next.add(head.next.get(0));
            head.next.set(0, node);
            ++count;
            return;
        }

        int level = selectRandomLevel();
        numOfLevels = Math.max(numOfLevels, level);
        while(heads.size() < numOfLevels){
            SkipNode temp = new SkipNode(Double.NEGATIVE_INFINITY);
            temp.next = head.next;
            temp.next.add(tail);
            heads.add(temp);

        }
        SkipNode[] curr = new SkipNode[numOfLevels];
        heads.toArray(curr);

        SkipNode node = new SkipNode(key);
        for(int i = 0; i < numOfLevels; ++i){
            node.next.add(tail);
        }
        for(int i = numOfLevels - 1; i >= 0; --i){
            SkipNode temp = curr[i];
            while(temp != tail && temp.next.size() > i && key > temp.next.get(i).key){
                temp = temp.next.get(i);
            }

            if(i < level){
                node.next.set(i, temp.next.get(i));
                temp.next.set(i, node);
            }
        }
        ++count;
    }

    @Override
    public void delete(double key) {

    }

    private int selectRandomLevel(){
        int randLevel = 0;
        while(random.nextBoolean()) ++randLevel;
        return randLevel;
    }

    public void printTree(){
        for(int i = numOfLevels - 1; i >= 0; --i){
            printLevel(i);
        }
    }

    private void printLevel(int i){
        SkipNode temp = heads.get(i).next.get(i);
        System.out.print("Level " + i + ": ");
        while(temp != tail){
            System.out.print((int)temp.key + " ");
            temp = temp.next.get(i);
        }
        System.out.println();
    }
}
