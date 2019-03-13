package binary_trees;

import binary_trees.nodes.RankNode;

import java.util.Random;

public class Treap extends AbstractBalancedBinaryTree{

    private Random random;

    @Override
    public void create(){
        super.create();
        random = new Random(333);
    }

    @Override
    public void insert(double key) {
        if(root == null){
            root = new RankNode(key, random.nextDouble());
        }
    }
}
