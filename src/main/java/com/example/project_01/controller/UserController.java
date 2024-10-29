package com.example.project_01.controller;

import com.example.project_01.dto.ErrorRes;
import com.example.project_01.dto.UserDTO;
import com.example.project_01.ex.UserNotFoundException;
import com.example.project_01.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> allUser() throws UserNotFoundException {

        return ResponseEntity.ok( userService.getAll());
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDTO dto ){
        try {
            System.out.println("awa "+dto);

            UserDTO userDTO = userService.addUser(dto);
            return ResponseEntity.ok(userDTO);
        }catch (UserNotFoundException e){
            e.printStackTrace();

            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }

    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserDTO dto ){
        try {
            System.out.println("awa "+dto);

            UserDTO userDTO = userService.updateUser(dto);
            return ResponseEntity.ok(userDTO);
        }catch (UserNotFoundException e){
            e.printStackTrace();

            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }

    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestParam("id") int id){
        try {
            System.out.println("awa "+id);

            UserDTO userDTO = userService.deleteUser(id);
            return ResponseEntity.ok(userDTO);
        }catch (UserNotFoundException e){
            e.printStackTrace();

            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }

    }
    @GetMapping(path = {"/userbyname"})
    public ResponseEntity<List<UserDTO>> userByname(@RequestParam("name") String name) throws UserNotFoundException {
        System.out.println(name);
        return ResponseEntity.ok( userService.findbyUsername(name));
    }
}
