package com.yaoli.ajob;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by will on 2017/2/9.
 */
public class _350_IntersectionofTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map1 = new HashMap<Integer,Integer>();
        Map<Integer,Integer> map2 = new HashMap<Integer,Integer>();

        for(int i = 0 ; i < nums1.length; i++){
            if(map1.containsKey(nums1[i])){
                map1.put(nums1[i],map1.get(nums1[i])+1);
            }else{
                map1.put(nums1[i],1);
            }
        }

        for(int i = 0 ; i < nums2.length; i++){
            if(map2.containsKey(nums2[i])){
                map2.put(nums2[i],map2.get(nums2[i])+1);
            }else{
                map2.put(nums2[i],1);
            }
        }

        Map<Integer,Integer> result = new HashMap<Integer,Integer>();

        if(map1.size() > map2.size()){
            for(Integer c : map2.keySet()){
                if(map1.get(c) != null){
                    result.put(c, map2.get(c) > map1.get(c) ? map1.get(c):map2.get(c));
                }
            }
        }else{
            for(Integer c : map1.keySet()){
                if(map2.get(c) != null){
                    result.put(c, map2.get(c) > map1.get(c) ? map1.get(c):map2.get(c));
                }
            }
        }

        int size = 0;
        for(Integer c : result.keySet()){
            size = size + result.get(c);
        }

        int b []= new int[size];
        int p = 0;

        for(Integer c : result.keySet()){
            int d  = result.get(c);
            for(int j = 0 ; j < d; j++){
                b[p] = c;
                p++;
            }
        }

        return b;

    }
}
