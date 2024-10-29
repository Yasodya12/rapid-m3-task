package com.example.project_01.repo;

import com.example.project_01.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepo extends JpaRepository<Loan, String> {
    Loan findFirstByOrderByLoanIDDesc();

    List<Loan> findByAccount_AccntId(int id);

    List<Loan> findByAccount_User_Id(int id);
}
