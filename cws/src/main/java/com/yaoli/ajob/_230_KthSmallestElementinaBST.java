package com.yaoli.ajob;

/**
 * Created by will on 2017/3/6.
 */
public class _230_KthSmallestElementinaBST {

    public static void main(String[] args) {
        _230_KthSmallestElementinaBST kk = new _230_KthSmallestElementinaBST();

        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(1);

        a.setLeft(b);
        kk.kthSmallest(a,1);
    }

    int count = 0;

    int res = 0;

    public int kthSmallest(TreeNode root, int k) {
        calc(root,k);

        return res;
    }

    public void calc(TreeNode node ,int k){


        if(node.left != null){
            calc(node.left,k);
        }

        if(node != null){
            count ++;
            if(k == count){
                res = node.val;
                return;
            }
        }

        if(node.right != null){
            calc(node.right ,k);
        }

    }
}
