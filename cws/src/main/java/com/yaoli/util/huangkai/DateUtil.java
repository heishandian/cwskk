package com.yaoli.util.huangkai;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by kk on 2017/7/7.
 */
public class DateUtil {
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
