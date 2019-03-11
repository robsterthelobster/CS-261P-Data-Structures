package binary_trees;

import java.util.ArrayList;
import java.util.Random;

public class SkipList implements Tree{

    class SkipNode{
        double key; // has to be double to use INFINITY

        SkipNode next;
        SkipNode down;

        SkipNode(double key){
            this.key = key;
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
        random = new Random();
        heads = new ArrayList<>();
        head = new SkipNode(Double.POSITIVE_INFINITY);
        tail = new SkipNode(Double.POSITIVE_INFINITY);
        head.next = tail;
        heads.add(head);
        numOfLevels = 1;
        count = 0;
    }

    @Override
    public TreeNode search(int key) {
        return null;
    }

    @Override
    public void insert(int key) {
        if(count == 0){
            SkipNode node = new SkipNode(key);
            node.next = head.next;
            head.next = node;
            ++count;
            return;
        }

        //int level = selectRandomLevel();
        int level = 2;
        numOfLevels = Math.max(numOfLevels, level);
        while(heads.size() < numOfLevels){
            SkipNode temp = new SkipNode(Double.NEGATIVE_INFINITY);
            temp.next = tail;
            heads.add(temp);

        }
        SkipNode[] prev = new SkipNode[numOfLevels];

        for(int i = numOfLevels - 1; i >= 0; --i){
            SkipNode curr = heads.get(i);
            while(curr != tail && key > curr.next.key){
                curr = curr.next;
            }
            prev[i] = curr;
            SkipNode node = new SkipNode(key);
            node.next = curr.next;
            curr.next = node;
        }
        ++count;
    }

    @Override
    public void delete(int key) {

    }

    private int selectRandomLevel(){
        int randLevel = 0;
        while(random.nextBoolean()) ++randLevel;
        return randLevel;
    }

    public void printTree(){
        for(int i = heads.size() - 1; i >= 0; --i){
            printLevel(heads.get(i));
        }
    }

    private void printLevel(SkipNode node){
        SkipNode temp = node.next;
        while(temp != tail){
            System.out.print((int)temp.key + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
