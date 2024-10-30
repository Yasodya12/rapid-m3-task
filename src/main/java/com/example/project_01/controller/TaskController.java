package com.example.project_01.controller;

import com.example.project_01.dto.TaskDTO;
import com.example.project_01.entity.enums.Status;
import com.example.project_01.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        System.out.println("get function stared get");
        return taskService.getAllTasks();
    }

    @GetMapping("/{status}")
    public List<TaskDTO> getFilterdTasks(@PathVariable Status status) {
        System.out.println("gethhhh function stared"+status+" after");
        List<TaskDTO> filterdTasks = taskService.getFilterdTasks(status);
        System.out.println(filterdTasks);
        return filterdTasks;
    }


    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO task) {
        System.out.println(task);
        TaskDTO createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDetails) {
        TaskDTO updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
