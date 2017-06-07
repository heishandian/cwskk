package com.yaoli.ajob;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by will on 2017/2/12.
 */
public class _234_PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        List<ListNode> lists = new ArrayList<ListNode>();

        while(head!= null){
            lists.add(head);
            head = head.next;
        }

        for(int i = 0 ; i < lists.size() / 2 ; i++){
            if(lists.get(i).val != lists.get(lists.size() - 1 -i).val){
                return false;
            }
        }

        return true;
    }
}
