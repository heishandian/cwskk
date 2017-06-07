package com.yaoli.ajob;

/**
 * Created by will on 2017/3/3.
 */
public class _108_ConverSortedArraytoBinarySearchTree {
    public static void main(String[] args) {
        _108_ConverSortedArraytoBinarySearchTree a = new _108_ConverSortedArraytoBinarySearchTree();
        int nums[] = {1,3};
        a.sortedArrayToBST(nums);
        System.out.println();
    }

    TreeNode root;

    public TreeNode sortedArrayToBST(int[] nums) {
        calc(nums,0,nums.length - 1);

        return root;
    }

    public void calc(int [] nums, int begin, int end){
        if(begin <= end){
            int middle = (begin + end) / 2;
            add(root,nums[middle]);
            calc(nums,begin,middle - 1);
            calc(nums,middle + 1 , end);
        }
    }

    public void add(TreeNode node,int val){
        if(node != null){
            if(node.val > val){
                if(node.left == null){
                    node.left = new TreeNode(val);
                }else{
                    add(node.left,val);
                }
            }else{
                if(node.right == null){
                    node.right = new TreeNode(val);
                }else{
                    add(node.right,val);
                }
            }
        }else{
            this.root = new TreeNode(val);
        }
    }
}
