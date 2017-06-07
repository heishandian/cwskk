package com.yaoli.ajob;

import java.util.Stack;



/**
 * Created by will on 2017/2/12.
 */
public class _206_ReverseLinkedList {
    public static void main(String[] args) {

    }

    public static ListNode reverseList(ListNode head) {

        Stack<ListNode> stack = new Stack<ListNode>();

        if(head == null){
            return head;
        }

        while(head != null){
            stack.push(head);

            head = head.next;
        }

        ListNode node = stack.pop();

        ListNode point = node;

        while(!stack.empty()){
            point.next = stack.pop();
            ///切记 这里 要把指针取消
            point.next.next = null;
            point = point.next;
        }

        return node;

    }
}
