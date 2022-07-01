package com.server.emcloud.vo;

import java.util.List;

/**
 * author：王俊博
 * 查询一段之间内不同类型异常数量（月，天）
 * 此类和AllExceptionAndTimeVO公用一个mapper，dao，service，controller
 */
public class AllExceptionTypeAndTimeVO {
    String name;//类型名称
    List<AllExceptionAndTimeVO> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AllExceptionAndTimeVO> getList() {
        return list;
    }

    public void setList(List<AllExceptionAndTimeVO> list) {
        this.list = list;
    }
}
