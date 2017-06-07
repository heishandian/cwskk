package com.yaoli.ajob;

/**
 * Created by will on 2017/2/17.
 */
public class _367_ValidPerfectSquare {
    public static void main(String[] args) {
        System.out.println(isPerfectSquare(2147483647));
    }

    public static boolean isPerfectSquare(int num) {
        int begin = 0;
        int end = num;
        int mid = 0;

        boolean  flag = false;

        while(begin <= end){
            mid = (begin + end) >> 1;

            if(mid * 1.0 * mid < num){
                begin = mid + 1;
            }else if(mid * 1.0 * mid> num ){
                end = mid - 1;
            }else{
                return true;
            }
        }

        return flag;
    }
}
