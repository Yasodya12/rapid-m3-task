package com.example.project_01.dto;

import com.example.project_01.entity.embeded.Address;
import com.example.project_01.entity.enums.ActTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDTO {
    public AccountDTO(int accntId, ActTypes actType, double balance, int user) {
        this.accntId = accntId;
        this.actType = actType;
        this.balance = balance;
        this.user = user;
    }

    private int accntId;
    private ActTypes actType;
    private double balance;
    private int user;

}
