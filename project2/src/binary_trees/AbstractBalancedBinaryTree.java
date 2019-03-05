package binary_trees;

/*
    Same as Binary tree, but self balances
    Contains the rotation methods
    Contains TreeNode with rank --> for AVL --> height
 */
public abstract class AbstractBalancedBinaryTree extends AbstractBinaryTree{

    class AVLNode extends TreeNode{
        int rank;

        AVLNode(int key) {
            super(key);
        }
    }
}
