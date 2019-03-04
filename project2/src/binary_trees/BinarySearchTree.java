package binary_trees;

public class BinarySearchTree extends AbstractBinaryTree {

    @Override
    public TreeNode search(int key) {
        TreeNode node = root;
        while(node != null){
            if(key < node.value){
                node = node.left;
            }else if(key >  node.value){
                node = node.right;
            }else{
                return node;
            }
        }
        return null;
    }

    @Override
    public void insert(int key) {
        if(root == null){
            root = new TreeNode(key);
            return;
        }
        TreeNode node = root;
        while(true){
            if(key < node.value){
                if(node.left == null){
                    node.left = new TreeNode(key);
                    return;
                }
                node = node.left;
            }else if(key >  node.value){
                if(node.right == null){
                    node.right = new TreeNode(key);
                    return;
                }
                node = node.right;
            }else{
                return;
            }
        }
    }

    @Override
    public void delete(int key) {

    }
}
