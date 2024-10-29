package com.example.project_01.repo;

import com.example.project_01.entity.DepositeMoney;
import com.example.project_01.entity.WithdrawMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface WithdrawMoneyRepo extends JpaRepository<WithdrawMoney, String> {
    WithdrawMoney findFirstByOrderByDateDesc();
    List<WithdrawMoney> findByAccount_AccntId(int id);

    List<WithdrawMoney> findByAccount_User_Id(int id);
}
