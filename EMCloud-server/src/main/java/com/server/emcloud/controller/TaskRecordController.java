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


}
