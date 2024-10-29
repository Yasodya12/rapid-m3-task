package com.example.project_01.dto;

import com.example.project_01.entity.Account;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class WithdrawMoneyDTO {

    public WithdrawMoneyDTO(String withdrawId, double withdrawAmount, Date date, int account) {
        this.withdrawId = withdrawId;
        this.withdrawAmount = withdrawAmount;
        this.date = date;
        this.account = account;
    }

    private String withdrawId;
    private double withdrawAmount;


    private Date date;


    private int account;
}
