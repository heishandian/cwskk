package com.yaoli.ajob;

import java.util.Random;

/**
 * \\快速排序
 * FastOrder
 * Created by will on 2016/11/22. this is use to test 阿斯蒂芬阿斯蒂芬阿斯蒂芬
 */
public class FastOrder {
    public static int b = 10;

    public static void main(String[] args) {
        Random random = new Random();
        int nums [] = new int [10];
        for(int i = 0 ; i < 10 ; i ++){
            nums[i] = random.nextInt(100);
        }
        for(int i = 0 ; i < 10 ; i ++){
            System.out.print(nums[i]+"\t");
        }

        System.out.println();
        quickSort(nums,0,nums.length - 1);

        for(int i = 0 ; i < 10 ; i ++){
            System.out.print(nums[i]+"\t");
        }

    }

    public static void quickSort(int [] nums,int low,int high){
        if(low < high){
            int a = partion(nums,low,high);
            quickSort(nums,low,a-1);
            quickSort(nums,a+1,high);
        }
    }

    public static int partion(int [] nums,int low,int high){
        int temp = nums[low];
        while(low < high){
            while(low < high && nums[high] >= temp){
                high--;
            }
            nums[low] = nums[high];//把后面给前面
            while(low < high && nums[low] <= temp){
                low++;
            }
            nums[high] = nums[low];//把前面给后面
        }
        nums[low]=temp;
        return low;
    }
}
