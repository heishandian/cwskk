package com.yaoli.ajob;

/**
 * Created by will on 2017/3/22.
 */
public class _151_ReverseWordsinaString {
    public static void main(String[] args) {
        _151_ReverseWordsinaString test = new _151_ReverseWordsinaString();
        System.out.println(test.reverseWords("   a   b "));
    }

    public String reverseWords(String s) {
        s = s.trim();
        char [] sets = s.toCharArray();
        for (int i = 0; i < sets.length / 2; i++) {
            char temp = sets[i];
            sets[i] = sets[sets.length - i - 1];
            sets[sets.length - i - 1] = temp;
        }

        int begin = -1;
        int end = -1;
        for(int i = 0 ; i < sets.length ; i++){

            if(!Character.isLetter(sets[i])){
                if(begin != -1 && end != -1){
                    //翻转
                    reverse(sets,begin,end);
                    begin = -1;
                    end = -1;
                }

                continue;
            }

            if(begin == -1 && Character.isLetter(sets[i])){
                begin = i;
            }
            else if(begin != -1 && Character.isLetter(sets[i])){
                end = i;
            }

        }

        if(begin != -1 && end != -1){
            //翻转
            reverse(sets,begin,end);
        }

        return String.valueOf(sets);
    }

    public void reverse(char sets[] , int begin ,int end){
        for(int i = begin,j = 0 ; i <= (begin + end) / 2 ; i++, j++){
            char temp = sets[i];
            sets[i] = sets[end - j];
            sets[end - j] = temp;
        }
    }
}
