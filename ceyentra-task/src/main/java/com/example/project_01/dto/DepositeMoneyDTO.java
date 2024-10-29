package com.example.project_01.dto;

import com.example.project_01.entity.Account;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;



@Data
@NoArgsConstructor
@ToString
public class DepositeMoneyDTO {
    public DepositeMoneyDTO(String depositeID, double depositeAmount, Date date, int account) {
        this.depositeID = depositeID;
        this.depositeAmount = depositeAmount;
        this.date = date;
        this.account = account;
    }

    private String depositeID;
    private double depositeAmount;
    private Date date;
    private int account;
}
