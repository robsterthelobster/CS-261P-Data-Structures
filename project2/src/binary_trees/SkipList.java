package binary_trees;

import java.util.ArrayList;
import java.util.Random;

public class SkipList implements Tree{

    class SkipNode{
        double key; // has to be double to use INFINITY

        ArrayList<SkipNode> next;
        SkipNode down;

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
    public Node search(double key) {
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
//            SkipNode temp = new SkipNode(Double.NEGATIVE_INFINITY);
//            temp.next = head.next;
//
//            temp.next.add(tail);
            head.next.add(tail);

            heads.add(head);

        }
        SkipNode[] prev = new SkipNode[numOfLevels];
        SkipNode[] curr = new SkipNode[numOfLevels];
        heads.toArray(curr);

        for(int i = numOfLevels - 1; i >= 0; --i){
            SkipNode temp = curr[i];
            while(temp != tail && temp.next.size() > i && key > temp.next.get(i).key){
                temp = temp.next.get(i);
            }
            prev[i] = temp;

            if(i == 0){
                // identical key --> do nothing
                // in a real tree, replace value
                if(temp.next.get(0).key == key) return;

                SkipNode node = new SkipNode(key);
                node.next.add(temp.next.get(i));
                temp.next.set(i, node);
                ++count;

                for(int j = 1; j < level; ++j){
                    node.next.add(prev[j].next.get(j));
                    prev[j].next.set(j, node);
                }
            }
        }
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