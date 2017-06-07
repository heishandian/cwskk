package com.yaoli.ajob;

/**
 * Created by will on 2017/2/17.
 */
public class _441_ArrangingCoins {
    public static void main(String[] args) {

        //System.out.println(0x11 >>> 1);
        System.out.println(arrangeCoins2(1804289383));
    }

    public static int arrangeCoins(int n) {
        int start = 0;
        int end = n;
        int mid = 0;
        while (start <= end){
            mid = (start + end) / 2;

            if (((1+mid) * 0.5  * mid) <= n){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return start - 1;
    }

    public static int arrangeCoins2(int n) {

        for(int i = (int )Math.ceil((double)n / (double) 2); i >= 0 ; i--){
            if( (1+i) * 0.5  * i <= n){
                return i;
            }
        }

        return n;
    }
}
