package binary_trees;

import binary_trees.nodes.RankNode;
import binary_trees.nodes.TreeNode;

import java.util.ArrayList;

public class AVLTree extends AbstractBalancedBinaryTree{

    ArrayList<RankNode> parents;

    @Override
    public TreeNode search(double key) {
        TreeNode node = root;
        parent = root;
        while(node != null){
            if(key < node.key){
                parent = node;
                node = node.left;
                parents.add((RankNode)parent);
            }else if(key >  node.key){
                parent = node;
                node = node.right;
                parents.add((RankNode)parent);
            }else{
                return node;
            }
        }
        return null;
    }

    @Override
    public void create(){
        super.create();
        parents = new ArrayList<>();
    }

    @Override
    public void insert(double key) {
        if(root == null){
            root = new RankNode(key);
            return;
        }
        if(search(key) == null){
            int rank = 0;
            if(key < parent.key){
                parent.left = new RankNode(key);
                if(parent.right != null) rank = ((RankNode)parent.right).rank;
            }else{
                parent.right = new RankNode(key);
                if(parent.left != null) rank = ((RankNode)parent.left).rank;
            }
            ((RankNode)parent).rank = rank + 1;
            for(RankNode node : parents){
                node.rank = getRank(node) + 1;
            }
        }
    }

    @Override
    public void delete(double key) {

    }

    private int getRank(RankNode node){
        if(node.left == null && node.right == null) return 0;
        if(node.left == null) return ((RankNode)node.right).rank;
        if(node.right == null) return ((RankNode)node.left).rank;
        return Math.max(((RankNode)node.right).rank, ((RankNode)node.left).rank);
    }
}
