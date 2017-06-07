package com.yaoli.ajob;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 * Created by will on 2017/2/10.
 */
public class _349_IntersectionofTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map1 = new HashMap<Integer,Integer>();
        Map<Integer,Integer> map2 = new HashMap<Integer,Integer>();

        for(Integer a : nums1){
            map1.put(a,1);
        }

        for(Integer a : nums2){
            map2.put(a,1);
        }

        List<Integer> list = new ArrayList<Integer>();

        if(map1.size() > map2.size()){
            for(Integer c : map2.keySet()){
                if(map1.containsKey(c)){
                    list.add(c);
                }
            }
        }else{
            for(Integer b : map1.keySet()){
                if(map2.containsKey(b)){
                    list.add(b);
                }
            }
        }

        int res [] = new int[list.size()];

        for(int i = 0 ; i < list.size() ; i++){
            res[i] = list.get(i);
        }

        return res;

    }
}
