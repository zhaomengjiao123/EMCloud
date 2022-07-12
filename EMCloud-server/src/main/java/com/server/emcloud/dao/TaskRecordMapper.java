package com.server.emcloud.dao;


import com.server.emcloud.domain.TaskRecord;
import org.springframework.stereotype.Repository;


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
}
