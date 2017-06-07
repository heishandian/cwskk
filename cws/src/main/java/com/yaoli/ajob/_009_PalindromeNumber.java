package com.yaoli.ajob;

/**
 * Created by will on 2017/3/2.
 */
public class _009_PalindromeNumber {
    public static void main(String[] args) {
        isPalindrome(-2147447412);
    }

    public static boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        int length = 0;

        int temp = x;

        while(temp != 0){
            temp = temp / 10;
            length ++;
            if(temp == 0){
                break;
            }
        }

        if(length % 2 == 0){ //长度为偶数
            int tmp = x;
            int res = 0;
            int num = 0;
            int i = 0;
            while( i < length / 2 ){
                num = tmp % 10;
                tmp = tmp / 10;
                res = res * 10 + num;
                i++;
            }
            if(res == tmp){
                return true;
            }
        }else if(length % 2 == 1){ //长度为奇数
            int tmp = x;
            int res = 0;
            int num = 0;
            int i = 0;
            while( i < length / 2 ){
                num = tmp % 10;
                tmp = tmp / 10;
                res = res * 10 + num;
                i++;
            }

            num = tmp % 10;
            res = res * 10 + num;

            if(res == tmp){
                return true;
            }

        }

        return false;
    }
}
