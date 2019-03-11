import binary_trees.AVLTree;
import binary_trees.AbstractBinaryTree;
import binary_trees.BinarySearchTree;
import binary_trees.SkipList;

import java.util.Random;

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
        //estBSTDelete();
        //testAVLTreeRotations();
        //testAVLTree();
        testSkipList();
    }

    static void testSkipList(){
        SkipList skipList = new SkipList();
        Random random = new Random(333);
        for(int i = 0; i < 10; ++i){
            skipList.insert(random.nextInt(100));
            skipList.printTree();
        }
    }

    static void testAVLTree(){
        AVLTree tree = new AVLTree();
        tree.insert(1);
        tree.insert(0);
        tree.insert(2);
        tree.printTree();
        tree.rotateRight(1);
        tree.printTree();
        tree = new AVLTree();
        tree.insert(1);
        tree.insert(0);
        tree.insert(2);
        tree.printTree();
        tree.rotateLeft(1);
        tree.printTree();
    }
    static void testAVLTreeRotations(){
        AVLTree tree = new AVLTree();
        for(int i = 0; i < 2; ++i){
            tree.insert(i*3);
            tree.insert(-i*3);
        }
        for(int i = 0; i < 2; ++i){
            tree.insert(i*3 + 1);
            tree.insert(-i*3 - 1);
        }
        tree.printTree();
        tree.rotateRight(0);
        tree.printTree();
        tree.rotateLeft(0);
        tree.printTree();
    }

    static void testBST(){
        BinarySearchTree tree = new BinarySearchTree();
        insert(tree);
//        delete(tree, 0);
//        delete(tree, 0);
    }

    static void testBSTDelete(){
        testBSTDeleteInsert(0, true); // test root
        testBSTDeleteInsert(6, false); // test right child with children
        testBSTDeleteInsert(3, false); // test left child with children
        testBSTDeleteInsert(-3, false);// test left side
        testBSTDeleteInsert(-7, false);// test leaf
        testBSTDeleteInsert(5, false); // test left child without children
    }

    static void testBSTDeleteInsert(int key, boolean print){
        BinarySearchTree tree = new BinarySearchTree();
        for(int i = 0; i < 3; ++i){
            tree.insert(i*3);
            tree.insert(-i*3);
        }
        tree.insert(5);
        for(int i = 0; i < 3; ++i){
            tree.insert(i*3 + 1);
            tree.insert(-i*3 - 1);
        }
        tree.insert(8);
        tree.insert(2);
        if(print) tree.printTree();
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
        System.out.println("IsBST before delete: " + tree.isBST());
        tree.delete(key);
        System.out.println("IsBST after delete: " + tree.isBST());
        tree.printTree();

    }
}
