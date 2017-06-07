package com.yaoli.ajob;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by will on 2017/3/5.
 */
public class _513_FindBottomLeftTreeValue {
    public int findBottomLeftValue(TreeNode root) {
        int leftval = 0 ;

        List<TreeNode> list = new ArrayList<TreeNode>();

        list.add(root);

        while(list.size()!= 0){

            leftval = list.get(0).val;

            int length = list.size();

            while(length != 0){
                TreeNode node = list.get(0);

                if(node.left != null){
                    list.add(node.left);
                }

                if(node.right != null){
                    list.add(node.right);
                }

                length --;

                list.remove(0);
            }


        }

        return leftval;
    }
}
