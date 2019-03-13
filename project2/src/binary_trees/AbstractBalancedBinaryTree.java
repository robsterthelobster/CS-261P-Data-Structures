package binary_trees;

import binary_trees.nodes.RankNode;

/*
    Same as Binary tree, but self balances
    Contains the rotation methods
    Contains TreeNode with rank --> for AVL --> height
 */
public abstract class AbstractBalancedBinaryTree extends AbstractBinaryTree{

      public void rotateRight(double key){
        RankNode node = (RankNode) search(key);
        setNode((RankNode) node.left, node);
        RankNode temp = (RankNode) node.left.right;
        node.left.right = node;
        node.left = temp;
    }

    public void rotateLeft(double key){
        RankNode node = (RankNode) search(key);
        setNode((RankNode) node.right, node);
        RankNode temp = (RankNode) node.right.left;
        node.right.left = node;
        node.right = temp;
    }

    private void setNode(RankNode nodeToPivot, RankNode node){
        if(node.equals(root)){
            root = nodeToPivot;
        } else if(isRight(node)){
            parent.right = nodeToPivot;
        }else{
            parent.left = nodeToPivot;
        }
    }
}
