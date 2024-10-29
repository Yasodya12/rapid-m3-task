package com.example.project_01.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//This endpoint allows a customer to search his account details and their loan details

//This endpoint  can access to any user that authenticated


@RestController
@RequestMapping("/CustomerSearchFeatureApi")
@CrossOrigin
public class CustomerSearchFeatureApi {
    @GetMapping
    public String getAccount(){
        System.out.println("get Account call");
        return "get Account customer search";
    }
}
