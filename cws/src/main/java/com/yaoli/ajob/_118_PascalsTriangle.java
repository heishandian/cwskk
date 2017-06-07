package com.yaoli.ajob;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by will on 2017/2/7.
 */
    public class _118_PascalsTriangle {
    public static void main(String[] args) {
        generate(5);
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();

        List<Integer> list = new ArrayList<Integer>();

        list.add(1);

        lists.add(list);

        for(int i = 1 ; i < numRows; i ++){
            list = new ArrayList<Integer>();
            for(int j = 0; j <= i; j ++){
                if(j == 0){
                    list.add(1);
                }else if(j == numRows - 1){
                    list.add(1);
                }else{
                    list.add(lists.get(i - 1).get(j - 1)+ lists.get(i - 1).get(j));
                }
            }

            lists.add(list);
        }

        return lists;

    }
}
