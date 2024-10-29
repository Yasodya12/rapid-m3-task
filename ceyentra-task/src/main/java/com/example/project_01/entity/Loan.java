package com.example.project_01.entity;


import com.example.project_01.entity.enums.LoanTypes;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Loan {
    public Loan(String loanID, LoanTypes loanTypes, double period, double amount, double interest,
                double amountWithInterest, double remaingAmount, double monthlySettlement, Account account) {
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

    @Id
    private String loanID;
    @Enumerated(EnumType.STRING)
    private LoanTypes loanTypes;

    private double period;

    private double amount;

    private double interest;

    private double amountWithInterest;

    private double remaingAmount;

    private double monthlySettlement;

    @ManyToOne
    @JoinColumn(name = "act_id")
    private Account account;

}
