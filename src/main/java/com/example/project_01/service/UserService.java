package com.example.project_01.service;

import com.example.project_01.dto.UserDTO;
import com.example.project_01.ex.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
     String search(String email, String password);
     UserDTO addUser(UserDTO userDTO) throws UserNotFoundException;
     UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException;

     UserDTO deleteUser(int id) throws UserNotFoundException;
     List<UserDTO> getAll() throws UserNotFoundException;
      List<UserDTO> findbyUsername(String name) throws UserNotFoundException;

}
