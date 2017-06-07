package com.yaoli.ajob;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by will on 2017/3/3.
 */
public class _387_FirstUniqueCharacterinaString {
    public int firstUniqChar(String s) {
        Map<Character,Integer> map = new HashMap<Character,Integer>();

        for(int i = 0 ; i < s.length();i ++){
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),1);
            }else{
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }
        }

        for(int i = 0 ; i < s.length() ; i ++){
            if(map.get(s.charAt(i)) == 1){
                return i;
            }
        }

        return -1;
    }
}
