package com.example.project_01.controller;


import com.example.project_01.config.JwtUtil;
import com.example.project_01.dto.UserDTO;
import com.example.project_01.dto.ErrorRes;
import com.example.project_01.dto.LoginReq;
import com.example.project_01.dto.LoginRes;
import com.example.project_01.ex.UserNotFoundException;
import com.example.project_01.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/log")
@CrossOrigin
public class LoginController {
    private final JwtUtil util;
    private final UserService service;
    public LoginController(UserService service, JwtUtil util){
        this.util=util;
        this.service = service;
        System.out.println("LoginController");
    }

    @GetMapping
    public ResponseEntity login(@RequestBody LoginReq req){
       System.out.println("LogIn controller funtion started");
       String token = service.search(req.getEmail(), req.getPassword());
       if(token!=null){
           return ResponseEntity.ok(new LoginRes(req.getEmail(),token));
       }else return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,"Invalid credentials"));
    }



}
