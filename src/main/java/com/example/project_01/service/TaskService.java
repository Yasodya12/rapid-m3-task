package com.example.project_01.service;

import com.example.project_01.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getAllTasks();

    TaskDTO createTask(TaskDTO task);
    TaskDTO updateTask(Long id, TaskDTO taskDetails);
    void deleteTask(Long id);
}
