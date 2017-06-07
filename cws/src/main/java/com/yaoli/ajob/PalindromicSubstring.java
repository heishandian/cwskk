package com.yaoli.ajob;

/**
 * Created by will on 2017/1/3.
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class PalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longest(""));
    }

    public static String longest(String s){
        String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        StringBuilder builder = new StringBuilder();

        int maxLength = 0;
        int maxPosition = 0;

        boolean flag = true;

        int realLength = 0;

        for(int i = 0 ; i < str.length(); i++){

            //奇数对称
            int middle = i;

            int j = middle - 1;

            for(; j >= 0; j--){
                int left = j;
                int right = middle+(middle - j);

                if(right >= str.length() || str.charAt(left) != str.charAt(right)){
                    break;
                }
            }
            if( (middle - (j + 1))*2 + 1 > realLength){
                maxPosition = middle;
                maxLength = middle - (j + 1);
                realLength =  (middle - (j + 1))*2 + 1;
                flag = true;
            }

            //偶数对称
            int current = middle;

            int symmetry = current - 1;

            for(; current < str.length(); current++,symmetry --){

                if( symmetry < 0 || str.charAt(current) != str.charAt(symmetry) ){
                    break;
                }
            }

            if( (current - 1 - symmetry)  > realLength){
                maxPosition = middle;
                maxLength = (current - 1 - symmetry) / 2;
                realLength = (current - 1 - symmetry);
                flag = false;
            }
        }


        //输出结果
        if(flag == true){
            int begin = maxPosition - maxLength;
            int end = maxPosition + maxLength;

            for(int a = begin ; a <= end; a ++){
                builder.append(str.charAt(a));
            }

        }else{
            int begin = maxPosition - maxLength;
            int end = maxPosition + maxLength;

            for(int a = begin ; a < end; a ++){
                builder.append(str.charAt(a));
            }
        }

        return builder.toString();
    }

}
