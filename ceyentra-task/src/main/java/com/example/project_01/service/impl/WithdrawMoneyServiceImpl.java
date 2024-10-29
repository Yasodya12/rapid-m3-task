package com.example.project_01.service.impl;

import com.example.project_01.dto.DepositeMoneyDTO;
import com.example.project_01.dto.WithdrawMoneyDTO;
import com.example.project_01.entity.Account;
import com.example.project_01.entity.DepositeMoney;
import com.example.project_01.entity.WithdrawMoney;
import com.example.project_01.ex.AccountException;
import com.example.project_01.ex.InsufficientBalanceExeption;
import com.example.project_01.repo.AccountRepo;
import com.example.project_01.repo.WithdrawMoneyRepo;
import com.example.project_01.service.WithdrawMoneyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class WithdrawMoneyServiceImpl implements WithdrawMoneyService {

    AccountRepo accountRepo;
    WithdrawMoneyRepo withdrawMoneyRepo;

    public WithdrawMoneyServiceImpl(AccountRepo accountRepo, WithdrawMoneyRepo withdrawMoneyRepo) {
        this.accountRepo = accountRepo;
        this.withdrawMoneyRepo = withdrawMoneyRepo;
    }


    @Override
    public WithdrawMoneyDTO withdrawMoney(WithdrawMoneyDTO withdrawMoneyDTO) throws InsufficientBalanceExeption, AccountException {

        try {
            if (withdrawMoneyDTO.getWithdrawAmount()> accountRepo.findById(withdrawMoneyDTO.getAccount()).get().getBalance()){
                throw new InsufficientBalanceExeption("Insufficient Balance to withdraw you ammount");
            }
        }catch (Exception e){
            throw new AccountException("Invalid Account ID");
        }

        Account account = accountRepo.findById(withdrawMoneyDTO.getAccount()).get();
        account.setBalance(account.getBalance()-withdrawMoneyDTO.getWithdrawAmount());
        accountRepo.save(account);
        WithdrawMoney save = withdrawMoneyRepo.save(toEnity(withdrawMoneyDTO));
        return toDTO(save);
    }

    @Override
    public List<WithdrawMoneyDTO> getListByActId(int id) throws AccountException {


        List<WithdrawMoneyDTO> withdrawMoneyDTOS = entiyListToDto(withdrawMoneyRepo.findByAccount_AccntId(id));
        if (withdrawMoneyDTOS.size()<=0){
            throw new AccountException("No withdraw happen to Account ID:"+id);
        }

      return withdrawMoneyDTOS;


    }

    @Override
    public List<WithdrawMoneyDTO> getListByUserId(int id) throws AccountException {

        List<WithdrawMoneyDTO> withdrawMoneyDTOS = entiyListToDto(withdrawMoneyRepo.findByAccount_User_Id(id));
        if (withdrawMoneyDTOS.size()<=0){
            throw new AccountException("There is no withdraws happen to any account with user ID:"+id);
        }

        return withdrawMoneyDTOS;
    }

    private WithdrawMoney toEnity(WithdrawMoneyDTO withdrawMoneyDTO) throws AccountException {

        try {


            return new WithdrawMoney(incrementId(withdrawMoneyRepo.findFirstByOrderByDateDesc().getWithdrawId()), withdrawMoneyDTO.getWithdrawAmount(),
                    withdrawMoneyDTO.getDate(),
                    accountRepo.findById(withdrawMoneyDTO.getAccount()).get());
        }catch (Exception e){
            throw new AccountException("Invalid Account ID");
        }




    }
    private WithdrawMoneyDTO toDTO(WithdrawMoney depositeMoney){
        return new WithdrawMoneyDTO(depositeMoney.getWithdrawId(), depositeMoney.getWithdrawAmount(),depositeMoney.getDate(), depositeMoney.getAccount().getAccntId());
    }


    private String incrementId(String originalId) {

        String numericPart = originalId.substring(4);


        int incrementedValue = Integer.parseInt(numericPart) + 1;


        String incrementedId = String.format("WTDR%03d", incrementedValue);

        return incrementedId;
    }

    private List<WithdrawMoneyDTO> entiyListToDto(List<WithdrawMoney> withdrawMonies){

        ArrayList<WithdrawMoneyDTO> withdrawMoneyDTOS=new ArrayList<>();
        for (WithdrawMoney withdrawMoney:withdrawMonies){

            withdrawMoneyDTOS.add(new WithdrawMoneyDTO(withdrawMoney.getWithdrawId(),
                    withdrawMoney.getWithdrawAmount(),withdrawMoney.getDate(), withdrawMoney.getAccount().getAccntId()));

        }
        return withdrawMoneyDTOS;
    }
}
