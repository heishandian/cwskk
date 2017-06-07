package com.yaoli.ajob;

/**
 * Created by will on 2017/2/21.
 */
public class _461_HammingDistance {
    public static void main(String[] args) {
        hammingDistance(1,4);
    }

    //long shrot
    public static int hammingDistance(int x, int y) {
        if(x < y){//如果 x < y ,交换
            int temp = x;
            x = y;
            y = temp;
        }

        int count = 0;
        while(x != 0 && y != 0){

            int tempx = x%2;
            int tempy = y%2;
            if(tempx != tempy){
                count ++;
            }

            x = x / 2;
            y = y / 2;
        }

        while(x != 0){
            int tempx = x % 2;
            if(tempx == 1){
                count ++;
            }
            x = x / 2;
        }

        return count;
    }
}

