package com.yaoli.ajob;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by will on 2017/2/7.
 */
public class _167_TwoSumIIInputarrayissorted {
    public static void main(String[] args) {
        int a [] = {2,3,4};
        twoSum(a,6);
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
    }



    public static int[] twoSum(int[] numbers, int target) {
        int a = -1;
        int b = -2;

        for(int i = 0 ; i < numbers.length ;i++){
            int result = target - numbers[i];
            if(result == numbers[i] && i + 1 <= numbers.length - 1 && numbers[i] == numbers[i+1] ){
                int c [] =new int[2];
                c[0] = i + 1;
                c[1] = i + 2;
                return c;
            }else{
                int temp = find(numbers,result);
                if(temp != -1){
                    int c [] =new int[2];
                    c[0] = i + 1;
                    c[1] = temp + 1;
                    return c;
                }
            }
        }

        return null;
    }

    /**
     * 二分查找方法
     * @param numbers
     * @param num
     * @return
     */
    public static int find(int [] numbers,int num){
        int begin = 0;
        int end = numbers.length - 1;


        while(begin <= end){
            int mid = (end + begin) / 2;
            if(num == numbers[mid]){
                return mid;
            }else{
                if(num > numbers[mid]){
                    begin = mid + 1 ;
                }else if(num < numbers[mid] ){
                    end = mid - 1;
                }

            }
        }

        return -1;
    }
}
