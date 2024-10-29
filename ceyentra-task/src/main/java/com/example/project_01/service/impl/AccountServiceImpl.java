package com.example.project_01.service.impl;

import com.example.project_01.dto.AccountDTO;
import com.example.project_01.dto.UserDTO;
import com.example.project_01.entity.Account;
import com.example.project_01.entity.User;
import com.example.project_01.entity.enums.ActTypes;
import com.example.project_01.ex.AccountException;
import com.example.project_01.ex.UserNotFoundException;
import com.example.project_01.repo.AccountRepo;
import com.example.project_01.repo.UserRepo;
import com.example.project_01.service.AccountService;
import com.google.gson.reflect.TypeToken;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

    AccountRepo accountRepo;

    UserRepo userRepo;
    ModelMapper modelMapper;

    @Autowired
    public AccountServiceImpl(AccountRepo accountRepo,UserRepo userRepo,ModelMapper modelMapper) {
        this.accountRepo=accountRepo;
        this.modelMapper=modelMapper;
        this.userRepo=userRepo;
    }

    @Override
    public AccountDTO addAccount(AccountDTO accountDTO) throws AccountException, UserNotFoundException{


        try {
            User user =accountConverer(accountDTO);
            Account account = new Account(accountDTO.getAccntId(), accountDTO.getActType(),accountDTO.getBalance(),user);
            if (!accountRepo.existsById(accountDTO.getAccntId())){
                accountRepo.save(account);
                return DTOconvertor(account);
            }
            throw new AccountException("Account ID is already exists");
        }catch (Throwable e){
            throw new AccountException("Account ID is already exists",e);
        }



    }

    @Override
    public AccountDTO updateAccount(AccountDTO accountDTO) throws AccountException, UserNotFoundException {
        try {
            User user =accountConverer(accountDTO);
            Account account = new Account(accountDTO.getAccntId(), accountDTO.getActType(),accountDTO.getBalance(),user);
            if (accountRepo.existsById(accountDTO.getAccntId())){
                accountRepo.save(account);
                return DTOconvertor(account);
            }
            throw new AccountException("Current account ID is not exists ");
        }catch (Throwable e){
            throw new AccountException("Current account ID is not exists ",e);
        }
    }

    @Override
    public AccountDTO findByID(int id) throws AccountException {
        try {

            if (accountRepo.existsById(id)){
                Account account = accountRepo.findById(id).get();
                return DTOconvertor(account);
            }
            throw new AccountException("Current account ID is not exists ");
        }catch (Throwable e){
            throw new AccountException("Current account ID is not exists ",e);
        }
    }

    @Override
    public AccountDTO deleteAccount(int id) throws AccountException {
        if (accountRepo.existsById(id)){
            Account account = accountRepo.findById(id).get();
            accountRepo.deleteById(id);
            return DTOconvertor(account);

        }
        throw new AccountException("Dont have Account with current ID");
    }

    @Override
    public List<AccountDTO> allAccount() {
        List<Account> all = (List<Account>) accountRepo.findAll();
        return toDtoList(all);

    }

    public User accountConverer(AccountDTO accountDTO) throws UserNotFoundException{

        try {
            User user = userRepo.findById(accountDTO.getUser()).get();
            if (user != null ) {

                return user;

            }
            throw new UserNotFoundException("User not found");
        }catch (Exception e){
            System.out.println("exe ek "+e.getMessage());
            throw new UserNotFoundException("Methana", e);
        }


    }

    private AccountDTO DTOconvertor(Account account){
      return   new AccountDTO(account.getAccntId(),account.getActType(),account.getBalance(),account.getUser().getId());
    }

    private List<AccountDTO> toDtoList(List<Account> accounts){
        List<AccountDTO> dtoList=new ArrayList<>();
        for (Account account:accounts){
            dtoList.add(new AccountDTO(account.getAccntId(),account.getActType(),account.getBalance(),account.getUser().getId())) ;
        }
        return dtoList;
    }
}
