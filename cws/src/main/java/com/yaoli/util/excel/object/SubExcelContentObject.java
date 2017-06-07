package com.yaoli.util.excel.object;

import java.util.ArrayList;

/**
 * Created by will on 2016/11/26.
 */
public class SubExcelContentObject extends ExcelContentObject{

    /**
     * 子标题
     */
    public ArrayList<String> subTitles;

    /**
     * 子标题对应的数据列
     * 比如，设备运行报表中 最外面的ArrayList表示曝气机、污水泵等
     * 中间的ArrayList表示运行时间 和 故障时间
     * 里面的ArrayList表示每一列数据
     */
    public ArrayList<ArrayList<ArrayList<String>>> subData;

    public ArrayList<String> getSubTitles() {
        return subTitles;
    }

    public void setSubTitles(ArrayList<String> subTitles) {
        this.subTitles = subTitles;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getSubData() {
        return subData;
    }

    public void setSubData(ArrayList<ArrayList<ArrayList<String>>> subData) {
        this.subData = subData;
    }
}
