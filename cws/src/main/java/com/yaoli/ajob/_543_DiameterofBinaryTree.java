package com.yaoli.ajob;

/**
 * Created by siemens on 3/20/2017.
 */
public class _543_DiameterofBinaryTree {
    public static void main(String[] args) {

        /**
         * [4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2]
         */
        _543_DiameterofBinaryTree diameterofBinaryTree = new _543_DiameterofBinaryTree();
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);

        a.setLeft(b);
        a.setRight(c);

        b.setLeft(d);
        b.setRight(e);

        System.out.println(diameterofBinaryTree.diameterOfBinaryTree(a));
    }

    public int diameterOfBinaryTree(TreeNode root) {
        calc(root);

        return maxlen;
    }

    int maxlen = Integer.MIN_VALUE;

    public void calc(TreeNode node){
        if (node != null) {
            int temp = getHigh(node.getLeft()) + getHigh(node.getRight()) ;
            if(temp > max){
                max = temp;
            }
            calc(node.left);
            calc(node.right);
        }
    }

    public int getHigh(TreeNode node){
        int rightHigh,leftHigh;
        if(node == null){
            return 0;
        }else{
            rightHigh = getHigh(node.getLeft());
            leftHigh = getHigh(node.getRight());

            return (leftHigh > rightHigh ? leftHigh : rightHigh) + 1;
        }
    }












    public int diameterOfBinaryTree2(TreeNode root) {
        if(root==null)return 0;
        help(root,0);
        preorder(root);
        return max;

    }
    private int help(TreeNode root,int deep){
        if(root==null){
            return deep;
        }
        deep++;
        int leftdeep=help(root.left,deep);
        int rightdeep=help(root.right,deep);
        root.val=leftdeep+rightdeep-2*deep;
        return Math.max(leftdeep,rightdeep);
    }
    int max=0;
    private void preorder(TreeNode root){
        if(root==null){return;}
        max=Math.max(max,root.val);
        preorder(root.right);
        preorder(root.left);
    }
}
