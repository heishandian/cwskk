package com.yaoli.ajob;


/**
 * Created by will on 2017/3/1.
 */
public class _leftNumInCircle {
    public static void main(String[] args) {
        System.out.println(calc(100,3));
    }

    /**
     *
     * @param n 一共有n个数字，分别是 0 到 n - 1
     * @param m 从数字0 开始，删除第 m 个数字
     * @return
     */
    public static int calc(int n,int m){

        int nums [] = new int[n];

        int left = n;

        int index = 0;//数组索引

        int count = 0;//指针

        while(left != 1){

            if(nums[index] == 0) { //可以计数
                count++;//数量 加1
            }

            if(count == m){ //数量与指定的m相等
                nums[index] = 1;//删除某个数字
                count = 0;
                left --;
            }

            index ++;//指针加1

            if(index == nums.length){
                index = 0;
            }
        }

        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] == 0){
                return i;
            }
        }

        return -1;
    }
}
