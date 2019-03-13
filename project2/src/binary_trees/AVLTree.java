package binary_trees;

import binary_trees.nodes.AVLNode;
import binary_trees.nodes.RankNode;
import binary_trees.nodes.TreeNode;

import java.util.ArrayList;

public class AVLTree extends AbstractBalancedBinaryTree{

    private ArrayList<AVLNode> parents;

    @Override
    public TreeNode search(double key) {
        TreeNode node = root;
        parents = new ArrayList<>();
        while(node != null){
            if(key == node.key){
                return node;
            }

            parents.add((AVLNode)node);
            if(key < node.key){
                node = node.left;
            }else{
                node = node.right;
            }
        }
        return null;
    }

    @Override
    public void insert(double key) {
        if(root == null){
            root = new AVLNode(key);
            return;
        }

        if(search(key) == null){
            AVLNode parent = parents.get(parents.size() - 1);
            if(key < parent.key){
                parent.left = new AVLNode(key);
            }else{
                parent.right = new AVLNode(key);
            }

            parent.updateRank();
            for(int i = parents.size() - 1; i >= 0; --i){
                AVLNode node = parents.get(i);
                node.updateRank();
                double needRotation = node.needRotation();

                if(needRotation > 1){
                    if(i == 0) rotate(node, (RankNode)root, false);
                    else rotate(node, parents.get(i - 1), false);
                }
                else if(needRotation < -1){
                    if(i == 0) rotate(node, (RankNode)root, true);
                    else rotate(node, parents.get(i - 1), true);
                }
            }

        }
    }

    @Override
    public void delete(double key) {

    }

    void rotate(RankNode node, RankNode nodeParent, boolean isRight){
        if(isRight) super.rotateRight(node, nodeParent);
        else super.rotateLeft(node, nodeParent);
        updateNodes((AVLNode)node, (AVLNode)nodeParent);
    }

    private void updateNodes(AVLNode node, AVLNode nodeParent){
        nodeParent.updateRank();
        node.updateRank();
    }

}
