package com.yaoli.ajob;

import java.util.*;

/**
 * Created by will on 2017/3/5.
 */
public class _515_FindLargestValueinEachTreeRow {
    public static void main(String[] args) {
        TreeNode  a = new TreeNode(1);
        TreeNode  b = new TreeNode(3);
        TreeNode  c = new TreeNode(2);

        TreeNode  d = new TreeNode(5);
        TreeNode  e = new TreeNode(3);
        TreeNode  f = new TreeNode(9);

        a.setLeft(b);
        a.setRight(c);

        b.setLeft(d);
        b.setRight(e);

        c.setRight(f);

        _515_FindLargestValueinEachTreeRow ff = new _515_FindLargestValueinEachTreeRow();

        ff.largestValues(a);


    }

    /**
     * 思想：使用Arraylist保存每一个结点
     *
     * 只要 list 不为空，就获得该list的长度，此时长度为 该层的长度。
     *
     * 从list获取第一个结点 并且 添加该结点左右结点
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {

        List<Integer> list = new ArrayList<Integer>();

        if(root == null){
            return list;
        }


        List<TreeNode> treeNodes = new ArrayList<TreeNode>();

        treeNodes.add(root);

        while(treeNodes.size()!= 0){

            int length = treeNodes.size();

            int max = treeNodes.get(0).val;

            while(length != 0 ){
                TreeNode node = treeNodes.get(0);

                if(node.val > max){
                    max = node.val;
                }

                if(node.left != null){
                    treeNodes.add(node.left);
                }

                if(node.right != null){
                    treeNodes.add(node.right);
                }

                treeNodes.remove(0);
                length --;
            }

            list.add(max);

        }

        return list;
    }
}
