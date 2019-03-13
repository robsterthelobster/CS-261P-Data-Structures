package binary_trees;

import binary_trees.nodes.TreeNode;

public class BinarySearchTree extends AbstractBinaryTree {

    @Override
    public void insert(double key) {
        if(root == null){
            root = new TreeNode(key);
            return;
        }

        if(search(key) != null) return;

        TreeNode parent = parents.get(parents.size() - 1);
        if(key < parent.key){
            parent.left = new TreeNode(key);
        }else{
            parent.right = new TreeNode(key);
        }
    }
}
