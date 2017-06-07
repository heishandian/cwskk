package com.yaoli.ajob;

/**
 * Created by will on 2017/2/27.
 */
public class _204_CountPrimes {
    public static void main(String[] args) {
        getPrime(2);
    }

    public static int getPrime(int n){
        if(n == 0 || n== 1){
            return 0;
        }

        n = n - 1;

        int set [] = new int[n+1];
        set[0] = 1;
        set[1] = 1;
        //是质数 全部为0
        for(int i = 2 ; i <= n; i ++){
            if(set[i] == 1){
                continue;
            }

            int j = i;
            for(j = i + j; j <= n ; j = j + i){
                set[j] = 1;//排除掉所有的 倍数
            }
            if(i*i > n){
                break;
            }
        }

        int count = 0;
        for(int i = 2 ; i < set.length ;i ++){
            if(set[i] == 0){
                count ++;
            }
        }



        return count;
    }



}
