package binary_trees;

import binary_trees.nodes.RankNode;
import binary_trees.nodes.TreeNode;

import java.util.ArrayList;

/*
    Pretty much the master class for this project.

    Tons of reusable code in this abstract class
    Some trees could be slimmed down a little, but a lot have shared logic
    so this class contains that logic.

    Notable functions: create, search, delete
    Insert cannot be shared since I use different variations of Nodes for each
    data structure (e.g. Binary Tree Node vs AVL Node)

    Debug functions: isBst, printTree
    Super helpful to see if my functions are working.

 */
public abstract class AbstractBinaryTree implements Tree {

    /*  so I can reuse search code
        have to call search before using parent
    */
    TreeNode root;
    ArrayList<TreeNode> parents;

    AbstractBinaryTree() {
        this.create();
    }

    @Override
    public void create() {
        root = null;
    }

    @Override
    public TreeNode search(double key) {
        TreeNode node = root;
        parents = new ArrayList<>();
        parents.add(root);
        while (node != null) {
            if (key == node.key) {
                return node;
            }

            parents.add(node);
            if (key < node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    @Override
    public void delete(double key) {
        if (root == null) {
            return;
        }
        TreeNode node = search(key);
        if (node == null) return;

        TreeNode parent = parents.get(parents.size() - 1);
        if (node.left == null || node.right == null) {
            TreeNode temp;
            if (node.left == null) {
                temp = node.right;
            } else {
                temp = node.left;
            }

            if (node.equals(root)) {
                root = temp;
            } else if (isRight(node, parent)) {
                parent.right = temp;
            } else {
                parent.left = temp;
            }
        } else {
            TreeNode successor = node.right;

            parent = node;
            while (successor.left != null) {
                parent = successor;
                successor = successor.left;
            }
            node.copyNode(successor);
            if (parent.equals(node)) {
                parent.right = parent.right.right;
            } else {
                parent.left = parent.left.right;
            }
        }
    }

    boolean isRight(TreeNode node, TreeNode nodeParent) {
        return nodeParent.key < node.key;
    }

    /*
        DEBUG helper method
        JSON format plug in: http://ysangkok.github.io/js-clrs-btree/btree.html
     */
    public void printTree() {
        System.out.println(printTree(root));
        System.out.println();
    }

    private String printTree(TreeNode node) {
        StringBuilder sb = new StringBuilder("{\"keys\": [");
        sb.append("\"").append((int) node.key);
        if (node instanceof RankNode) sb.append(", ").append(((RankNode) node).rank);
        sb.append("\"]");
        if (node.left != null || node.right != null) {
            sb.append(", \"children\": [");
            if (node.left != null) {
                sb.append(printTree(node.left));
            } else {
                sb.append("{\"keys\": [null]}");
            }
            if (node.right != null) {
                sb.append(", ");
                sb.append(printTree(node.right));
            }
            sb.append("]");
        }
        sb.append("}");
        return sb.toString();
    }

    // talked about in discussion
    // taken from https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
    public boolean isBST() {
        return isBSTUtil(root, Integer.MIN_VALUE,
                Integer.MAX_VALUE);
    }

    private boolean isBSTUtil(TreeNode node, double min, double max) {
        if (node == null)
            return true;

        if (node.key < min || node.key > max)
            return false;

        return (isBSTUtil(node.left, min, node.key - 1) &&
                isBSTUtil(node.right, node.key + 1, max));
    }
}
