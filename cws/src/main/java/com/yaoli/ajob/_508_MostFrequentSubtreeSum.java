package com.yaoli.ajob;

import java.util.*;

/**
 * Created by siemens on 3/20/2017.
 *
 * 基本思想：
 *
 * 计算 两个子数目 之和。
 */
public class _508_MostFrequentSubtreeSum {
    public static void main(String[] args) {
        TreeNode a = new TreeNode(5);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(-5);

        a.setLeft(b);
        a.setRight(c);

        _508_MostFrequentSubtreeSum mostFrequentSubtreeSum = new _508_MostFrequentSubtreeSum();
        int s [] =mostFrequentSubtreeSum.findFrequentTreeSum(a);
        System.out.println(s);

    }

    Map<Integer,Integer> map = new HashMap<Integer,Integer>();

    public int[] findFrequentTreeSum(TreeNode root) {
        calc(root);

        int max = Integer.MIN_VALUE;
        for (Integer val : map.keySet()) {
            max = map.get(val) > max ? map.get(val) : max;
        }

        List<Integer> list = new ArrayList<Integer>();

        for(Integer val : map.keySet()){
            if(map.get(val) == max){
                list.add(val);
            }
        }

        int result [] = new int[list.size()];
        for(int i = 0 ; i < list.size() ; i++){
            result [i] = list.get(i);
        }

        return result;
    }

    public int calc(TreeNode node){
        if(node == null){
            return 0;
        }else{
            int sum = calc(node.left) + calc(node.right) + node.val;
            if(!map.containsKey(sum)){
                map.put(sum,1);
            }else{
                map.put(sum,map.get(sum)+1);
            }
            return sum;
        }
    }


}
