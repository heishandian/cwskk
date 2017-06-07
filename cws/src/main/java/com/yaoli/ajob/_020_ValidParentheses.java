package com.yaoli.ajob;

import java.util.Stack;

/**
 * Created by will on 2017/2/19.
 */
public class _020_ValidParentheses {
    public static void main(String[] args) {
        _020_ValidParentheses ab =new  _020_ValidParentheses();
        ab.isValid("()");
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();

        boolean flag = true;
        for(int i  = 0; i < s.length() ; i++){
            if(s.charAt(i) == '{' ||  s.charAt(i) == '[' ||  s.charAt(i) == '(' ){
                stack.push(s.charAt(i));
            }else if(s.charAt(i) == '}' || s.charAt(i) == ']' || s.charAt(i) == ')'){
                char left = stack.empty() ? '.' : stack.pop();
                if( (left == '{' && s.charAt(i)  == '}') || (left == '[' && s.charAt(i)  == ']') || (left == '(' && s.charAt(i)  == ')') ){

                }else{
                    return false;
                }
            }
        }

        if(stack.size() > 0){
            return false;
        }


        return flag;


    }
}
