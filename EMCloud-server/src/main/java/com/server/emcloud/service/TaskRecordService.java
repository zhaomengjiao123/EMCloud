package com.server.emcloud.service;

import com.server.emcloud.domain.TaskRecord;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lyx
 * @Date: 2022/07/12
 * @Description:
 */
public interface TaskRecordService {
    public List<TaskRecord> getTask();

    public int addTaskRecord(TaskRecord taskRecord);

    public List<TaskRecord> getTaskById(String taskID);

    public List<TaskRecord> getTaskByComapnyId(String company_id);
}
