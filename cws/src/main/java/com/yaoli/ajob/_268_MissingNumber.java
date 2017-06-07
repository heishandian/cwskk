package com.yaoli.ajob;

import java.util.Arrays;

/**
 * Created by will on 2017/2/7.
 */
public class _268_MissingNumber {
    public int missingNumber(int[] nums) {

        Arrays.sort(nums);

        int result = nums.length;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i){
                result = i;
                break;
            }
        }

        return result;
    }
}
