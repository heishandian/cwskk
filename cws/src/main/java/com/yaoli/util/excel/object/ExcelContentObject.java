package com.yaoli.util.excel.object;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by will on 2016/11/26.
 */
public class ExcelContentObject {
    /**
     * 表格题目
     */
    public String header;


    /**
     * 第一列标题
     */
    public String mainTitle;

    /**
     * 从二列开始的标题，
     * 在ExcelContentObject表示标题
     * 在SubExcelContentObject表示的是父标题
     */
    public ArrayList<String> titles;


    /**
     * 第一列 mainTitle的数据
     */
    public ArrayList<String> mainData;

    /**
     * 第二列开始的数据,其data.size()表示含有多少列数据，data.get(i).siez().表示一列有多少行数据
     */
    public ArrayList<ArrayList<String>> data;


    /**
     * 制表人
     */
    public String author;


    /**
     * 制表时间
     */
    public DateTime makeTableTime;



    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public DateTime getMakeTableTime() {
        return makeTableTime;
    }

    public void setMakeTableTime(DateTime makeTableTime) {
        this.makeTableTime = makeTableTime;
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public ArrayList<String> getMainData() {
        return mainData;
    }

    public void setMainData(ArrayList<String> mainData) {
        this.mainData = mainData;
    }

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<String>> data) {
        this.data = data;
    }
}
