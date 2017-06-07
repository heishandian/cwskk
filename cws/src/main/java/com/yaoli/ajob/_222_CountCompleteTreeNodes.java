package com.yaoli.ajob;

/**
 * Created by siemens on 3/21/2017.
 *
 * 这道题 运行时间过长
 *
 * 不清楚为什么
 *
 * 思想是这样的
 *
 * 对于                  *
 *                    *    *
 *                  *  *  *  *
 *                 *  *
 * 这样一棵树，显示计算最左边的额高度
 *
 * 然后计算 右边节点的高度，如果两者 相差 1，表示右边的是一个完全二叉树，此时可以计算出左边的 所有节点值，包括根节点
 *
 * 如果不为 1 ，就计算右边的所有值 包括节点值
 *
 * 这样 递归计算 即可。
 *
 *
 */
public class _222_CountCompleteTreeNodes {
    public static void main(String[] args) {
        _222_CountCompleteTreeNodes test = new _222_CountCompleteTreeNodes();
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

        //System.out.println(test.countNodes(a));
        System.out.println(test.count(a));
    }

    private int getHight(TreeNode node){
        return node == null ? 0 : getHight(node.left) + 1;
    }

    int count(TreeNode node){
        int h = getHight(node);
        int num = 0;
        while(node != null){
            int temp = getHight(node.right);
            if(temp == h-1){
                node = node.right;
                //num = (int)Math.pow((double)2,(double)(h - 1)) + num;

                num = (1 << (h - 1)) + num;
            }else{
                node = node.left;
                //num = (int)Math.pow((double)2,(double)(temp)) + num;
                num = (1 << temp) + num;
            }
            h --;
        }

        return num;
    }

    int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }
    public int countNodes(TreeNode root) {
        int nodes = 0, h = height(root);
        while (root != null) {
            if (height(root.right) == h - 1) {
                nodes += 1 << h;
                root = root.right;
            } else {
                nodes += 1 << h-1;
                root = root.left;
            }
            h--;
        }
        return nodes;
    }
}
