package binary_trees;

public class BinarySearchTree extends AbstractBinaryTree {

    @Override
    public void insert(int key) {
        if(root == null){
            root = new TreeNode(key);
            return;
        }

        if(search(key) == null){
            if(key < parent.key){
                parent.left = new TreeNode(key);
            }else{
                parent.right = new TreeNode(key);
            }
        }
    }

    @Override
    public void delete(int key) {
        if(root == null){
            return;
        }
        TreeNode node = search(key);
        if(node == null) return;

        boolean isRight = parent.value < node.value;
        if(node.left == null || node.right == null){
            TreeNode temp;
            if(node.left == null){
                temp = node.right;
            }else{
                temp = node.left;
            }

            if (node.equals(root)) {
                root = temp;
            } else if (isRight) {
                parent.right = temp;
            } else {
                parent.left = temp;
            }
        } else {
            TreeNode successor = node.right;

            parent = node;
            while (successor.left != null) {
                parent = successor;
                successor = successor.left;
            }
            node.copyNode(successor);
            if (parent.equals(node)) {
                parent.right = parent.right.right;
            } else {
                parent.left = parent.left.right;
            }
        }
    }
}
