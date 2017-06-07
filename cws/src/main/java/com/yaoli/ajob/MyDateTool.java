package com.yaoli.ajob;

import org.joda.time.DateTime;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by will on 2017/1/1.
 */
public class MyDateTool {

    private static int initYear = 1900;

    private static Map<Month,Integer> monthDays = new LinkedHashMap<Month, Integer>();

    private static Map<Month,Integer> monthToInteger = new LinkedHashMap<Month, Integer>();

    private static Map<Integer,Month> integerToMonth = new LinkedHashMap<Integer,Month>();

    static {
        monthDays.put(Month.January,31);
        monthDays.put(Month.February,28);
        monthDays.put(Month.March,31);
        monthDays.put(Month.April,30);
        monthDays.put(Month.May,31);
        monthDays.put(Month.June,30);
        monthDays.put(Month.July,31);
        monthDays.put(Month.August,31);
        monthDays.put(Month.September,30);
        monthDays.put(Month.October,31);
        monthDays.put(Month.November,30);
        monthDays.put(Month.December,31);


        monthToInteger.put(Month.January,1);
        monthToInteger.put(Month.February,2);
        monthToInteger.put(Month.March,3);
        monthToInteger.put(Month.April,4);
        monthToInteger.put(Month.May,5);
        monthToInteger.put(Month.June,6);
        monthToInteger.put(Month.July,7);
        monthToInteger.put(Month.August,8);
        monthToInteger.put(Month.September,9);
        monthToInteger.put(Month.October,10);
        monthToInteger.put(Month.November,11);
        monthToInteger.put(Month.December,12);

        integerToMonth.put(1,Month.January);
        integerToMonth.put(2,Month.February);
        integerToMonth.put(3,Month.March);
        integerToMonth.put(4,Month.April);
        integerToMonth.put(5,Month.May);
        integerToMonth.put(6,Month.June);
        integerToMonth.put(7,Month.July);
        integerToMonth.put(8,Month.August);
        integerToMonth.put(9,Month.September);
        integerToMonth.put(10,Month.October);
        integerToMonth.put(11,Month.November);
        integerToMonth.put(12,Month.December);
    }

    enum Month{
        January, February,March,April, May, June, July, August, September, October, November, December
    }

    enum Week{
        MON, TUE, WED, THU, FRI, SAT, SUN
    }

    public static void main(String[] args) throws Exception {

        for(int i = 1900; i < 2068;i++){

            int days = calcBetweenDays(i,2,21);

            DateTime date = new DateTime(i+"-"+2+"-"+21);
            System.out.print(date.toString("yyyy-MM-dd")+"\t");
            System.out.print(i+"\t"+days+"\t\t"+(days%7+1)+"\t");
            System.out.print(date.getDayOfWeek() == (days%7+1));
            System.out.println();
        }




    }

    /**
     *  1900年01月01号是星期一
     */




    /**
     * 计算传入的日期与1900年01月01日 相差多少天
     * @param year,month,day,
     * 三者全是合法数据
     * @return
     */
    public static int calcBetweenDays(int year,int month,int day) throws Exception {
        int differece = year - initYear;

        if(differece < 0){
            throw new Exception("不支持1990年之前日期查询");
        }

        //计算 去年年底到 1900年1月1号 有多少天 + 两年之间有多少闰年
        int lastYearDays = differece * 365 + calcGapHowManyRunYear(year);

        //计算 日期到 当年年初 的天数
        int days = lastYearDays + calcDateToFirstDay(year,month,day);

        return days;
    }


    /**
     * 计算 距离 当年1月1日有多少天
     * @param day
     * @return
     */
    private static int calcDateToFirstDay(int year,int month,int day){
        boolean isRunYear = isRunYear(year);

        int totalDays = 0;

        totalDays = calcMonthToFirstDay(month);

        //并且 月份 大于2
        if((isRunYear == true) && month > 2){

            totalDays = totalDays + 1;

        }

        //去掉当天
        totalDays = totalDays + day - 1;

        return totalDays;
    }

    private static int calcMonthToFirstDay(int month){
        int totalDays = 0;

        Month temp = integerToMonth.get(month);

        Set<Map.Entry<Month, Integer>> set = monthDays.entrySet();

        for (Map.Entry<Month, Integer> me : set) {

            Month key = me.getKey();

            if(key == temp){
                break;
            }

            Integer days = me.getValue();

            totalDays = days + totalDays;
        }

        return totalDays;
    }

    /**
     * 计算 两个年份 间隔多少个闰年
     * @param year
     * @return
     */
    private static int calcGapHowManyRunYear(int year){
        int difference = year - initYear;
        int temp = (difference - 1 )/4;
        if(temp >= 0){
            return temp;
        }else{
            return 0;
        }
    }

    /**
     * 判断是否是闰年
     * @return
     */
    private static boolean isRunYear(int year){
        if((year%4==0&&year%100!=0)||year%400==0){
            return true;
        }else {
            return false;
        }
    }
}
