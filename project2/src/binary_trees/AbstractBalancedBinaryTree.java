package binary_trees;

import binary_trees.nodes.RankNode;

/*
    Same as Binary tree, but self balances
    Contains the rotation methods
    Contains TreeNode with rank --> for AVL --> height
 */
abstract class AbstractBalancedBinaryTree extends AbstractBinaryTree{

    void rotateRight(RankNode node, RankNode nodeParent){
        if(node.left == null){
            RankNode temp = new RankNode(nodeParent.key, nodeParent.rank);
            nodeParent.key = node.key;
            nodeParent.rank = node.rank;
            temp.right = nodeParent.right;
            temp.left = node.right;
            nodeParent.right = temp;
            nodeParent.left = null;
        }else{
            setNode((RankNode) node.left, node, nodeParent);
            RankNode temp = (RankNode) node.left.right;
            node.left.right = node;
            node.left = temp;
        }
    }

    void rotateLeft(RankNode node, RankNode nodeParent){
        if(node.right == null){
            RankNode temp = new RankNode(nodeParent.key, nodeParent.rank);
            nodeParent.key = node.key;
            nodeParent.rank = node.rank;
            temp.right = node.left;
            temp.left = nodeParent.left;
            nodeParent.left = temp;
            nodeParent.right = null;
        }else{
            setNode((RankNode) node.right, node, nodeParent);
            RankNode temp = (RankNode) node.right.left;
            node.right.left = node;
            node.right = temp;
        }
    }

    private void setNode(RankNode nodeToPivot, RankNode node, RankNode nodeParent){
//        System.out.println(isRight(node, nodeParent));
//        System.out.println(nodeParent.key);
//        System.out.println(node.key);
//        if(nodeToPivot != null) System.out.println(nodeToPivot.key);
        if(node.equals(root)){
            root = nodeToPivot;
        } else if(isRight(node, nodeParent)){
            nodeParent.right = nodeToPivot;
        }else{
            nodeParent.left = nodeToPivot;
        }
    }
}
