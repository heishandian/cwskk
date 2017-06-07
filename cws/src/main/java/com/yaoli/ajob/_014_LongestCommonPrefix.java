package com.yaoli.ajob;

import java.util.Arrays;

/**
 * Created by will on 2017/3/2.
 */
public class _014_LongestCommonPrefix {
    public static void main(String[] args) {
        String a [] = {"ac","acgd","aca","ac",""};
        longestCommonPrefix(a);
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }else if(strs.length == 1){
            return strs[0];
        }

        String res = "";

        int i = 0;

        boolean flag = true;

        char temp;

        while(flag == true){
            if( i >= strs[0].length()){
                break;
            }else{
                temp = strs[0].charAt(i);
            }


            for(int j = 1 ; j < strs.length ; j++ ){
                if( i < strs[j].length() ){
                    if(temp != strs[j].charAt(i)){
                        flag = false;
                    }
                }else{
                    flag = false;
                    break;
                }
            }

            i++;

            if(flag == false){
                break;
            }else{
                res = res + temp;
            }
        }


        return res;
    }
}
