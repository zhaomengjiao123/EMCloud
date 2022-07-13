package com.server.emcloud.domain;

/**
 *  任务接口类
 * @Author: lyx
 * @Date: 2022/7/12
 */

public class Task {
    private Integer task_id;
    private Integer equipment_id;
    private String task_start_time;
    private String task_end_time;

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getTask_start_time() {
        return task_start_time;
    }

    public void setTask_start_time(String task_start_time) {
        this.task_start_time = task_start_time;
    }

    public String getTask_end_time() {
        return task_end_time;
    }

    public void setTask_end_time(String task_end_time) {
        this.task_end_time = task_end_time;
    }
}
