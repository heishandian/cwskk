package com.yaoli.ajob;

/**
 * Created by will on 2017/2/28.
 */
public class _263_UglyNumber {
    public static void main(String[] args) {
        System.out.println(isUgly(6));
    }

    public static boolean isUgly(int num) {
        if(num == 0){
            return false;
        }
        if(num == 1){
            return true;
        }

        while(num % 2 == 0){
            num = num / 2;
        }

        while(num % 3 == 0){
            num = num / 3;
        }

        while(num % 5 == 0){
            num = num / 5;
        }

        if(num == 1){
            return true;
        }else{
            return false;
        }

    }
}
