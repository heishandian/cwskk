package com.yaoli.ajob;


/**
 * Created by will on 2017/2/22.
 */
public class _190_ReverseBits {

    public static void main(String[] args) {
        System.out.println(reverseBits(1));
    }

    public static int reverseBits(int n) {
        int result = 0;
        for(int i=0; i<32; i++){
            result <<= 1;
            result += n&1;
            n >>= 1;
        }
        return result;
    }
}
