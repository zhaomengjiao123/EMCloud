package com.server.emcloud.controller;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 王俊博
 * @Date: 2022/06/23
 * @Description: salesman表
 */

import com.server.emcloud.domain.Salesman;
import com.server.emcloud.service.EquipmentService;
import com.server.emcloud.service.SalesmanService;
import com.server.emcloud.utils.Consts;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesman")
public class SalesmanController {

    @Autowired
    private SalesmanService salesmanService;


    //获取所有的salesman的id和name
    @GetMapping("/getAll")
    public List<Salesman> getAll(){
        return salesmanService.getAll();
    }

    //添加salesman
    @PostMapping("/addSalesman")
    public Object addSalesman(@RequestBody Salesman salesman) {
        JSONObject jsonObject = new JSONObject();
        try {
            int res = salesmanService.addSalesman(salesman);
            if (res == 1) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "添加成功");
                return jsonObject;
            } else {
                throw new RuntimeException();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "添加失败");
            return jsonObject;
        }


    }
    //更新和删除功能暂不开发，需要再开发

}
