package com.yaoli.ajob;

/**
 * Created by will on 2017/3/5.
 */
public class _098_ValidateBinarySearchTree {

    public static void main(String[] args) {
        _098_ValidateBinarySearchTree cc = new _098_ValidateBinarySearchTree();



        TreeNode  a = new TreeNode(Integer.MIN_VALUE);
        TreeNode  b = new TreeNode(Integer.MIN_VALUE);
        a.setLeft(b);
        System.out.println(cc.isValidBST(a));

    }

    boolean flag = true;

    boolean first = true;

    Integer pre = Integer.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        calc(root);

        return flag;
    }

    /**
     * 最要注意的是 两个节点一样 ，和第一个结点是 整数的最小值
     * @param node
     */
    public void calc(TreeNode node){
        if(node != null){
            calc(node.left);

            if(first == false){
                if(pre >= node.val){
                    flag = false;
                    return ;
                }else{
                    pre = node.val;
                }
            }else{
                first = false;
                pre = node.val;
            }

            calc(node.right);
        }
    }
}
