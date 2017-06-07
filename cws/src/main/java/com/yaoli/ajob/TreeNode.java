package com.yaoli.ajob;

/**
 * Created by will on 2016/11/22 this is use to test
 */
public class TreeNode {

    public static void main(String[] args) {
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

    }
    public int val;

    public String value;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(String value){
        this.value = value;
    }

    public TreeNode(int val){
        this.val = val;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
