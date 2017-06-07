package com.yaoli.ajob;

import java.util.Map;
import java.util.HashMap;
/**
 * Created by will on 2017/2/12.
 */
public class _290_WordPattern {
    public static void main(String[] args) {
        String a = "abba";
        String b = "dog cat cat dog";

        System.out.println(wordPattern(b,a));
    }

    public static boolean wordPattern(String pattern, String str) {
        String temp = str;
        str = pattern;
        pattern = temp;

        String patterns [] = pattern.split(" ");

        if(patterns.length != str.length()){
            return false;
        }

        Map<String,String> mapp = new HashMap<String,String>();
        Map<Character,String> maps = new HashMap<Character,String>();

        for(int i = 0 ;i < patterns.length ; i++){
            if(!mapp.containsKey(patterns[i])){
                mapp.put(patterns[i],mapp.get(patterns[i])+i);
            }else{
                mapp.put(patterns[i],""+i);
            }
        }

        for(int i = 0 ;i < str.length() ; i++){
            if(!maps.containsKey(str.charAt(i))){
                maps.put(str.charAt(i),maps.get(str.charAt(i)) + i);

            }else{
                maps.put(str.charAt(i),""+i);
            }
        }

        for(int i = 0 ; i < patterns.length ; i++){
            if(!mapp.get(patterns[i]).equals(maps.get(str.charAt(i)))){
                return false;
            }
        }

            return true;


    }
}
