package com.yaoli.ajob;

/**
 * Created by will on 2017/2/7.
 * https://leetcode.com/problems/rotate-array/
 *
 * 多种解法
 * http://www.cnblogs.com/grandyang/p/4298711.html
 *
 */
public class _189_RotateArray {

    public void reverse(int [] nums,int begin ,int end){
        for(int i = begin ; i < (begin + end) / 2; i ++){
            int temp = nums[i];
            //nums[i] = nums[]
        }
    }

    //未解决
    public void rotate(int[] nums, int k) {



        for(int i = 0 ; i < (nums.length - k) / 2; i ++){
            int temp = nums[i];
            nums[i] = nums[nums.length - k - 1 -i];
            nums[nums.length - k - 1 -i] = temp;
        }

        for(int i = nums.length - k; i < nums.length - (nums.length - k)/2 ; i ++){
            int temp = nums[i];
            nums[i] = nums[nums.length - 1 -i];
            nums[nums.length - 1 -i] = temp;
        }

        for(int i = 0 ; i < nums.length / 2 ; i ++){
            int tmp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = tmp;
        }


    }


    public void rotate1(int[] nums, int k) {
        int [] copy = new int[nums.length];

        for(int i = 0 ; i < copy.length ;i++){
            copy[i] = nums[i];
        }

        for(int i = 0 ; i < copy.length ; i ++){
            nums[(i + k) % copy.length ] = copy[i];
        }
    }
}
