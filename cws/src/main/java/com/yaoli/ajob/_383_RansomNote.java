package com.yaoli.ajob;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by will on 2017/3/3.
 */
public class _383_RansomNote {
    public static void main(String[] args) {
        canConstruct("aa","aab");
    }

    public static  boolean canConstruct(String ransomNote, String magazine) {
        Map<Character,Integer> map = new HashMap<Character,Integer>();

        for(int i = 0 ; i < magazine.length() ; i++){
            if(!map.containsKey(magazine.charAt(i))){
                map.put(magazine.charAt(i),1);
            }else{
                map.put(magazine.charAt(i),map.get(magazine.charAt(i))+1);
            }
        }

        Map<Character,Integer> rmap = new HashMap<Character,Integer>();
        for(int i = 0 ; i < ransomNote.length() ; i++){
            if(!rmap.containsKey(ransomNote.charAt(i))){
                rmap.put(ransomNote.charAt(i),1);
            }else{
                rmap.put(ransomNote.charAt(i),rmap.get(ransomNote.charAt(i))+1);
            }
        }

        for(int i = 0 ; i < ransomNote.length() ; i++){
            if(map.get(ransomNote.charAt(i)) == null || map.get(ransomNote.charAt(i)) == null || rmap.get(ransomNote.charAt(i)) > map.get(ransomNote.charAt(i))){
                return false;
            }
        }

        return true;
    }
}
