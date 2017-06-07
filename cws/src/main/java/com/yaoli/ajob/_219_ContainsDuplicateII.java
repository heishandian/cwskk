package com.yaoli.ajob;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by will on 2017/2/22.
 */
public class _219_ContainsDuplicateII {
    public static void main(String[] args) {
        int a [] = {1,2};

        containsNearbyDuplicate(a,1);
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums.length <= 1){
            return false;
        }

        Map<Integer,int []> map = new HashMap<Integer,int[]>();

        for(int i = 0 ; i < nums.length ; i ++){
            if(!map.containsKey(nums[i])){
                int a [] = {i,i};
                map.put(nums[i],a);
            }else{
                int a [] = map.get(nums[i]);
                if(a[1] == a[0] || a[1] - a[0] > i - a[1] ){
                    a[0] = a[1];
                    a[1] = i;
                }
                map.put(nums[i],a);
            }
        }

        for(Integer i : map.keySet()){
            if(map.get(i)[1] - map.get(i)[0] <= k &&
                    (map.get(i)[1] != map.get(i)[0])){
                return true;
            }
        }

        return false;
    }
}
