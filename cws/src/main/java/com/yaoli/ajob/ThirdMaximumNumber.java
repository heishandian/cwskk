package com.yaoli.ajob;

import java.util.Set;
import java.util.HashSet;

/**
 * Created by will on 2017/2/7.
 */
public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        int mid = Integer.MIN_VALUE;
        int min = Integer.MIN_VALUE;

        Set<Integer> sets = new HashSet<Integer>();

        for(int i = 0 ; i < nums.length ; i ++){
            if(!sets.contains(nums[i])){
                sets.add(nums[i]);

                int temp = nums[i];
                if(min < temp){
                    min = temp;
                }

                if(mid < min){
                    int temp1 = min;
                    min = mid;
                    mid = temp1;
                }

                if(max < mid){
                    int temp1 = max;
                    max = mid;
                    mid = temp1;

                }
            }
        }

        if(sets.size() == 1 || sets.size() == 2){
            return max;
        }else{
            return min;
        }

    }
}
