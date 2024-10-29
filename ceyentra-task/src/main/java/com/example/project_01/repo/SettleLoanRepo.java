package com.example.project_01.repo;

import com.example.project_01.entity.LoanSettle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettleLoanRepo extends JpaRepository<LoanSettle, String> {
    LoanSettle findFirstByOrderByDateDesc();
    List<LoanSettle> findByLoan_Account_AccntId(int id);

    List<LoanSettle> findByLoan_Account_UserId(int id);

}
