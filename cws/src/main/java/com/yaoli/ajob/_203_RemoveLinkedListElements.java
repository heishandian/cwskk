package com.yaoli.ajob;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by will on 2017/2/12.
 */
public class _203_RemoveLinkedListElements {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);

        ListNode node2 = new ListNode(2);

        ListNode node66 = new ListNode(6);

        ListNode node3 = new ListNode(3);

        ListNode node4 = new ListNode(4);

        ListNode node5 = new ListNode(5);

        ListNode node6 = new ListNode(6);

        node5.next = node6;

        node4.next = node5;

        node3.next = node4;

        node66.next = node3;

        node2.next = node66;

        node1.next = node2;

        removeElements(node1,6);

    }
    public static ListNode removeElements(ListNode head, int val) {

        Queue<ListNode> queue = new LinkedList<ListNode>();

        ListNode temp = null;

        while(head != null){
            if(head.val != val){
                temp = head;//给定一个临时变量
                head = head.next;
                temp.next = null;
                queue.offer(temp);

            }else{
                head = head.next;
            }
        }

        ListNode node = queue.poll();
        ListNode point = node;


        while(queue.size() != 0){
            point.next = queue.poll();
            point = point.next;
        }

        return node;
    }
}
