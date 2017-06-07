package com.yaoli.ajob;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by will on 2017/1/3
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/.
 */
public class RemoveDuplicatesfromSortedArray {
    public static int removeDuplicates(int [] nums){

        int length = nums.length;

        int result = 0;

        List<Integer> a = new ArrayList<Integer>();

        if(length == 1){
            result = 1;
            a.add(nums[0]);
        }else{
            for(int i = 0 ; i < length - 1 ;){
                int num = nums[i];
                int total = 0;
                int j = i;
                result++;
                a.add(num);

                int tempCount = 0;
                for( ; j < length - 1 ; j++){
                    total = total + nums[j];
                    int count = j - i + 1;
                    if( (double)total / (double)count ==(double) num){
                        tempCount++;
                        continue;
                    }else{
                        i = j;
                        break;
                    }
                }

                if( (i + tempCount == length - 1)  && (i == length - 2) && (j == length - 2)){
                    if(nums[length -1] != nums[length-2]){
                        result++;
                        a.add(num);
                    }
                    break;
                }
            }
        }




//        int tempResult [] = new int[a.size()];
//        for(int i = 0 ; i < tempResult.length; i ++){
//            tempResult[i] = a.get(i);
//        }

        return result;
    }

    public static void main(String[] args) {
        int nums[]= {1,2,2};
        System.out.println(removeDuplicates(nums));
    }
}
