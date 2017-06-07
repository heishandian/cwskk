package com.yaoli.ajob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by will on 2017/3/3.
 */
public class _501_FindModeinBinarySearchTree {
    public static void main(String[] args) {
        _501_FindModeinBinarySearchTree b = new _501_FindModeinBinarySearchTree();

    }

    Map<Integer,Integer> map = new HashMap<Integer,Integer>();

    public int[] findMode(TreeNode root) {
        calc(root);

        List<Integer> list = new ArrayList<Integer>();
        int max = 0;
        for(Integer i : map.keySet()){
            if(map.get(i) > max){
                list.clear();
                list.add(i);
                max = map.get(i);
            }else if(map.get(i) == max){
                list.add(i);
            }
        }

        int a [] = new int[list.size()];
        for(int i = 0 ;i < list.size() ; i++){
            a[i] = list.get(i);
        }

        return a;
    }

    public void calc(TreeNode node){
        if(node != null){
            calc(node.left);

            if(!map.containsKey(node.val)){
                map.put(node.val,1);
            }else{
                map.put(node.val,map.get(node.val)+1);
            }

            calc(node.right);
        }
    }
}
