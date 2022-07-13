package com.server.emcloud.domain;
/**
 *  任务接口类
 * @Author: lyx
 * @Date: 2022/7/12
 */

public class TaskRecord {
    private String TaskID;
    private String RunModel;
    private String RunModelName;
    private String BeginTime;
    private String EndTime;
    private float TotalMinute;
    private String AGVID;
    private float costbattery;
    private float beginbattery;
    private float endbattery;

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String taskID) {
        TaskID = taskID;
    }

    public String getRunModel() {
        return RunModel;
    }

    public void setRunModel(String runModel) {
        RunModel = runModel;
    }

    public String getRunModelName() {
        return RunModelName;
    }

    public void setRunModelName(String runModelName) {
        RunModelName = runModelName;
    }

    public String getBeginTime() {
        return BeginTime;
    }

    public void setBeginTime(String beginTime) {
        BeginTime = beginTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public float getTotalMinute() {
        return TotalMinute;
    }

    public void setTotalMinute(float totalMinute) {
        TotalMinute = totalMinute;
    }

    public String getAGVID() {
        return AGVID;
    }

    public void setAGVID(String AGVID) {
        this.AGVID = AGVID;
    }

    public float getCostbattery() {
        return costbattery;
    }

    public void setCostbattery(float costbattery) {
        this.costbattery = costbattery;
    }

    public float getBeginbattery() {
        return beginbattery;
    }

    public void setBeginbattery(float beginbattery) {
        this.beginbattery = beginbattery;
    }

    public float getEndbattery() {
        return endbattery;
    }

    public void setEndbattery(float endbattery) {
        this.endbattery = endbattery;
    }
}
