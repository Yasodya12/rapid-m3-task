package com.example.project_01.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//This is the endpoint that we call when customer is deporting or withdrawing money

//This endpoint  can access to User only




@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionApi {
    @GetMapping
    public String getAccount(){
        System.out.println("get Transaction call");
        return "get Transaction";
    }
}
