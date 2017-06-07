package com.yaoli.ajob;


import java.util.HashSet;
import java.util.Set;

/**
 * Created by will on 2017/2/18.
 *
 * http://wuchong.me/blog/2014/07/28/permutation-and-combination-realize/
 *
 */
public class _Permutation {
    Set<Integer> set = new HashSet<Integer>();

    public static void main(String[] args) {
        _Permutation a = new _Permutation();
        a.permutation("1000".toCharArray(),0,4);

        System.out.println();
    }

    public void permutation(char [] str,int begin,int length){
        if(begin == length){
            set.add(Integer.parseInt(String.valueOf(str),2));
        }else{
            for(int i = begin ; i < length; i ++){
                swap(str,begin,i);
                permutation(str,begin+1,length);
                swap(str,begin,i);
            }
        }


    }

    public void swap(char [] str,int i , int j){
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;

    }

}
