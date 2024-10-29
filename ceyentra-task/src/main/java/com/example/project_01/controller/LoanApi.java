package com.example.project_01.controller;

import com.example.project_01.dto.ErrorRes;
import com.example.project_01.dto.LoanDTO;
import com.example.project_01.ex.LoanExeption;
import com.example.project_01.service.LoanService;
import com.example.project_01.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//This endpoint allows basic crud operation for loan

//This endpoint  can access to User only

@RestController
@RequestMapping("/loan")
@CrossOrigin
public class LoanApi {
    LoanService loanService;

    public LoanApi(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public String getAccount(){
        System.out.println("get loan call");
        return "get loan";
    }

    @GetMapping(path = "/getByActId")
    public ResponseEntity getLoanBuAct(@RequestParam("id") int id){
        List<LoanDTO> loanDTOS = loanService.loanByAct(id);
        return ResponseEntity.ok(loanDTOS);
    }


    @GetMapping(path = "/getByUserId")
    public ResponseEntity getLoanByUser(@RequestParam("id") int id){

        try {
            List<LoanDTO> loanDTOS = loanService.loanByUser(id);
            return ResponseEntity.ok(loanDTOS);
        } catch (LoanExeption e) {

            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<LoanDTO>> getLoan(@RequestBody LoanDTO dto){
        LoanDTO loanDTO = loanService.addLoan(dto);
        ResponseMessage<LoanDTO> responseMessage = new ResponseMessage("Loan successfully issued", loanDTO);
        return ResponseEntity.ok(responseMessage);
    }
}
