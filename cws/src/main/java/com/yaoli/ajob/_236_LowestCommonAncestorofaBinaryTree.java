package com.yaoli.ajob;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by will on 2017/3/1.
 */
public class _236_LowestCommonAncestorofaBinaryTree {
    public static void main(String[] args) {
        _236_LowestCommonAncestorofaBinaryTree tree = new _236_LowestCommonAncestorofaBinaryTree();


        TreeNode  a = new TreeNode(1);
        TreeNode  b = new TreeNode(2);
        TreeNode  c = new TreeNode(3);

        TreeNode  d = new TreeNode(4);
        TreeNode  e = new TreeNode(5);
        TreeNode  f = new TreeNode(6);
        TreeNode  g = new TreeNode(7);

        TreeNode  h = new TreeNode(8);
        TreeNode  i = new TreeNode(9);

        a.setLeft(b);
        a.setRight(c);

        b.setLeft(d);
        b.setRight(e);

        c.setLeft(f);
        c.setRight(g);

        d.setLeft(h);
        d.setRight(i);

        tree.lowestCommonAncestor(a,h,e);


    }

    List<TreeNode> listp = new ArrayList<TreeNode>();
    List<TreeNode> listq = new ArrayList<TreeNode>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        getpath(root,p,listp);
        getpath(root,q,listq);

        int length = listp.size() > listq.size() ? listq.size() : listp.size();

        TreeNode result = null;
        for(int i = 0 ; i < length; i++){
            if(listq.get(i).val == listp.get(i).val){
                result = listq.get(i);
            }else{
                break;
            }
        }
        return result;
    }

    public void getpath(TreeNode root,TreeNode node,List<TreeNode> list){
        if(root != null){
            list.add(root);

            getpath(root.left , node ,list);

            getpath(root.right , node ,list);
            if(list.get(list.size() - 1) != node){ //这里是删除 最后一个节点，而不是删除root
                list.remove(list.size() -  1);
            }else{
                return;
            }
        }
    }
}
