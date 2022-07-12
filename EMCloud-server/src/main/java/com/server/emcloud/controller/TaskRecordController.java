package com.server.emcloud.controller;
import com.server.emcloud.domain.TaskRecord;
import com.server.emcloud.service.TaskRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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

    public int addTaskRecord(TaskRecord taskRecord){
        return taskRecordService.addTaskRecord(taskRecord);
    }


}
