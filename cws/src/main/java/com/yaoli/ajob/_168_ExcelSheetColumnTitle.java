package com.yaoli.ajob;

/**
 * Created by will on 2017/2/22.
 */
public class _168_ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        //65 90 此题考查 进制转换
        String res = "";
        while(n >= 0){
            n = n - 1;
            int temp = n % 26;
            res = String.valueOf((char)(temp+65)) + res;
            n = n / 26;
            if(n == 0){
                break;
            }
        }

        return res;
    }
}
