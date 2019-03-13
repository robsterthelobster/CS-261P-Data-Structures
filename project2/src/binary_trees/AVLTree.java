package binary_trees;

import binary_trees.nodes.RankNode;

public class AVLTree extends AbstractBalancedBinaryTree{

    @Override
    public void insert(double key) {
        if(root == null){
            root = new RankNode(key);
            return;
        }

        if(search(key) == null){
            if(key < parent.key){
                parent.left = new RankNode(key);
            }else{
                parent.right = new RankNode(key);
            }
        }
    }

    @Override
    public void delete(double key) {

    }
}
