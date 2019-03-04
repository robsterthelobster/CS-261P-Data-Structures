package binary_trees;

abstract class AbstractBinaryTree implements Tree{
    TreeNode root;
    int count;

    @Override
    public void create(){
        root = null;
        count = 0;
    }

    public int getCount(){
        return count;
    }

    /*
        DEBUG helper method
        JSON format plug in: http://ysangkok.github.io/js-clrs-btree/btree.html
     */
    public void printTree(){
        System.out.println(printTree(root));
    }

    private String printTree(TreeNode node){
        StringBuilder sb = new StringBuilder("{\"keys\": [");
        sb.append(node.key);
        sb.append("]");
        if(node.left != null || node.right != null){
            sb.append(", \"children\": [");
            if(node.left != null){
                sb.append(printTree(node.left));
            }else{
                sb.append("{\"keys\": [null]}");
            }
            if(node.right != null){
                sb.append(", ");
                sb.append(printTree(node.right));
            }
            sb.append("]");
        }
        sb.append("}");
        return sb.toString();
    }
}
