package com.yaoli.ajob;

/**
 * Created by siemens on 3/20/2017.
 */
public class _538_ConvertBSTtoGreaterTree {
    public static void main(String[] args) {

    }

    public TreeNode convertBST(TreeNode root) {
        calc(root);

        return root;
    }

    int sum = 0;

    public void calc(TreeNode node){
        if(node != null){
            calc(node.right);
            int temp = node.val + sum;
            node.val = temp;
            sum = temp;
            calc(node.left);
        }
    }
}
