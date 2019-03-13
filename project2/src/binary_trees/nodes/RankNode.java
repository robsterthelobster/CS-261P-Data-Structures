package binary_trees.nodes;

public class RankNode extends TreeNode{
    public double rank;

    public RankNode(double key){
        super(key);
        this.rank = 0;
    }

    public RankNode(double key, double rank){
        super(key);
        this.rank = rank;
    }
}
