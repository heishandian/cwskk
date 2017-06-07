package com.yaoli.ajob;

/**
 * Created by will on 2017/2/28.
 */
public class _110_BalancedBinaryTree {
    public static void main(String[] args) {

    }

    public boolean isBalanced(TreeNode root) {
        int depth [] = new int[1];
        depth[0] = 0;

        return isBalanced(root,depth);
    }


    /**
     *  利用后续遍历的方式遍历二叉树的每一个结点，在遍历到一个结点之前 我们就已经 遍历了他的 左右子树
     *
     *  只要在遍历每个结点的时候记录他的深度（某一结点的深度等于它到 叶子结点的路径的长度），我们就可以
     *
     *  一边遍历一边 判断每个结点是不是平衡的
     *
     *  这道题目的关键是 如何 保存深度，和返回 true和false的问题
     *
     *  因为Java中没有指针，所以必须采用数组 来进行保存深度
     * @param node
     * @param height
     * @return
     */
    public boolean isBalanced(TreeNode node,int height[]){
        if(node == null){
            height[0] = 0;
            return true;
        }

        int lefth [] = new int[1];
        int righth [] = new int[1];

        if(isBalanced(node.left,lefth) && isBalanced(node.right,righth)){
            int diff = lefth[0] - righth[0];
            if(diff <=1 && diff>= -1){
                height[0] = 1 + (lefth[0] > righth[0] ? lefth[0] : righth[0]);
                return true;
            }
        }

        return false;
    }
}
