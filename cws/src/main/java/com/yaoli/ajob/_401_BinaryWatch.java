package com.yaoli.ajob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by will on 2017/2/18.
 */
public class _401_BinaryWatch {


    public static void main(String[] args) {
        _401_BinaryWatch b = new _401_BinaryWatch();
        b.readBinaryWatch(1);
    }

    Set<Integer> hourSet = new HashSet<Integer>();

    Set<Integer> minuteSet = new HashSet<Integer>();

    /**
     *
     * @param num 表示开着灯的数量
     * @return
     */
    public List<String> readBinaryWatch(int num) {
        List<String> list = new ArrayList<String>();

        int hour = 0;
        int minute = 0;

        while(hour <= num){
            minute = num - hour;

            //使用hour能组成多少个数字 小于等于 11 的数字
            //转化成树的深度遍历，并将路径的值放在list中
            //长度为4的数组，含有2个1 的所有情况怎么遍历


            String hourStr = "";
            for(int i = 0 ; i < hour;i++){
                hourStr = hourStr + "1";
            }
            for(int i = 0; i < 4 - hour ; i++){
                hourStr = hourStr + "0";
            }

            String minuteStr = "";
            for(int i = 0 ; i < minute; i++){
                minuteStr = minuteStr + "1";
            }
            for(int i = 0 ; i < 6 - minute ; i ++){
                minuteStr = minuteStr + "0";
            }

            calcHour(hourStr.toCharArray(),0,4);

            calcMinute(minuteStr.toCharArray(),0,6);

            //使用minute能组成多少个 小于等于 59 的数字

            hour = hour + 1;

            for(Integer i:hourSet){
                for(Integer j : minuteSet){
                    String min = String.valueOf(j < 10?"0"+j:String.valueOf(j));
                    list.add(i + ":"+ min);
                }
            }

            hourSet.clear();
            minuteSet.clear();
        }


        return list;
    }

    public void calcHour(char [] str,int begin ,int length){
        if(begin == length){
            int hour = Integer.parseInt(String.valueOf(str),2);
            if(hour < 12){
                hourSet.add(hour);
            }
        }else{
            for(int i = begin ; i < length ; i++){
                swap(str,begin,i);
                calcHour(str,begin+1,length);
                swap(str,begin,i);
            }
        }
    }

    public void calcMinute(char [] str,int begin ,int length){
        if(begin == length){
            int minute = Integer.parseInt(String.valueOf(str),2);
            if(minute < 60){
                minuteSet.add(minute);
            }
        }else{
            for(int i = begin ; i < length ; i++){
                swap(str,begin,i);
                calcMinute(str,begin+1,length);
                swap(str,begin,i);
            }
        }
    }

    public void swap(char [] str,int i,int j){
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

}
