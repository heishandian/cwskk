package com.yaoli.ajob;

import java.util.Stack;

/**
 * Created by will on 2016/11/22.
 */
public class TestTree {
    /**
     * http://blog.csdn.net/fansongy/article/details/6798278/
     */
    public static void main(String args[]){
        TreeNode a = new TreeNode("a");
        TreeNode b = new TreeNode("b");
        TreeNode c = new TreeNode("c");
        TreeNode d = new TreeNode("d");
        TreeNode e = new TreeNode("e");
        TreeNode f = new TreeNode("f");
        TreeNode g = new TreeNode("g");


//        TreeNode h = new TreeNode("h");
//        TreeNode i = new TreeNode("i");
//        TreeNode j = new TreeNode("j");
//        TreeNode k = new TreeNode("k");
//        TreeNode m = new TreeNode("m");

        a.setLeft(b);
        a.setRight(c);

        b.setLeft(d);
        b.setRight(f);

        d.setRight(e);
        f.setLeft(g);

//        c.setLeft(f);
//        c.setRight(g);

//        ZhongOrder(a);
//        System.out.println();
//        ZhongOrders(a);

        PreOrder(a);
        System.out.println();
        PreOrders(a);

        System.out.println();

        ZhongOrder(a);
        System.out.println();
        ZhongOrders(a);



    }

    public static void PreOrder(TreeNode root){
        if(root != null){
            System.out.print(root.getValue());
            PreOrder(root.getLeft());
            PreOrder(root.getRight());
        }
    }
    public static void ZhongOrder(TreeNode root){
        if(root != null){
            ZhongOrder(root.getLeft());
            System.out.print(root.getValue());
            ZhongOrder(root.getRight());
        }
    }
    public static void PostOrder(TreeNode root){
        if(root != null){
            PostOrder(root.getLeft());
            PostOrder(root.getRight());
            System.out.print(root.getValue());
        }
    }

    public static void ZhongOrders(TreeNode node){
        Stack stack = new Stack();
        while(node != null || !stack.empty()){
            if(node != null){
                stack.push(node);
                node = node.getLeft();
            }else{
                node = (TreeNode) stack.pop();
                System.out.print(node.getValue());
                node = node.getRight();
            }
        }
    }

    /**
     * 非递归
     */
    public static void PreOrders(TreeNode node){
        Stack stack = new Stack();
        while(node != null || !stack.empty()){
            while(node != null){
                System.out.print(node.getValue());
                stack.push(node);
                node = node.getLeft();
            }
            if(!stack.empty()){
                node = (TreeNode) stack.pop();
                node = node.getRight();
            }
        }
    }
}
