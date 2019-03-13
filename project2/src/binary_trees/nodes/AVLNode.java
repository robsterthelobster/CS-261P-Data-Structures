package binary_trees.nodes;

public class AVLNode extends RankNode{

    public AVLNode(double key) {
        super(key);
    }

    public void updateRank(){
        if(left == null && right == null) rank = 0;
        else if(left == null) rank = ((RankNode)right).rank + 1;
        else if(right == null) rank = ((RankNode)left).rank + 1;
        else rank = Math.max(((RankNode)right).rank, ((RankNode)left).rank) + 1;
    }

    public double needRotation(){
        if(left == null && right == null) return 0;
        if(left == null) return ((RankNode)right).rank + 1;
        else if(right == null) return -1 - ((RankNode)left).rank;
        else{
            return ((RankNode)right).rank - ((RankNode)left).rank;
        }
    }
}
