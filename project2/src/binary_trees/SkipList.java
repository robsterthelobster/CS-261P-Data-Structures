package binary_trees;

import java.util.ArrayList;
import java.util.Random;

public class SkipList implements Tree{

    class SkipNode extends Node{
        ArrayList<SkipNode> next;

        SkipNode(double key) {
            super(key);
            next = new ArrayList<>();
        }
    }

    private SkipNode head;
    private SkipNode tail;
    private ArrayList<SkipNode> heads;
    private int numOfLevels;
    private Random random;
    private int count;
    private SkipNode[] prev;

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
        return search(key, false);
    }

    private Node search(double key, boolean toBottom){
        SkipNode[] curr = new SkipNode[numOfLevels];
        heads.toArray(curr);

        for(int i = numOfLevels - 1; i >= 0; --i){
            SkipNode temp = curr[i];
            while(temp != tail && temp.next.size() > i && key > temp.next.get(i).key){
                temp = temp.next.get(i);
            }
            prev[i] = temp;
            if(temp.next.get(i).key == key && (!toBottom || i == 0)) return temp.next.get(i);
        }
        return null;
    }

    @Override
    public void insert(double key) {
        SkipNode node = new SkipNode(key);
        if(count == 0){
            addNode(node, head);
            return;
        }

        int level = selectRandomLevel();
        numOfLevels = Math.max(numOfLevels, level);
        while(heads.size() < numOfLevels){
            head.next.add(tail);
            heads.add(head);
        }

        prev = new SkipNode[numOfLevels];
        SkipNode temp = (SkipNode) search(key);
        if(temp == null){
            addNode(node, prev[0]);

            for(int i = 1; i < level; ++i){
                node.next.add(prev[i].next.get(i));
                prev[i].next.set(i, node);
            }
        }
    }

    private void addNode(SkipNode add, SkipNode prev){
        add.next.add(prev.next.get(0));
        prev.next.set(0, add);
        ++count;
    }

    @Override
    public void delete(double key) {
        prev = new SkipNode[numOfLevels];
        if(search(key, true) == null) return;

        for(int i = numOfLevels - 1; i >= 0; --i){
            ArrayList<SkipNode> nodes = prev[i].next;
            if(nodes.get(i).key == key){
                nodes.set(i, nodes.get(i).next.get(i));
            }
        }
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