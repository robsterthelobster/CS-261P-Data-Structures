package binary_trees;

// assuming key == value ALWAYS
public class TreeNode {
    int key; // value = key
    TreeNode left;
    TreeNode right;

    TreeNode(int key){
        this.key = key;
    }

    @Override
    public boolean equals(Object o){
        return o instanceof TreeNode &&
                ((TreeNode) o).key == this.key;
    }

    void copyNode(TreeNode node){
        this.key = node.key;
    }
}
