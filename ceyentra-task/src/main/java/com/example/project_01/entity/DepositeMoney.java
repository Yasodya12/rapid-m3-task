package com.example.project_01.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class DepositeMoney {
    public DepositeMoney(String depositeID, double depositeAmount,  Account account) {
        this.depositeID = depositeID;
        this.depositeAmount = depositeAmount;
        this.account = account;
    }

    @Id
    private String depositeID;
    private double depositeAmount;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "act_id")
    private Account account;
}
