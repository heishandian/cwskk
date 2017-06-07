package com.yaoli.ajob;

/**
 * Created by will on 2017/3/2.
 */
public class _530_MinimumAbsoluteDifferenceinBST {
    public static void main(String[] args) {
        _530_MinimumAbsoluteDifferenceinBST _ = new _530_MinimumAbsoluteDifferenceinBST();


        TreeNode  a = new TreeNode(1);
        TreeNode  b = new TreeNode(2);
        TreeNode  c = new TreeNode(3);

//        TreeNode  d = new TreeNode(4);
//        TreeNode  e = new TreeNode(5);
//        TreeNode  f = new TreeNode(6);
//        TreeNode  g = new TreeNode(7);
//
//        TreeNode  h = new TreeNode(8);
//        TreeNode  i = new TreeNode(9);

        a.setLeft(b);
        a.setRight(c);

//        b.setLeft(d);
//        b.setRight(e);
//
//        c.setLeft(f);
//        c.setRight(g);

//        d.setLeft(h);
//        d.setRight(i);


        _.getMinimumDifference(a);
    }

    int min = Integer.MAX_VALUE;

    int pre = 0 ;

    public int getMinimumDifference(TreeNode root) {
        calc(root);
        return min ;
    }

    public void calc(TreeNode node){
        if(node != null){
            if(Math.abs(node.val - pre) < min){
                min = Math.abs(node.val - pre);
            }
            pre = node.val;
            calc(node.left);
            calc(node.right);
        }
    }

}
