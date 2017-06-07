package com.yaoli.ajob;

/**
 * Created by will on 2017/3/19.
 *
 * 第一个不重复的字符
 *
 * 思想：
 *
 * 首先要保留顺序
 *
 * 要记录多少个
 *
 * i -> char ->num
 */
public class _JianZhi055 {
    public static void main(String[] args) {
        String a = "google";

        int point = 0;

        int poinis [] = new int[256];

        int nums[] = new int[256];

        for (int i = 0; i < a.length() ; i++) {
            if(nums[a.charAt(i)] == 0){
                poinis[point] = a.charAt(i);
                point++;
            }
            nums[a.charAt(i)] ++;
        }

        for (int i = 0; i < point; i++) {
            if(nums[poinis[i]] == 1){
                System.out.println((char)poinis[i]);
                break;
            }
        }


    }


}
