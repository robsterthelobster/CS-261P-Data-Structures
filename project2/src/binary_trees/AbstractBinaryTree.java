package binary_trees;

abstract class AbstractBinaryTree implements Tree{
    TreeNode root;
    int count;

    @Override
    public void create(){
        root = null;
        count = 0;
    }

    @Override
    public void create(int key){
        root = new TreeNode(key);
        count = 1;
    }

    public int getCount(){
        return count;
    }
}
