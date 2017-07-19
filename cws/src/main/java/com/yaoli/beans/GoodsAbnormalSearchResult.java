package com.yaoli.beans;

/**
 * Created by kk on 2017/6/22.
 */
public class GoodsAbnormalSearchResult {
    String id;
    String name;
    String number;
    String origin;
    String abnormalType;
    String abnormalTime;
    String description;
    String abnormalAnalyse;
    String currentState;
    String currentStateTime;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getOrigin() {
        return origin;
    }

    public String getAbnormalType() {
        return abnormalType;
    }

    public String getAbnormalTime() {
        return abnormalTime;
    }

    public String getDescription() {
        return description;
    }

    public String getAbnormalAnalyse() {
        return abnormalAnalyse;
    }

    public String getCurrentState() {
        return currentState;
    }

    public String getCurrentStateTime() {
        return currentStateTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setAbnormalType(String abnormalType) {
        this.abnormalType = abnormalType;
    }

    public void setAbnormalTime(String abnormalTime) {
        this.abnormalTime = abnormalTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAbnormalAnalyse(String abnormalAnalyse) {
        this.abnormalAnalyse = abnormalAnalyse;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public void setCurrentStateTime(String currentStateTime) {
        this.currentStateTime = currentStateTime;
    }

    public GoodsAbnormalSearchResult(String id, String name, String number, String origin, String abnormalType, String abnormalTime, String description, String abnormalAnalyse, String currentState, String currentStateTime) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.origin = origin;
        this.abnormalType = abnormalType;
        this.abnormalTime = abnormalTime;
        this.description = description;
        this.abnormalAnalyse = abnormalAnalyse;
        this.currentState = currentState;
        this.currentStateTime = currentStateTime;
    }

    public GoodsAbnormalSearchResult() {
    }

    @Override
    public String toString() {
        return "GoodsAbnormalSearchResult{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", origin='" + origin + '\'' +
                ", abnormalType='" + abnormalType + '\'' +
                ", abnormalTime='" + abnormalTime + '\'' +
                ", description='" + description + '\'' +
                ", abnormalAnalyse='" + abnormalAnalyse + '\'' +
                ", currentState='" + currentState + '\'' +
                ", currentStateTime='" + currentStateTime + '\'' +
                '}';
    }
}
