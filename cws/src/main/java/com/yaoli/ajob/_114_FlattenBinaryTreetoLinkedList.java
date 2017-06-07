package com.yaoli.ajob;
import java.util.ArrayList;

/**
 * Created by siemens on 3/20/2017.
 */
public class _114_FlattenBinaryTreetoLinkedList {
    public static void main(String[] args) {
        _114_FlattenBinaryTreetoLinkedList test = new _114_FlattenBinaryTreetoLinkedList();

        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(5);

        TreeNode d = new TreeNode(3);
        TreeNode e = new TreeNode(4);
        TreeNode f = new TreeNode(6);

        a.setLeft(b);
        a.setRight(c);

        b.setLeft(d);
        b.setRight(e);

        c.setRight(f);

        test.flatten(a);
    }


    public void flatten(TreeNode root) {
        calc(root);
    }

    public void calc(TreeNode node) {
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();

        ArrayList<TreeNode> result = new ArrayList<TreeNode>();

        list.add(node);

        result.add(node);

        while (list.size() != 0) {

            int length = list.size();

            TreeNode temp;
            for (int i = 0; i < length; i++) {
                temp = list.get(0);

                if (temp.left != null) {
                    list.add(temp.left);
                    result.add(temp.left);
                }

                if (temp.right != null) {
                    list.add(temp.right);
                    result.add(temp.right);
                }
            }

            while (length != 0) {
                list.remove(0);
                length--;
            }
        }
        TreeNode tmp;
        for (int i = 0; i < result.size() - 1; i++) {
            tmp = result.get(i);
            tmp.left = null;
            tmp.right = result.get(i + 1);
        }
    }
}