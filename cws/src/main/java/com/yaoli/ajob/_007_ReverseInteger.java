package com.yaoli.ajob;

/**
 * Created by will on 2017/2/21.
 */
public class _007_ReverseInteger {
    public static void main(String[] args) {
        System.out.println(reverse(123));
    }

    public static int reverse(int x) {
        boolean fu = false;
        if (x < 0) {
            fu = true;
            x = x * -1;
        }
        long res = 0;

        while (x != 0) {
            res = res * 10 + x % 10;
            x = x / 10;
            if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
                return 0;
            }

        }

        if (fu == true) {
            return (int) (-1 * res);
        } else {
            return (int) res;
        }
    }
}
