package com.example.project_01.service;

import com.example.project_01.dto.TaskDTO;
import com.example.project_01.entity.enums.Status;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getAllTasks();
    List<TaskDTO> getFilterdTasks(Status status);
    TaskDTO createTask(TaskDTO task);
    TaskDTO updateTask(Long id, TaskDTO taskDetails);
    void deleteTask(Long id);
}
