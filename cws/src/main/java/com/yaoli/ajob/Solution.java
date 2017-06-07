package com.yaoli.ajob;

import java.math.BigDecimal;

public class Solution {
    public int[] plusOne(int[] digits) {
        String str = "";
        for(int i = 0; i < digits.length ; i++){
            str = str + digits[i];
        }
        BigDecimal a = new BigDecimal(str);
        str = a.add(new BigDecimal("1")).toString();

        int b []= new int[str.length()];

        for(int c = 0 ; c < str.length() ; c++){
            b[c] = Integer.valueOf(str.charAt(c));
        }

        return b;
    }
}
