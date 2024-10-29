package com.example.project_01.repo;

import com.example.project_01.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



public interface AccountRepo extends CrudRepository<Account, Integer> {
}
