package binary_trees;

public interface Tree {
    void create();
    void create(int key); // initiate tree with a root
    int search(int key);
    void insert(int key);
    void delete(int key);
}
