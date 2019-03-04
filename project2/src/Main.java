import binary_trees.BinarySearchTree;

public class Main {

    /*
        1. Standard BST -- Tree
        2. AVL tree     -- Tree
        3. Treap        -- Tree
        4. Skip-list    -- Not a tree
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        testBST();
    }

    static void testBST(){
        BinarySearchTree tree = new BinarySearchTree();
//        tree.insert(0);
//        tree.insert(2);
//        tree.insert(3);
//        tree.insert(4);
//        tree.insert(7);
//        tree.insert(-1);
//        tree.insert(1);
//        tree.insert(-6);
        for(int i = 0; i < 100; ++i){
            tree.insert(i);
        }
        tree.printTree();
    }
}
