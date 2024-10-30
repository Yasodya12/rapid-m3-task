package com.example.project_01.service.impl;

import com.example.project_01.dto.TaskDTO;
import com.example.project_01.entity.Task;
import com.example.project_01.entity.enums.Status;
import com.example.project_01.repo.TaskRepo;
import com.example.project_01.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<TaskDTO> getAllTasks() {
        return toDTOList(taskRepo.findAll());
    }

    @Override
    public List<TaskDTO> getFilterdTasks(Status status) {
        List<Task> byStatus = taskRepo.findByStatus(status);
       return toDTOList(byStatus);

    }


    public TaskDTO createTask(TaskDTO taskDTO) {
        return toDTO(taskRepo.save(toEntity(taskDTO)));
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDetails) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());

        return toDTO(taskRepo.save(task));
    }

    public void deleteTask(Long id) {
        if (!taskRepo.existsById(id)) {
            throw new IllegalArgumentException("Task not found");
        }
        taskRepo.deleteById(id);
    }

    // Converts TaskDTO to Task entity
    public Task toEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        return task;
    }
    // Converts Task entity to TaskDTO
    private TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
    }
    // Converts a list of Task entities to a list of TaskDTOs
    private List<TaskDTO> toDTOList(List<Task> tasks) {
        return tasks.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
