package com.server.emcloud.service.impl;
import com.server.emcloud.dao.TaskMapper;
import com.server.emcloud.domain.Task;
import com.server.emcloud.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public int addTask(Task task) {
        return taskMapper.addTask(task);
    }
}