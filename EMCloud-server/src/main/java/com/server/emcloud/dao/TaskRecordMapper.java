package com.server.emcloud.dao;


import com.server.emcloud.domain.TaskRecord;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lyx
 * @Date: 2022/07/12
 * @Description:
 */
@Repository
public interface TaskRecordMapper {

    int addTaskRecord(TaskRecord taskRecord);

    List<TaskRecord> getTask();

    List<TaskRecord> getTaskById(String taskID);

    List<TaskRecord> getTaskByCompanyId(String company_id);
}
