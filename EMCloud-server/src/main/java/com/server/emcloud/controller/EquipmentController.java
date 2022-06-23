package com.server.emcloud.controller;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 王俊博
 * @Date: 2022/06/23
 * @Description: 设备类
 */

import com.server.emcloud.domain.Company;
import com.server.emcloud.domain.Equipment;
import com.server.emcloud.service.CompanyService;
import com.server.emcloud.service.EquipmentService;
import com.server.emcloud.utils.Consts;
import com.server.emcloud.vo.EquipmentVO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    /**
     * 查询所有。由于前端需要展示的数据和数据库表不能对应，所以新建EquipmentVO对象表示前端需要的数据。
     * author：王俊博
     * @return
     */
    @GetMapping("getAll")
    public List<EquipmentVO>getAll(){
        return equipmentService.getAll();
    }


    /**
     * 修改功能
     * author：王俊博
     * @param equipment
     * @return
     */
    @PostMapping("update")
    public Object update(@RequestBody Equipment equipment){
        JSONObject jsonObject = new JSONObject();

        try {
            int res = equipmentService.update(equipment);
            if (res == 1) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "修改成功");
                return jsonObject;
            } else {
                throw new RuntimeException();
            }

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "修改失败");
            return jsonObject;
        }
    }

    /**
     * author：王俊博
     * 根据主键查询某条记录
     */
    @GetMapping("getByPrimaryKey")
    public Object getByPrimaryKey(Integer equipment_id){
        EquipmentVO equipmentVO = equipmentService.getByPrimaryKey(equipment_id);
        JSONObject jsonObject = new JSONObject();

        if (equipmentVO!=null){
           return equipmentVO;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "不存在此记录");
            return jsonObject;
        }
    }



}
