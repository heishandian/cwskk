package com.yaoli.ajob;

/**
 * Created by will on 2017/3/3.
 */
public class _258_AddDigits {
    public static void main(String[] args) {
        System.out.println(addDigits(1));
    }

    public static int addDigits(int num) {
        if (num == 0){
            return 0;
        }

        boolean flag = true;

        int res = 0;

        while(flag == true){

            while(flag == true){

                int yu = num % 10;

                res = res + yu;

                num = num / 10;

                if(num == 0){
                    num = res; // 再次循
                    break;
                }

            }

            if(res / 10 == 0){
                break;
            }

            res = 0;

        }


        return res;

    }
}
