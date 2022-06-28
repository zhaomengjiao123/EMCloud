package com.server.emcloud.vo;

/**
 * author：王俊博
 * 查询设备状态
 */
public class EquipmentStateVo {
    Integer equipstate;
    Integer count;
    public EquipmentStateVo() {

    }

    public EquipmentStateVo(Integer equipstate, Integer count) {
        this.equipstate = equipstate;
        this.count = count;
    }

    public Integer getEquipstate() {
        return equipstate;
    }

    public void setEquipstate(Integer equipstate) {
        this.equipstate = equipstate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
