package com.server.emcloud.domain;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/10:05
 * @Description:
 */
public class EquipmentEmergency {
    private Integer emergency_warning_id;
    private Integer equipment_id;
    private String emergency_warning_content;
    private String emergency_warning_time;

    public Integer getEmergency_warning_id() {
        return emergency_warning_id;
    }

    public void setEmergency_warning_id(Integer emergency_warning_id) {
        this.emergency_warning_id = emergency_warning_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getEmergency_warning_content() {
        return emergency_warning_content;
    }

    public void setEmergency_warning_content(String emergency_warning_content) {
        this.emergency_warning_content = emergency_warning_content;
    }

    public String getEmergency_warning_time() {
        return emergency_warning_time;
    }

    public void setEmergency_warning_time(String emergency_warning_time) {
        this.emergency_warning_time = emergency_warning_time;
    }
}
