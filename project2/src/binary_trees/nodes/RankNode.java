package binary_trees.nodes;

public class RankNode extends TreeNode{
    public int rank;

    public RankNode(double key){
        super(key);
        this.rank = 0;
    }

    public RankNode(double key, int rank) {
        super(key);
        this.rank = rank;
    }
}
