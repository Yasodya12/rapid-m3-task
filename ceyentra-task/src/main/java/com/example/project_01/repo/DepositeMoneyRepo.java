package com.example.project_01.repo;

import com.example.project_01.entity.DepositeMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface DepositeMoneyRepo extends   JpaRepository<DepositeMoney, String>{
//   DepositeMoney findTopByOrderByIdDesc();

    DepositeMoney findFirstByOrderByDateDesc();
    List<DepositeMoney> findByAccount_AccntId(int id);

    List<DepositeMoney> findByAccount_User_Id(int id);



}
