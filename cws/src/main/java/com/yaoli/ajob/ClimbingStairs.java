package com.yaoli.ajob;

/**
 * Created by will on 2017/1/3.
 * https://leetcode.com/problems/climbing-stairs/
 */
public class ClimbingStairs {

    public static int count = 1;

    public int climbStairs(int n){
        Node root = new Node(0);
        root.value = 0;

        return 0;
    }

    public void calc(Node node, int tempSum,int sum){
        if(node == null){
            node.right = new Node(1);
            node.left = new Node(2);
        }

        if(tempSum + 1 == sum){
            count = count + 1;
            calc(node,tempSum + 1,sum);
        }else if(tempSum + 2 == sum){
            count = count + 1;
            calc(node,tempSum + 2,sum);
        }
    }

    class Node{
        int value;
        Node left;
        Node right;

        public Node(int value){
            this.value = value;
        }
    }

}
