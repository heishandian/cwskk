package com.yaoli.ajob;

import sun.font.TrueTypeFont;

/**
 * Created by will on 2017/2/21.
 */
public class _125_ValidPalindrome {
    public static void main(String[] args) {
        //System.out.println(isPalindrome("\"\\\"Sue,\\\" Tom smiles, \\\"Selim smote us.\\\"\""));
        System.out.println(isPalindrome("0 0 P"));
    }
    public static boolean isPalindrome(String s) {
        boolean res = true;

        int begin = 0 ;
        int end = s.length() - 1;

        while(begin <= end){

            //该方法，哪怕整个字符串 都是 符号，那也返回 第一个是字符了，
            for(int i = begin ; i <= end ; i++){
                if( Character.isLetterOrDigit(s.charAt(i)) ){ // 2）如果这里 一直没有找到，就指向了某个 字符
                    begin = i ; //
                    break;
                }else{
                    continue;
                }
            }

            //同上
            for(int i = end ; i >= begin ; i--){
                if( Character.isLetterOrDigit(s.charAt(i)) ){
                    end = i;
                    break;
                }else{
                    continue;
                }
            }


            // 整个字符串 都没有字符，全是标点
            if(!Character.isLetterOrDigit(s.charAt(begin)) && !Character.isLetterOrDigit(s.charAt(end))){
                end--;
                begin++;
                //都必须是字符，并且相等
            }else if(Character.isLetterOrDigit(s.charAt(begin)) && Character.isLetterOrDigit(s.charAt(end)) && Character.toLowerCase(s.charAt(begin)) == Character.toLowerCase(s.charAt(end))){
                end--;// 1） 这里 -- 之后 指向的是前一个 字符 可能是 符号 可能是字母，已经指向了 下一个字符
                begin++;//++ 之后 指向的是前一个 字符 可能是符号 可能是 字母
            }else{
                return false;
            }
        }


        return res;
    }

    public static boolean isPalindrome2(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;
        while(head <= tail) {
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if (!Character.isLetterOrDigit(cHead)) {
                head++;
            } else if(!Character.isLetterOrDigit(cTail)) {
                tail--;
            } else {
                if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                    return false;
                }
                head++;
                tail--;
            }
        }

        return true;
    }
}
