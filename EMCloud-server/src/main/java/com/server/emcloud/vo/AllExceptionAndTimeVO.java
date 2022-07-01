package com.server.emcloud.vo;

/**
 * author：王俊博
 * 查询一段之间内所有异常数量（月，天）
 */
public class AllExceptionAndTimeVO {
    String time;
    Integer count;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
