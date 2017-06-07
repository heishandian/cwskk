package com.yaoli.ajob;


/**
 * Created by will on 2017/3/1.
 */
public class _235_LowestCommonAncestorofaBinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val > q.val){
            TreeNode temp = q;
            q = p;
            p = temp;
        }
        return calc(root,p,q);
    }

    public TreeNode calc(TreeNode root,TreeNode p,TreeNode q){
        if(root.val > p.val && root.val < q.val){
            return root;
        }else if(root.val == p.val || root.val == q.val){
            return root;
        }else if(root.val < p.val && root.val < q.val){
            if(root != null){
                return calc(root.right,p,q);
            }else{
                return null; //判断为空
            }
        }else if(root.val > p.val && root.val > q.val){
            if(root != null){
                return calc(root.left,p,q);
            }else{
                return null; //判断为空
            }
        }else{
            return null; //判断为空
        }
    }
}
