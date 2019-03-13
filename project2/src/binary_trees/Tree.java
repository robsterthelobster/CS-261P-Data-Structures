package binary_trees;

import binary_trees.nodes.Node;

public interface Tree {
    void create();
    Node search(double key);
    void insert(double key);
    void delete(double key);
}
