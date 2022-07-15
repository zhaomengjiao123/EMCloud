package com.server.emcloud.controller;
import com.server.emcloud.domain.TaskRecord;
import com.server.emcloud.service.TaskRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lyx
 * @Date: 2022/07/12
 * @Description:
 */
@RestController
public class TaskRecordController {
    @Autowired
    private TaskRecordService taskRecordService;
   // @RequestMapping(value = "/addtask",method = RequestMethod.POST)
    public int addTaskRecord(TaskRecord taskRecord){
        return taskRecordService.addTaskRecord(taskRecord);
    }

    /**
     * @Description: GET法
     *
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: lyx
     * @Date: 2022/7/14
     */
    @RequestMapping(value = "getTask", method = RequestMethod.GET)
    public Object getTask(){
        return taskRecordService.getTask();

    }

    @RequestMapping(value = "getTaskbyId", method = RequestMethod.GET)
    public Object getTaskById(HttpServletRequest req){
        String taskID = req.getParameter("taskID");
        return taskRecordService.getTaskById(taskID);
    }

    @RequestMapping(value = "getTaskbyCompanyId", method = RequestMethod.GET)
    public Object getTaskByCompanyId(HttpServletRequest req){
        String company_id = req.getParameter("company_id");
        return taskRecordService.getTaskByComapnyId(company_id);
    }

    @RequestMapping(value = "getTaskbyEquipId", method = RequestMethod.GET)
    public Object getTaskByEquipId(HttpServletRequest req){
        String company_id = req.getParameter("company_id");
        String equipment_number=req.getParameter("equipment_number");
        return taskRecordService.getTaskByEId(company_id,equipment_number);
    }

}
