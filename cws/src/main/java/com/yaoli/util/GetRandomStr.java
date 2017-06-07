package com.yaoli.util;

import java.util.Random;

/**
 * Created by will on 2016/11/18.
 */
public class GetRandomStr {
    public static int length = 20;

    private static String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String getRandomStr(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length ; i++){
            sb.append(str.charAt(random.nextInt(str.length()-1)));
        }
        return sb.toString();
    }
}
