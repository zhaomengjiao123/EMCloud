package com.server.emcloud.domain;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/0:36
 * @Description: 设备预警信息
 */
public class EquipmentWarning {

    private Integer warning_id;
    private Integer equipment_id;
    private String  warning_content;
    private String  warning_time;

    public Integer getWarning_id() {
        return warning_id;
    }

    public void setWarning_id(Integer warning_id) {
        this.warning_id = warning_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getWarning_content() {
        return warning_content;
    }

    public void setWarning_content(String warning_content) {
        this.warning_content = warning_content;
    }

    public String getWarning_time() {
        return warning_time;
    }

    public void setWarning_time(String warning_time) {
        this.warning_time = warning_time;
    }
}
