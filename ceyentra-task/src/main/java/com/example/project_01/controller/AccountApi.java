package com.example.project_01.controller;

import com.example.project_01.dto.*;
import com.example.project_01.ex.AccountException;
import com.example.project_01.ex.UserNotFoundException;
import com.example.project_01.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//This endpoint allows basic crud operation for account

//This endpoint  can access to User only



@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountApi {

    AccountService accountService;

    public AccountApi(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity getAccount(){
        return ResponseEntity.ok( accountService.allAccount());
    }

    @PostMapping
    public ResponseEntity addAccount(@RequestBody AccountDTO dto ){
            try {
                System.out.println("awa "+dto);

                AccountDTO accountDTO = accountService.addAccount(dto);
                return ResponseEntity.ok(accountDTO);
            }catch (AccountException e){
                e.printStackTrace();

                return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
            } catch (UserNotFoundException e) {
                return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
            }


    }

    @PutMapping

    public ResponseEntity updateAccount(@RequestBody AccountDTO dto ){
        try {


            AccountDTO accountDTO = accountService.updateAccount(dto);
            return ResponseEntity.ok(accountDTO);
        }catch (AccountException e){
            e.printStackTrace();

            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }


    }
    @GetMapping(path = {"/accntbyid"})
    public ResponseEntity userByname(@RequestParam("id") int id)  {

        try {
            return ResponseEntity.ok( accountService.findByID(id));
        }catch (AccountException e){
            e.printStackTrace();

            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }
    }



    @DeleteMapping
    public ResponseEntity deleteUser(@RequestParam("id") int id){
        try {
            System.out.println("awa "+id);

            AccountDTO accountDTO = accountService.deleteAccount(id);
            return ResponseEntity.ok(accountDTO);
        }catch (AccountException e){
            e.printStackTrace();

            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }

    }

}
