package binary_trees;

public class AVLTree extends AbstractBalancedBinaryTree{

    @Override
    public void insert(int key) {
        if(root == null){
            root = new AVLNode(key);
            return;
        }

        if(search(key) == null){
            if(key < parent.key){
                parent.left = new AVLNode(key);
            }else{
                parent.right = new AVLNode(key);
            }
        }
    }

    @Override
    public void delete(int key) {

    }
}
