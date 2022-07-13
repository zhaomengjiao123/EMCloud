package com.server.emcloud.domain;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/13/18:10
 * @Description:
 */
public class EquipmentState {

    private int state_id;
    private int equipment_id;
    private String state_update_time;

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public int getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(int equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getState_update_time() {
        return state_update_time;
    }

    public void setState_update_time(String state_update_time) {
        this.state_update_time = state_update_time;
    }
}

