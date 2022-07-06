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
import com.server.emcloud.service.*;
import com.server.emcloud.utils.Consts;
import com.server.emcloud.vo.EquipmentCountOfCompanyVO;
import com.server.emcloud.vo.EquipmentNumAndCity;
import com.server.emcloud.vo.EquipmentStateVo;
import com.server.emcloud.vo.EquipmentVO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentWarningService equipmentWarningService;
    @Autowired
    private EquipmentErroService equipmentErroService;
    @Autowired
    private EquipmentEmergencyService equipmentEmergencyService;

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

    /**
     * @Description: 添加设备
     * @Param: [req]
     * @return: java.lang.Object
     * @Author: mcj
     * @Date: 2022/6/22
     */
    @RequestMapping(value = "/addEquipment", method = RequestMethod.POST)
    public Object addEquipment(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        //接收前端传来的表单参数
        Integer product_id = Integer.valueOf(req.getParameter("product_id").trim()); //设备所属产品id
        Integer company_id = Integer.valueOf(req.getParameter("company_id").trim()); //购买设备客户公司id
        String equipment_number = req.getParameter("equipment_number"); //设备编号
        Integer salesman_id = Integer.valueOf(req.getParameter("salesman_id").trim()); //销售员id
        String sell_time = req.getParameter("sell_time").trim(); //销售时间
        Integer equipment_state = Integer.valueOf(req.getParameter("equipment_state").trim()); //设备在线状态1在线0宕机
        String equipment_comment = req.getParameter("equipment_comment").trim(); //设备备注信息

        //设备预警数量，设备告警数量，设备紧急告警数量默认设置为0
        Integer equipment_warning_count = 0;
        Integer equipment_erro_count = 0;
        Integer equipment_emergency_warning_count = 0;

        System.out.println("添加设备："+product_id + "--" +equipment_number);

        //判空
        if(product_id == null || company_id == null || equipment_number.isEmpty()
                || salesman_id == null || sell_time.isEmpty() || equipment_state == null){
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "添加失败");
            return jsonObject;
        }

        //新建设备对象
        Equipment equipment = new Equipment();
        equipment.setProduct_id(product_id);
        equipment.setCompany_id(company_id);
        equipment.setEquipment_number(equipment_number);
        equipment.setSalesman_id(salesman_id);
        equipment.setSell_time(sell_time);
        equipment.setEquipment_state(equipment_state);
        equipment.setEquipment_comment(equipment_comment);
        equipment.setEquipment_warning_count(equipment_warning_count);
        equipment.setEquipment_erro_count(equipment_erro_count);
        equipment.setEquipment_emergency_warning_count(equipment_emergency_warning_count);

        //执行添加设备操作
        int res = equipmentService.addEquipment(equipment);

        if (res == 1){
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "添加失败");
            return jsonObject;
        }
    }

    /**
     * @Description: 删除设备
     * @Param: [req]
     * @return: java.lang.Object
     * @Author: mcj
     * @Date: 2022/6/23
     */
    @RequestMapping(value = "/deleteEquipment/detail", method = RequestMethod.GET)
    public Object deleteEquipment(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String equipment_id = req.getParameter("equipment_id");
        System.out.println("要删除的设备ID是"+equipment_id);

        int res = equipmentService.deleteEquipmentByEid(new Integer(equipment_id));

        if (res == 1){
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "删除成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "删除失败");
            return jsonObject;
        }
    }

    /**
     * author：王俊博
     * 查询每个城市的设备总数
     * 要求返回：
     * 城市名：城市中所有设备的数量
     */
    @RequestMapping(value = "/getAllEquipmentNumAndCity", method = RequestMethod.GET)
    public List<EquipmentNumAndCity> getAllEquipmentNumAndCity(){
        System.out.println("获取所有城市的设备数量");
        return equipmentService.getAllEquipmentNumAndCity();
    }

    /**
     * author：王俊博
     * 查询设备在线和不在线数量
     */
    @GetMapping("/getOnlineAndNotOnlineEquipmentNum")
    public List<EquipmentStateVo> getOnlineAndNotOnlineEquipmentNum(){
        List<EquipmentStateVo> res = new ArrayList<>();
        EquipmentStateVo vo1 = new EquipmentStateVo(1,equipmentService.getOnlineNum());

        EquipmentStateVo vo2 = new EquipmentStateVo(0,equipmentService.getNotOnlineNum());
        res.add(vo1);
        res.add(vo2);
        return res;
    }

    /**
     * author：王俊博
     * 查询每个公司的所有设备数
     */
    @GetMapping("/getEquipmentCountOfCompany")
    public List<EquipmentCountOfCompanyVO> getEquipmentCountOfCompany() {
        return equipmentService.getEquipmentCountOfCompany();
    }


    /**
    * @Description: 得到某公司的所有设备的预警数量
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/1
    */
    @RequestMapping(value = "/getWarningNumByCid",method = RequestMethod.GET)
    public Object getWarningNumByCid(HttpServletRequest request){
        String company_id = request.getParameter("company_id");
        System.out.println("请求得到某公司的所有设备的预警数量："+company_id);
        return equipmentService.getWarningNumByCid(new Integer(company_id));
    }

    /**
    * @Description: 得到某公司的所有设备的报警数量
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/1
    */
    @RequestMapping(value = "/getErroNumByCid",method = RequestMethod.GET)
    public Object getErroNumByCid(HttpServletRequest request){
        String company_id = request.getParameter("company_id");
        System.out.println("请求得到某公司的所有设备的报警数量："+company_id);
        return equipmentService.getErroNumByCid(new Integer(company_id));
    }

    /**
    * @Description: 得到某公司的所有设备的紧急告警数量
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/1
    */
    @RequestMapping(value = "/getEmergencyNumByCid",method = RequestMethod.GET)
    public Object getEmergencyNumByCid(HttpServletRequest request){
        String company_id = request.getParameter("company_id");
        System.out.println("请求得到某公司的所有设备的紧急告警数量："+company_id);
        return equipmentService.getEmergencyNumByCid(new Integer(company_id));
    }

    /**
    * @Description: 请求得到全部的设备数量
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/2
    */
    @RequestMapping(value = "/getAllEquipmentCount",method = RequestMethod.GET)
    public Object getAllEquipmentCount(HttpServletRequest request){
        System.out.println("请求得到全部的设备数量");
        return equipmentService.getAllEquipmentCount();
    }

    /**
    * @Description: 请求得到全部的异常数量
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/2
    */
    @RequestMapping(value = "/getAllExceptionCount",method = RequestMethod.GET)
    public Object getAllExceptionCount(HttpServletRequest request){
        int exceptionCount = equipmentEmergencyService.getAllEmergencyCount()+equipmentErroService.getAllErroCount()
                +equipmentWarningService.getAllWarningCount();
        JSONObject jsonObject = new JSONObject();

        System.out.println("请求得到全部的异常数量:"+exceptionCount);
        jsonObject.put("exceptionCount", exceptionCount);

        return jsonObject;
    }

    /**
    * @Description: 大屏设备轮播图信息 状态 ID 所属公司名 所在地
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/4
    */
    @RequestMapping(value = "/getBigScreenEquipmentInfo",method = RequestMethod.GET)
    public Object getBigScreenEquipmentInfo(HttpServletRequest request){
        System.out.println("请求得到大屏设备轮播图信息");
        return equipmentService.getBigScreenEquipmentInfo();
    }

    /**
    * @Description: 查询有异常的设备的数量
    * @Param: [request]
    * @return: java.lang.Object
    * @Author: zmj
    * @Date: 2022/7/5
    */
    @RequestMapping(value = "/getAbnormalEquipmentNum",method = RequestMethod.GET)
    public Object getAbnormalEquipmentNum(HttpServletRequest request){
        int abnormalEquipmentCount = equipmentService.getAbnormalEquipmentNum();
        JSONObject jsonObject = new JSONObject();
        System.out.println("请求得到有异常的设备的数量:"+abnormalEquipmentCount);
        jsonObject.put("abnormalEquipmentCount", abnormalEquipmentCount);
        return jsonObject;
    }

}
