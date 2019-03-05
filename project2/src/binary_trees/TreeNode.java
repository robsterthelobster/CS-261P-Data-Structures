package binary_trees;

// assuming key == value ALWAYS
public class TreeNode {
    int key;
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int key){
        this.key = key;
        value = key;
    }

    @Override
    public boolean equals(Object o){
        return o instanceof TreeNode &&
                ((TreeNode) o).key == this.key &&
                ((TreeNode) o).value == this.value;
    }

    void copyNode(TreeNode node){
        this.key = node.key;
        this.value = node.value;
    }
}
