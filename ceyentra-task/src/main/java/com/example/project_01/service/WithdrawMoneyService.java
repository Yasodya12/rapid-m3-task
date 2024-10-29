package com.example.project_01.service;

import com.example.project_01.dto.DepositeMoneyDTO;
import com.example.project_01.dto.WithdrawMoneyDTO;
import com.example.project_01.ex.AccountException;
import com.example.project_01.ex.InsufficientBalanceExeption;

import java.util.List;

public interface WithdrawMoneyService {
    WithdrawMoneyDTO withdrawMoney(WithdrawMoneyDTO withdrawMoneyDTO) throws InsufficientBalanceExeption, AccountException;

    List<WithdrawMoneyDTO> getListByActId(int id) throws AccountException;

    List<WithdrawMoneyDTO> getListByUserId(int id) throws AccountException;
}
