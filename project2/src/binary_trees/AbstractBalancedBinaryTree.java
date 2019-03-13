package binary_trees;

import binary_trees.nodes.AVLNode;
import binary_trees.nodes.RankNode;

/*
    Same as Binary tree, but self balances
    Contains the rotation methods
    Contains TreeNode with rank --> for AVL --> height
 */
abstract class AbstractBalancedBinaryTree extends AbstractBinaryTree{

    void rotateRight(RankNode node, RankNode nodeParent){
        setNode((RankNode) node.left, node, nodeParent);
        RankNode temp = (RankNode) node.left.right;
        node.left.right = node;
        node.left = temp;
    }

    public void rotateLeft(RankNode node, RankNode nodeParent){
        setNode((RankNode) node.right, node, nodeParent);
        RankNode temp = (RankNode) node.right.left;
        node.right.left = node;
        node.right = temp;

    }

    private void setNode(RankNode nodeToPivot, RankNode node, RankNode nodeParent){
        if(node.equals(root)){
            root = nodeToPivot;
        } else if(isRight(node, nodeParent)){
            nodeParent.right = nodeToPivot;
        }else{
            nodeParent.left = nodeToPivot;
        }
    }
}
