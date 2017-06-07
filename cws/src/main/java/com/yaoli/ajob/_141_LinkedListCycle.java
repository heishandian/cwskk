package com.yaoli.ajob;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by will on 2017/3/4.
 */
public class _141_LinkedListCycle {
    public static void main(String[] args) {
        _141_LinkedListCycle b = new _141_LinkedListCycle();

        ListNode a = new ListNode(1);

        a.next = a;

        b.hasCycle(a);
    }

    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }

        ListNode temp = head;
        Set<ListNode> set = new HashSet<ListNode>();

        boolean flag = true;
        while(flag == true){
            if(!set.contains(temp)){
                set.add(temp);
            }else{
                return true;
            }
            temp = temp.next;
            if(temp == null){
                return false;
            }
        }

        return false;

    }
}
