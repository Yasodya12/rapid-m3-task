package com.example.project_01.dto;

import com.example.project_01.entity.enums.LoanTypes;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoanDTO {
    public LoanDTO(String loanID, LoanTypes loanTypes, double period, double amount, double interest,
                   double amountWithInterest, double remaingAmount, double monthlySettlement, int account) {
        this.loanID = loanID;
        this.loanTypes = loanTypes;
        this.period = period;
        this.amount = amount;
        this.interest = interest;
        this.amountWithInterest = amountWithInterest;
        this.remaingAmount = remaingAmount;
        this.monthlySettlement = monthlySettlement;
        this.account = account;
    }

    private String loanID;

    private LoanTypes loanTypes;

    private double period;

    private double amount;

    private double interest;

    private double amountWithInterest;

    private double remaingAmount;

    private double monthlySettlement;
    private int account;
}
