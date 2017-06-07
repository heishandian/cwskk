package com.yaoli.ajob;

import java.util.Arrays;
/**
 * Created by will on 2017/2/12.
 */
public class _345_ReverseVowelsofaString {
    public static void main(String[] args) {
        _345_ReverseVowelsofaString b = new _345_ReverseVowelsofaString();

        System.out.println(b.reverseVowels("ai"));

        System.out.println(b.reverseVowels(",."));
    }
    public String reverseVowels(String s) {
        char [] set = s.toCharArray();

        int begin = 0 ;
        int end = set.length - 1;

        while(begin < end){
            for(int i = begin ; i < set.length ; i ++){
                if(isV(set[i])){
                    begin = i;
                    break;
                }
            }

            for(int i = end; i>= 0 ; i--){
                if(isV(set[i])){
                    end = i;
                    break;
                }
            }

            if(begin < end && isV(set[begin]) && isV(set[end])){
                char temp = set[begin];
                set[begin] = set[end];
                set[end] = temp;

                begin ++;
                end --;
            }else{
                break;
            }


        }

        return String.valueOf(set);
    }

    public boolean isV(char c){
         if(c == 'a' || c == 'e' || c == 'i' || c  == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c  == 'O' || c == 'U'){
            return true;
        }else{
            return false;
        }

    }
}
