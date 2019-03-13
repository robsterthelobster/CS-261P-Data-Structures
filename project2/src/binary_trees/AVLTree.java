package binary_trees;

import binary_trees.nodes.AVLNode;
import binary_trees.nodes.RankNode;

public class AVLTree extends AbstractBalancedBinaryTree{

    @Override
    public void insert(double key) {
        if(root == null){
            root = new AVLNode(key);
            return;
        }

        if(search(key) != null) return;

        AVLNode parent = (AVLNode) parents.get(parents.size() - 1);
        if(key < parent.key){
            parent.left = new AVLNode(key);
        }else{
            parent.right = new AVLNode(key);
        }
        updateHeights();
    }

    @Override
    public void delete(double key) {
        super.delete(key);
        updateHeights();
    }

    private void updateHeights(){
        for(int i = parents.size() - 1; i >= 0; --i){
            AVLNode node = (AVLNode) parents.get(i);
            node.updateRank();
            double needRotation = node.needRotation();

            if(needRotation > 1){
                rotate(node, (RankNode) parents.get(i - 1), false);
            }
            else if(needRotation < -1){
                rotate(node, (RankNode) parents.get(i - 1), true);
            }
        }
    }

    private void rotate(RankNode node, RankNode nodeParent, boolean isRight){
        if(isRight) super.rotateRight(node, nodeParent);
        else super.rotateLeft(node, nodeParent);
        updateNodes((AVLNode)node, (AVLNode)nodeParent);
    }

    private void updateNodes(AVLNode node, AVLNode nodeParent){
        nodeParent.updateRank();
        node.updateRank();
    }

}
