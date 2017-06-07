package com.yaoli.ajob;

/**
 * Created by will on 2017/2/8.
 */
public class _088_MergeSortedArray {
    public static void main(String[] args) {
        int a []= {0};
        int b [] = {1};

        merge(a,0,b,1);

        System.out.println("");
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int res [] = new int[m+n];

        int m1 = 0;
        int n1 = 0;
        int p = 0;

        while(m1 < m && n1 < n){
            if(nums1[m1] <= nums2[n1]){
                res[p] = nums1[m1];
                m1 ++;
                p++;
            }else{
                res[p] = nums2[n1];
                n1++;
                p++;
            }
        }

        if(m1 == m){//说明nums1排序完成，只要将 nums2 剩余的贴上
            int j = 0;
            for(int i = n1 ; i < n ; i ++){
                res[p + j]  = nums2[i];
                j++;
            }
        }

        if(n1 == n){//说明nums2排序完成，只要将 nums1 剩余的贴上
            int j = 0;
            for(int i = m1 ; i < m ; i ++){
                res[p + j]  = nums1[i];
                j++;
            }
        }

        for(int i = 0 ; i < m+ n; i++){
            nums1[i] = res[i];
        }
    }
}
