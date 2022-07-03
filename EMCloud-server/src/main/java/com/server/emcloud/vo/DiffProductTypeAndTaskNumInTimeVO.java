package com.server.emcloud.vo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: mcj
 * @Date: 2022/7/1
 * @Description: 用于查询不同类型产品在一段时间内的任务数量的返回
 * 此类和TaskNumInTimeVO共用一个mapper，dao，service，controller
 */
public class DiffProductTypeAndTaskNumInTimeVO {
    //产品名称
    private String name;
    //该产品对应的一段时间内的任务数量
    private List<TaskNumInTimeVO> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskNumInTimeVO> getData() {
        return data;
    }

    public void setData(List<TaskNumInTimeVO> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DiffProductTypeAndTaskNumInTimeVO{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
