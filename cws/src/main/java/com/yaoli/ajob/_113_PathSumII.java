package com.yaoli.ajob;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by will on 2017/3/9.
 */
public class _113_PathSumII {
    public static void main(String[] args) {
        _113_PathSumII aa = new _113_PathSumII();

        TreeNode  a = new TreeNode(5);
        TreeNode  b = new TreeNode(4);
        TreeNode  c = new TreeNode(8);

        TreeNode  d = new TreeNode(11);
        TreeNode  e = new TreeNode(13);
        TreeNode  f = new TreeNode(4);

        TreeNode  g = new TreeNode(7);
        TreeNode  j = new TreeNode(2);
        TreeNode  h = new TreeNode(5);
        TreeNode  i = new TreeNode(1);

        a.setLeft(b);
        a.setRight(c);

        b.setLeft(d);

        c.setLeft(e);
        c.setRight(f);

        d.setLeft(g);
        d.setRight(j);

        f.setLeft(h);
        f.setRight(i);
        //[5,4,8,11,null,13,4,7,2,null,null,5,1] 22

        aa.pathSum(a,22);
        System.out.println();
    }

    List<List<Integer>> lists = new ArrayList<List<Integer>>();

    List<Integer> list = new ArrayList<Integer>();

    List<Integer> temp = null;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        calc(root,sum,list);

        return lists;
    }

    //使用sum计算和，使用list保存路径
    public void calc(TreeNode node ,int sum, List<Integer> list){
        if(node != null){

            list.add(node.val);

            calc(node.left,sum,list);

            //该结点是叶子结点，计算结果
            if(node.left == null && node.right == null){
                if(sum == sum(list)){
                    temp = new ArrayList<Integer>();
                    for(int i = 0 ; i < list.size() ; i ++){
                        temp.add(list.get(i));
                    }
                    lists.add(temp);
                }
            }

            calc(node.right,sum,list);

            list.remove(list.size() - 1);//移除该节点

        }
    }

    public int  sum(List<Integer> list){
        int sum  = 0;

        for(int i = 0 ; i < list.size(); i++){
            sum = sum + list.get(i);
        }

        return sum;
    }
}
