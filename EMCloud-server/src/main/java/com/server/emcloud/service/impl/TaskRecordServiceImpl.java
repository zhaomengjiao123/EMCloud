package com.server.emcloud.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.server.emcloud.domain.TaskRecord;
import com.server.emcloud.service.TaskRecordService;
import com.server.emcloud.dao.TaskRecordMapper;

import java.util.List;

@Service
public class TaskRecordServiceImpl implements TaskRecordService {
    @Autowired
    private TaskRecordMapper taskRecordMapper;

    @Override
    public List<TaskRecord> getTask() {
        return taskRecordMapper.getTask();
    }

    @Override
    public int addTaskRecord(TaskRecord taskRecord) {
        return taskRecordMapper.addTaskRecord(taskRecord);
    }

    @Override
    public List<TaskRecord> getTaskById(String taskID) {
        return taskRecordMapper.getTaskById(taskID);
    }

    @Override
    public List<TaskRecord> getTaskByComapnyId(String company_id) {
        return taskRecordMapper.getTaskByCompanyId(company_id);
    }

    @Override
    public List<TaskRecord> getTaskByEId(String company_id, String equipment_number) {
        return taskRecordMapper.getTaskByEId(company_id,equipment_number);
    }
}