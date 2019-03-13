package binary_trees.nodes;

import java.util.ArrayList;

public class SkipNode extends Node {
    public ArrayList<SkipNode> next;

    public SkipNode(double key) {
        super(key);
        next = new ArrayList<>();
    }
}