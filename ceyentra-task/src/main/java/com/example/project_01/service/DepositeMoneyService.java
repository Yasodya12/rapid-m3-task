package com.example.project_01.service;

import com.example.project_01.dto.DepositeMoneyDTO;
import com.example.project_01.entity.DepositeMoney;
import com.example.project_01.ex.AccountException;

import java.util.List;

public interface DepositeMoneyService {

    DepositeMoneyDTO depositeMoney(DepositeMoneyDTO depositeMoneyDTO) throws AccountException;

    List<DepositeMoneyDTO> getListByActId(int id) throws AccountException;

    List<DepositeMoneyDTO> getListByUserId(int id) throws AccountException;

}
