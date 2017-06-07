package com.yaoli.ajob;

import java.util.Stack;
/**
 * Created by will on 2017/2/12.
 */
public class _160_IntersectionofTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Stack<ListNode> stacka = new Stack<ListNode>();
        Stack<ListNode> stackb = new Stack<ListNode>();

        while(headA != null){
            stacka.push(headA);
            headA = headA.next;
        }

        while(headB != null){
            stackb.push(headB);
            headB = headB.next;
        }

        ListNode result = null;

        while(!stacka.empty() && !stackb.empty()){
            if(stacka.peek() != stackb.peek()){
                return result;
            }else{
                result = stacka.pop();
                stackb.pop();
            }
        }

        return result;
    }
}
