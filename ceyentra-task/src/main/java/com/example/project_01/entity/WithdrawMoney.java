package com.example.project_01.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class WithdrawMoney {
    public WithdrawMoney(String withdrawId, double withdrawAmount, Date date, Account account) {
        this.withdrawId = withdrawId;
        this.withdrawAmount = withdrawAmount;
        this.date = date;
        this.account = account;
    }

    @Id
    private String withdrawId;
    private double withdrawAmount;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "act_id")
    private Account account;

}
