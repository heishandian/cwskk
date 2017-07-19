package com.yaoli.beans;

/**
 * Created by kk on 2017/6/23.
 */
public class GoodsAbnormalStatisticResult {
    Integer id;
    String name;
    Integer awaitdetectionamounts = 0;   //待检数量
    Integer processingamounts = 0;   //在处理数量
    Integer scrapedamounts = 0;  //报废数量
    Integer completedamounts = 0;    //维修完成数量

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAwaitdetectionamounts() {
        return awaitdetectionamounts;
    }

    public Integer getProcessingamounts() {
        return processingamounts;
    }

    public Integer getScrapedamounts() {
        return scrapedamounts;
    }

    public Integer getCompletedamounts() {
        return completedamounts;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAwaitdetectionamounts(Integer awaitdetectionamounts) {
        this.awaitdetectionamounts = awaitdetectionamounts;
    }

    public void setProcessingamounts(Integer processingamounts) {
        this.processingamounts = processingamounts;
    }

    public void setScrapedamounts(Integer scrapedamounts) {
        this.scrapedamounts = scrapedamounts;
    }

    public void setCompletedamounts(Integer completedamounts) {
        this.completedamounts = completedamounts;
    }

    @Override
    public String toString() {
        return "GoodsAbnormalStatisticResult{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", awaitdetectionamounts=" + awaitdetectionamounts +
                ", processingamounts=" + processingamounts +
                ", scrapedamounts=" + scrapedamounts +
                ", completedamounts=" + completedamounts +
                '}';
    }
}
