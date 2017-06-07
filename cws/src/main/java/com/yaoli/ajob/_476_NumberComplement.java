package com.yaoli.ajob;

/**
 * Created by will on 2017/2/21.
 */
public class _476_NumberComplement {
    public static void main(String[] args) {
        System.out.println(findComplement(1));
    }
    public static int findComplement(int num) {
        char [] str = Integer.toBinaryString(num).toCharArray();

        for(int i = 0 ; i < str.length ; i++){
            if( str[i] == '0' ){
                str[i] = '1';
            }else{
                str[i] = '0';
            }
        }

        return Integer.parseInt(String.valueOf(str),2);

    }
}
