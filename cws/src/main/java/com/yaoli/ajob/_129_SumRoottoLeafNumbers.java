package com.yaoli.ajob;

/**
 * Created by will on 2017/3/9.
 */
public class _129_SumRoottoLeafNumbers {
    int sum = 0;

    public int sumNumbers(TreeNode root) {

        calc(root,0);

        return sum;
    }

    public void calc(TreeNode node,int total){

        if(node != null){

            total = total * 10 + node.val;

            calc(node.left,total);

            //叶子结点
            if(node.left == null && node.right == null){
                sum = total + sum;
            }

            calc(node.right,total);

            total = total / 10;

        }

    }
}
