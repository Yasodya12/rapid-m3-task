package com.example.project_01.repo;

import com.example.project_01.entity.Account;
import com.example.project_01.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {

}
