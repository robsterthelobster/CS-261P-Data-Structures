package binary_trees;

/*
    Same as Binary tree, but self balances
    Contains the rotation methods
    Contains TreeNode with rank --> for AVL --> height
 */
public abstract class AbstractBalancedBinaryTree extends AbstractBinaryTree{

    class AVLNode extends TreeNode{
        int rank;

        AVLNode(double key) {
            super(key);
            rank = 0;
        }
    }

    public void rotateRight(double key){
        AVLNode node = (AVLNode) search(key);
        setNode((AVLNode) node.left, node);
        AVLNode temp = (AVLNode) node.left.right;
        node.left.right = node;
        node.left = temp;
    }

    public void rotateLeft(double key){
        AVLNode node = (AVLNode) search(key);
        setNode((AVLNode) node.right, node);
        AVLNode temp = (AVLNode) node.right.left;
        node.right.left = node;
        node.right = temp;
    }

    private void setNode(AVLNode nodeToPivot, AVLNode node){
        if(node.equals(root)){
            root = nodeToPivot;
        } else if(isRight(node)){
            parent.right = nodeToPivot;
        }else{
            parent.left = nodeToPivot;
        }
    }
}
