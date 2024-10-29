package com.example.project_01.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@ToString
public class LoanSettle {
    public LoanSettle(String id, double amount, Date date, Loan loan) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.loan = loan;
    }

    @Id
    private String id;

    private double amount;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false, updatable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
}
