package com.example.project_01.repo;

import com.example.project_01.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
     User findAdminByUsername(String username);
     User findUserByEmailAndPassword(String email, String password);
     User findByEmail(String email);
    List<User> findByUsernameLike(String pattern);

}
