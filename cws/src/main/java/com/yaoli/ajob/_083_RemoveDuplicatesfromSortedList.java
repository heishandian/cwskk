package com.yaoli.ajob;

import java.util.Queue;
import java.util.LinkedList;
/**
 * Created by will on 2017/2/12.
 */
public class _083_RemoveDuplicatesfromSortedList {
    public ListNode deleteDuplicates(ListNode head) {

        Queue<ListNode> queue = new LinkedList<ListNode>();

        if(head == null){
            return head;
        }

        ListNode pre = null;
        ListNode temp ;
        while(head != null){
            if(pre == null || pre.val != head.val){
                temp = head;
                head = head.next;
                temp.next = null;
                queue.offer(temp);
                pre = temp;
            }else{
                head = head.next;
            }
        }

        ListNode result = queue.poll();

        ListNode point = result;

        while(queue.size() != 0){
            point.next = queue.poll();
            point = point.next;
        }

        return result;

    }
}
