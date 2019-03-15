package binary_trees;

import binary_trees.nodes.RankNode;

import java.util.Random;

public class Treap extends AbstractBalancedBinaryTree{

    private Random random;

    @Override
    public void create(){
        super.create();
        random = new Random();
    }

    @Override
    public void insert(double key) {
        if(root == null){
            root = new RankNode(key, random.nextDouble());
            return;
        }

        if(search(key) != null) return;

        int parentIndex = parents.size() - 1;
        RankNode parent = (RankNode) parents.get(parentIndex);
        RankNode node = new RankNode(key, random.nextDouble());
        if(key < parent.key){
            parent.left = node;
        }else{
            parent.right = node;
        }

        while(!node.equals(root) && node.rank > parent.rank && parentIndex > 0){
            RankNode temp = parent;
            parent = (RankNode) parents.get(--parentIndex);
            if(isRight(node, temp)){
                rotateLeft(temp, parent);
            }else{
                rotateRight(temp, parent);
            }
        }
    }

    @Override
    public void delete(double key){
        if(root == null) return;

        RankNode node = (RankNode) search(key);
        if(node == null) return;

        RankNode parent = (RankNode) parents.get(parents.size() - 1);
        while(!isLeaf(node)){
            if(node.left == null){
                parent = pivotNodes(node, parent, true);
            } else if(node.right == null || ((RankNode) node.left).rank > ((RankNode) node.right).rank){
                parent = pivotNodes(node, parent, false);
            } else{
                parent = pivotNodes(node, parent, true);
            }
        }

        if(isRight(node, parent)){
            parent.right = null;
        }else{
            parent.left = null;
        }
    }

    private RankNode pivotNodes(RankNode node, RankNode parent, boolean isRight){
        if(isRight){
            RankNode temp = (RankNode) node.right;
            rotateLeft(node, parent);
            return temp;
        }else{
            RankNode temp = (RankNode) node.left;
            rotateRight(node, parent);
            return temp;
        }
    }

    private boolean isLeaf(RankNode node){
        return node.left == null && node.right == null;
    }
}
