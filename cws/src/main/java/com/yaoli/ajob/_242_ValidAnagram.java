package com.yaoli.ajob;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by will on 2017/2/12.
 */
public class _242_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }

        Map<Character,Integer> maps = new HashMap<Character,Integer>();
        Map<Character,Integer> mapt = new HashMap<Character,Integer>();

        for(int i =  0 ; i < s.length() ; i++){
            if(!maps.containsKey(s.charAt(i))){
                maps.put(s.charAt(i),1);
            }else{
                maps.put(s.charAt(i),maps.get(s.charAt(i))+1);
            }

            if(!mapt.containsKey(t.charAt(i))){
                mapt.put(t.charAt(i),1);
            }else{
                mapt.put(t.charAt(i),mapt.get(t.charAt(i))+1);
            }
        }

        for(Character c : maps.keySet()){
            if(mapt.get(c) == null){
                return false;
            }
            if(!mapt.get(c).equals(maps.get(c))){
                return false;
            }
        }

        return true;

    }
}
