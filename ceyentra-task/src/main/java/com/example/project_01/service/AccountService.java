package com.example.project_01.service;

import com.example.project_01.dto.AccountDTO;
import com.example.project_01.ex.AccountException;
import com.example.project_01.ex.UserNotFoundException;

import java.util.List;

public interface AccountService {
     AccountDTO addAccount(AccountDTO accountDTO) throws AccountException,UserNotFoundException;
     AccountDTO updateAccount(AccountDTO accountDTO) throws AccountException,UserNotFoundException;
     AccountDTO findByID(int id) throws AccountException;

     AccountDTO deleteAccount(int id) throws AccountException;
     List<AccountDTO> allAccount();
}
