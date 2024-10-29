package com.example.project_01.controller;


import com.example.project_01.dto.DepositeMoneyDTO;
import com.example.project_01.dto.ErrorRes;
import com.example.project_01.ex.AccountException;
import com.example.project_01.service.DepositeMoneyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deposite")
@CrossOrigin
public class DepositeMoneyApi {



    DepositeMoneyService depositeMoneyService;


    public DepositeMoneyApi(DepositeMoneyService depositeMoneyService) {
        this.depositeMoneyService = depositeMoneyService;
    }

    @PostMapping
    public ResponseEntity depositeMoney(@RequestBody DepositeMoneyDTO dto ){
        try {
            DepositeMoneyDTO depositeMoneyDTO = depositeMoneyService.depositeMoney(dto);
            return ResponseEntity.ok(depositeMoneyDTO);
        }catch (AccountException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }


    }

    @GetMapping(path = "/getByAct")
    public ResponseEntity depositeByActId( @RequestParam("id") int id)  {

        try {
            return ResponseEntity.ok( depositeMoneyService.getListByActId(id));
        } catch (AccountException e) {
            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }
    }

    @GetMapping(path = "/getByUser")
    public ResponseEntity depositeByUserId( @RequestParam("id") int id)  {

        try {
            return ResponseEntity.ok( depositeMoneyService.getListByUserId(id));
        } catch (AccountException e) {

            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }
    }
}
