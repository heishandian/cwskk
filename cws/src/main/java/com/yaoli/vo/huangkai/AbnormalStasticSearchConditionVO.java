package com.yaoli.vo.huangkai;

/**
 * Created by kk on 2017/6/26.
 */
public class AbnormalStasticSearchConditionVO {
    String time;
    String flag;
    String origin;
    String page;
    String rows;

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {

        return origin;
    }

    public String getTime() {
        return time;
    }

    public String getFlag() {
        return flag;
    }

    public String getPage() {
        return page;
    }

    public String getRows() {
        return rows;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public AbnormalStasticSearchConditionVO(String time, String flag, String origin, String page, String rows) {
        this.time = time;
        this.flag = flag;
        this.origin = origin;
        this.page = page;
        this.rows = rows;
    }

    public AbnormalStasticSearchConditionVO() {
    }

    @Override
    public String toString() {
        return "AbnormalStasticSearchConditionVO{" +
                "time='" + time + '\'' +
                ", flag='" + flag + '\'' +
                ", origin='" + origin + '\'' +
                ", page='" + page + '\'' +
                ", rows='" + rows + '\'' +
                '}';
    }
}
