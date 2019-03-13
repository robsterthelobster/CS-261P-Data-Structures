import binary_trees.*;
import binary_trees.nodes.Node;

import java.util.Random;

public class Main {

    /*
        1. Standard BST -- Tree
        2. AVL tree     -- Tree
        3. Treap        -- Tree
        4. Skip-list    -- "Tree"
     */
    public static void main(String[] args) {
//        testBST();
//        testBSTDelete();
//        testAVLTreeRotations();
//        testAVLExample();
//        testAVLTree();
//        testSkipList();
//        testTreap();

    }

    static void testTreap(){
        Treap treap = new Treap();
        for(int i = 100; i > 90; --i){
            treap.insert(i);
        }
        debugTree(treap);

        treap = new Treap();
        for(int i = 0; i < 10; ++i){
            treap.insert(i);
        }
        debugTree(treap);

        treap = new Treap();
        Random random = new Random(555);
        for(int i = 0; i < 10; ++i){
            int key = random.nextInt(100);
            System.out.print(key + " ");
            treap.insert(key);
        }
        System.out.println();
        debugTree(treap);

        delete(treap, 73);
        delete(treap, 38);
    }

    static void testSkipList(){
        SkipList skipList = new SkipList();
        Random random = new Random(333);
        for(int i = 0; i < 15; ++i){
            int key = random.nextInt(100);
            System.out.print(key + " ");
            skipList.insert(key);
        }
        System.out.println();
        System.out.println();

        skipList.printTree();

        skipList.delete(2);
        System.out.println("Delete 2");
        skipList.printTree();

        skipList.delete(3);
        System.out.println("Delete 3");
        skipList.printTree();

        skipList.delete(85);
        System.out.println("Delete 85");
        skipList.printTree();

//        random = new Random(555);
//        for(int i = 0; i < 15; ++i){
//            search(skipList, random.nextInt(100));
//        }
//        for(int i = 0; i < 5; ++i){
//            search(skipList, i);
//        }

    }

    static void search(Tree tree, int key){
        Node node = tree.search(key);
        if(node == null) System.out.println(key + ": Key is null");
        else System.out.println(key + ": " + node.key);
    }

    static void testAVLExample(){
        AVLTree tree = new AVLTree();
        tree.insert(5);
        tree.insert(2);
        tree.insert(3);
        tree.insert(23);
        tree.insert(17);
        tree.insert(29);
        tree.insert(11);
        tree.insert(19);
        debugTree(tree);
        tree.insert(13);
        debugTree(tree);
    }

    static void testAVLTree(){
        AVLTree tree = new AVLTree();
        tree.insert(1);
        tree.insert(0);
        tree.insert(2);
        tree.printTree();
    }

    static void testAVLTreeRotations(){
        AVLTree tree = new AVLTree();
        for(int i = 100; i > 90; --i){
            tree.insert(i);
        }
        debugTree(tree);

        tree = new AVLTree();
        for(int i = 0; i < 10; ++i){
            tree.insert(i);
        }
        debugTree(tree);

        delete(tree, 7);
        delete(tree, 9);
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
        Random random = new Random();
        for(int i = 0; i < 1000; ++i){
            tree.insert(random.nextInt());
        }
        debugTree(tree);
    }

    static void delete(AbstractBinaryTree tree, int key){
        System.out.println("Deleting key: " + key);
        System.out.println("IsBST before delete: " + tree.isBST());
        tree.delete(key);
        System.out.println("IsBST after delete: " + tree.isBST());
        tree.printTree();
    }

    static void debugTree(AbstractBinaryTree tree){
        tree.printTree();
        System.out.println("isBst: " + tree.isBST());
        System.out.println();
    }
}
