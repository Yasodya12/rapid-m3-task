package com.example.project_01.controller;

import com.example.project_01.dto.ErrorRes;
import com.example.project_01.dto.LoanSettleDTO;
import com.example.project_01.ex.LoanSettleExeption;
import com.example.project_01.service.SettleLoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//This is the endpoint that we settle the loan

//This endpoint  can access to User only




@RestController
@RequestMapping("/pay")
@CrossOrigin
public class PayApi {

    SettleLoanService settleLoanService;

    public PayApi(SettleLoanService settleLoanService) {
        this.settleLoanService = settleLoanService;
    }

    @GetMapping
    public String getAccount(){
        System.out.println("get pay call");
        return "get pay";
    }

    @PostMapping
    public ResponseEntity payLoan(@RequestBody LoanSettleDTO loanSettleDTO){


        try {
           LoanSettleDTO loanSettleDTO1 = settleLoanService.payLoan(loanSettleDTO);
            return ResponseEntity.ok(loanSettleDTO1);
        } catch (LoanSettleExeption e) {
            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }

    }

    @GetMapping(path = "/actID")
    public ResponseEntity getPayLoanByAct(@RequestParam("id") int id ){

        try {
            List<LoanSettleDTO> loanSettleDTOS = settleLoanService.payLoanByActId(id);
            return ResponseEntity.ok(loanSettleDTOS);
        } catch (LoanSettleExeption e) {

            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }


    }

    @GetMapping("/userID")
    public ResponseEntity getPayLoanByUserId(@RequestParam("id") int id ){

        try {
            List<LoanSettleDTO> loanSettleDTOS = settleLoanService.payLoanByUserId(id);
            return ResponseEntity.ok(loanSettleDTOS);
        } catch (LoanSettleExeption e) {
            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }


    }

    @GetMapping("/payID")
    public ResponseEntity searchByPayId(@RequestParam("id") String  id ){

        try {
            LoanSettleDTO  loanSettleDTO = settleLoanService.searchByPayId(id);
            return ResponseEntity.ok(loanSettleDTO);
        } catch (LoanSettleExeption e) {
            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));

        }


    }
}
