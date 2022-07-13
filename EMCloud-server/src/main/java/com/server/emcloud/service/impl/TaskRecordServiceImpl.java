package com.server.emcloud.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.server.emcloud.domain.TaskRecord;
import com.server.emcloud.service.TaskRecordService;
import com.server.emcloud.dao.TaskRecordMapper;

@Service
public class TaskRecordServiceImpl implements TaskRecordService {
    @Autowired
    private TaskRecordMapper taskRecordMapper;

    @Override
    public int addTaskRecord(TaskRecord taskRecord) {
        return taskRecordMapper.addTaskRecord(taskRecord);
    }
}