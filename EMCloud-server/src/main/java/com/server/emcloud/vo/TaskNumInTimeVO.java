package com.server.emcloud.vo;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: mcj
 * @Date: 2022/7/1
 * @Description: 用于查询某段时间内的任务数量的返回
 * 此类和DiffProductTypeAndTaskNumInTimeVO共用一个mapper，dao，service，controller
 */
public class TaskNumInTimeVO {
    //时间（可能是某天如2022/6/6或者某月如2022/6）
    String time;
    //任务数量
    Integer Count;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }
}
