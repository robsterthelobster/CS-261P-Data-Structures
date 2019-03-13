package binary_trees.nodes;

public class Node {
    public double key;

    Node(double key){
        this.key = key;
    }

    @Override
    public boolean equals(Object o){
        return o instanceof TreeNode &&
                ((TreeNode) o).key == this.key;
    }

    public void copyNode(TreeNode node){
        this.key = node.key;
    }
}
