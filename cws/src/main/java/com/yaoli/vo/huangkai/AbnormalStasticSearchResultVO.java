package com.yaoli.vo.huangkai;

/**
 * Created by kk on 2017/6/26.
 */
public class AbnormalStasticSearchResultVO {
    String name;
    String status;
    Integer total;

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Integer getTotal() {
        return total;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "AbnormalStasticSearchResultVO{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", total=" + total +
                '}';
    }
}
