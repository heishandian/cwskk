package com.yaoli.ajob;

/**
 * Created by will on 2017/2/20.
 */
public class _MergeLinkedList {
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(5);
        ListNode d = new ListNode(7);

        ListNode e = new ListNode(2);
        ListNode f = new ListNode(4);
        ListNode g = new ListNode(6);
        ListNode h = new ListNode(8);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = null;

        e.next = f;
        f.next = g;
        g.next = h;
        h.next = null;

        merge(a,e);
    }

    public static ListNode merge(ListNode a , ListNode b){

        ListNode header = new ListNode(0);

        ListNode point = header;

        while(a != null && b != null){

            if(a.val > b.val){
                point.next = b;
                b = b.next;
            }else if(a.val < b.val){
                point.next = a;
                a = a.next;
            }

            point = point.next;

        }

        if(a == null){
            point.next = b;
        }

        if(b == null){
            point.next = a;
        }

        return header.next;
    }


}
