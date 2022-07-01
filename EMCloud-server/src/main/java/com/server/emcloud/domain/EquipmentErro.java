package com.server.emcloud.domain;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/01/9:22
 * @Description:
 */
public class EquipmentErro {

    private Integer erro_id;
    private Integer equipment_id;
    private String erro_content;
    private String erro_time;

    public Integer getErro_id() {
        return erro_id;
    }

    public void setErro_id(Integer erro_id) {
        this.erro_id = erro_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getErro_content() {
        return erro_content;
    }

    public void setErro_content(String erro_content) {
        this.erro_content = erro_content;
    }

    public String getErro_time() {
        return erro_time;
    }

    public void setErro_time(String erro_time) {
        this.erro_time = erro_time;
    }
}
