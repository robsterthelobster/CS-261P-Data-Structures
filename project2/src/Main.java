import binary_trees.AbstractBinaryTree;
import binary_trees.BinarySearchTree;
import binary_trees.TreeNode;

public class Main {

    /*
        1. Standard BST -- Tree
        2. AVL tree     -- Tree
        3. Treap        -- Tree
        4. Skip-list    -- Not a tree
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("TREEEEEEEEES");
        //testBST();
        testBSTDelete();
    }

    static void testBST(){
        BinarySearchTree tree = new BinarySearchTree();
        insert(tree);
//        delete(tree, 0);
//        delete(tree, 0);
    }

    static void testBSTDelete(){
        testBSTDeleteInsert(0); // test root
        testBSTDeleteInsert(6); // test right child with children
        testBSTDeleteInsert(3); // test left child with children
        testBSTDeleteInsert(-3);// test left side
        testBSTDeleteInsert(-7);// test leaf
    }

    static void testBSTDeleteInsert(int key){
        BinarySearchTree tree = new BinarySearchTree();
        for(int i = 0; i < 3; ++i){
            tree.insert(i*3);
            tree.insert(-i*3);
        }
        for(int i = 0; i < 3; ++i){
            tree.insert(i*3 + 1);
            tree.insert(-i*3 - 1);
        }
        tree.insert(8);
        tree.insert(5);
        tree.insert(2);
        //tree.printTree();
        delete(tree, key);

    }

    static void insert(AbstractBinaryTree tree){
        //        tree.insert(0);
        tree.insert(2);
        tree.insert(4);
        tree.insert(3);

        tree.insert(7);
        tree.insert(-2);
        tree.insert(1);
        tree.insert(-6);
        tree.insert(-1);
        tree.printTree();
    }

    static void delete(AbstractBinaryTree tree, int key){
        System.out.println("Deleting key: " + key);
        tree.delete(key);
        tree.printTree();
    }
}
