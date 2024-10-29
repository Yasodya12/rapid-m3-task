package com.example.project_01.controller;


import com.example.project_01.dto.ErrorRes;
import com.example.project_01.dto.WithdrawMoneyDTO;
import com.example.project_01.ex.AccountException;
import com.example.project_01.ex.InsufficientBalanceExeption;
import com.example.project_01.service.WithdrawMoneyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/withdraw")
@CrossOrigin
public class WithdrawMoneyApi {


    WithdrawMoneyService withdrawMoneyService;

    public WithdrawMoneyApi(WithdrawMoneyService withdrawMoneyService) {
        this.withdrawMoneyService = withdrawMoneyService;
    }

    @PostMapping
    public ResponseEntity addAccount(@RequestBody WithdrawMoneyDTO dto )  {

        try {
            WithdrawMoneyDTO withdrawMoneyDTO = withdrawMoneyService.withdrawMoney(dto);

            return ResponseEntity.ok(withdrawMoneyDTO);
        }catch (InsufficientBalanceExeption e){

            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        } catch (AccountException e) {
            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }

    }

    @GetMapping(path = "/getByAct")
    public ResponseEntity depositeByActId( @RequestParam("id") int id)  {


        try {
            return ResponseEntity.ok( withdrawMoneyService.getListByActId(id));
        } catch (AccountException e) {
            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }

    }

    @GetMapping(path = "/getByUser")
    public ResponseEntity depositeByUserId( @RequestParam("id") int id)  {

        try {
            return ResponseEntity.ok( withdrawMoneyService.getListByUserId(id));
        } catch (AccountException e) {
            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }
    }


}
