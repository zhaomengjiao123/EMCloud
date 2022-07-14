package com.server.emcloud.vo;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:  mcj
 * @Date: 2022/6/28
 * @Description: 用作查询不同类型产品的任务数量时的返回对象
 */
public class ProductTypeAndTaskNum {
    //产品类型
    private String product_type;
    //该产品类型对应的所有任务数量
    private Integer task_num;

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public Integer getTask_num() {
        return task_num;
    }

    public void setTask_num(Integer task_num) {
        this.task_num = task_num;
    }

    @Override
    public String toString() {
        return "ProductTypeAndTaskNum{" +
                "product_type='" + product_type + '\'' +
                ", task_num=" + task_num +
                '}';
    }
}
