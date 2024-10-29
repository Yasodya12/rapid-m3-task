package com.example.project_01.entity;


import com.example.project_01.entity.enums.ActTypes;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Account {
    public Account(int accntId, ActTypes actType, double balance, User user) {
        this.accntId = accntId;
        this.actType = actType;
        this.balance = balance;
        this.user = user;
    }

    @Id
    private int accntId;
    @Enumerated(EnumType.STRING)
    private ActTypes actType;
    private double balance;

//    ,referencedColumnName = "id",insertable = false,updatable = false,nullable = false
@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
