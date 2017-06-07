package com.yaoli.ajob;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siemens on 3/20/2017.
 */
public class _199_BinaryTreeRightSideView {
    public static void main(String[] args) {

    }

    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();

        ArrayList<Integer> result = new ArrayList<Integer>();

        list.add(root);
        while(list.size() != 0){

            //这一层含有多少个节点
            int length = list.size();
            TreeNode temp;
            for(int i = 0 ; i < length ; i++){
                temp = list.get(i);// 获得 第i个节点

                if(temp.left != null){
                    list.add(temp.left);
                }

                if(temp.right !=null){
                    list.add(temp.right);
                }
            }

            while(length != 0){
                if(length == 1){
                    result.add(list.get(0).val);
                }
                list.remove(0);
                length --;
            }

        }

        return result;
    }
}
