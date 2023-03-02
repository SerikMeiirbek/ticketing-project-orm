package com.cydeo.service;

import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;

import java.util.List;

public interface TaskService {
    List<TaskDTO> listAllTasks();
    void save(TaskDTO taskDTO);
    void update(TaskDTO taskDTO);
    TaskDTO findById(Long id);
    void delete(Long id);

    int totalNonCompletedTask(String projectCode);

    int totalCompletedTask(String projectCode);
}
