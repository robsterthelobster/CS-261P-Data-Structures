package binary_trees;

public class BinarySearchTree extends AbstractBinaryTree {

    /*  so I can reuse search code
        have to call search before using parent
     */
    private TreeNode parent;

    @Override
    public TreeNode search(int key) {
        TreeNode node = root;
        parent = root;
        while(node != null){
            if(key < node.value){
                parent = node;
                node = node.left;
            }else if(key >  node.value){
                parent = node;
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

        if (node != null) {
            boolean isRight = parent.value < node.value;
            if (node.left == null) {
                if (node.equals(root)) {
                    root = node.right;
                } else if (isRight) {
                    parent.right = node.right;
                } else {
                    parent.left = node.right;
                }
            } else if (node.right == null) {
                if (node.equals(root)) {
                    root = node.left;
                } else if (isRight) {
                    parent.right = node.left;
                } else {
                    parent.left = node.left;
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
}
