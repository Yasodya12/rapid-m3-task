package com.example.project_01.repo;

import com.example.project_01.entity.Task;
import com.example.project_01.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);

}
