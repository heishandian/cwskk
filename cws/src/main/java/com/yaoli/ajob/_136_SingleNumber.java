package com.yaoli.ajob;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by will on 2017/2/10.
 */
public class _136_SingleNumber {
    public int singleNumber(int[] nums) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();

        for(int i = 0 ; i < nums.length ; i++){
            if(map.containsKey(nums[i])){
                map.remove(nums[i]);
            }else{
                map.put(nums[i],1);
            }
        }

        for(Integer c : map.keySet()){
            return c;
        }

        return 0;
    }
}
