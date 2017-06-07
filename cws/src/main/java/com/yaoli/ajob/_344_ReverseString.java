package com.yaoli.ajob;

/**
 * Created by will on 2017/2/21.
 */
public class _344_ReverseString {
    public String reverseString(String s) {
        char [] str = s.toCharArray();
        for(int i = 0 ; i < str.length / 2 ; i++){
            char temp = str[i];
            str[i] = str[str.length - i - 1];
            str[str.length - i - 1] = temp;
        }

        return String.valueOf(str);
    }
}
