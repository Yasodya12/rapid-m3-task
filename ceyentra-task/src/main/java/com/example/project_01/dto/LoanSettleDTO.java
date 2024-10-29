package com.example.project_01.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class LoanSettleDTO {
    public LoanSettleDTO(String id, double amount, Date date, String loan) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.loan = loan;
    }

    private String id;

    private double amount;


    private Date date;


    private String loan;
}
