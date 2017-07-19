package com.yaoli.vo.huangkai;
/**
 * Created by kk on 2017/7/6.
 */
public class CarKMChangeDiaramSearchConditon {
    String carnumber;
    String time;

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CarKMChangeDiaramSearchConditon{" +
                "carnumber='" + carnumber + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
