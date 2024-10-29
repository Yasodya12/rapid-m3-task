package com.example.project_01.service;

import com.example.project_01.dto.LoanSettleDTO;
import com.example.project_01.ex.LoanSettleExeption;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettleLoanService {
    LoanSettleDTO payLoan(LoanSettleDTO loanSettleDTO) throws LoanSettleExeption;

     LoanSettleDTO searchByPayId(String  id) throws LoanSettleExeption;
    List<LoanSettleDTO> payLoanByActId(int id) throws LoanSettleExeption;

    List<LoanSettleDTO> payLoanByUserId(int id) throws LoanSettleExeption;
}
