package com.yaoli.ajob;

/**
 * Created by will on 2017/2/22.
 */
public class _171_ExcelSheetColumnNumber {
    public static void main(String[] args) {
        System.out.println(titleToNumber("AA"));
    }
    public static int titleToNumber(String s) {
        char [] set = s.toCharArray();

        int jin = 1;

        int res = 0;
        for(int i = set.length - 1 ; i >= 0 ; i--){
            res =  ((int)set[i] - 65 + 1) *  jin + res;
            jin = jin * 26;
        }

        return res;
    }
}
