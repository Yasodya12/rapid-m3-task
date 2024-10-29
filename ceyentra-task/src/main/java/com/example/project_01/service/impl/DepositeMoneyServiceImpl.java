package com.example.project_01.service.impl;

import com.example.project_01.dto.DepositeMoneyDTO;
import com.example.project_01.entity.Account;
import com.example.project_01.entity.DepositeMoney;
import com.example.project_01.ex.AccountException;
import com.example.project_01.repo.AccountRepo;
import com.example.project_01.repo.DepositeMoneyRepo;
import com.example.project_01.service.DepositeMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DepositeMoneyServiceImpl implements DepositeMoneyService {

    AccountRepo accountRepo;
    DepositeMoneyRepo depositeMoneyRepo;

    @Autowired
    public DepositeMoneyServiceImpl(AccountRepo accountRepo, DepositeMoneyRepo depositeMoneyRepo) {
        this.accountRepo = accountRepo;
        this.depositeMoneyRepo = depositeMoneyRepo;
    }




    @Override
    public DepositeMoneyDTO depositeMoney(DepositeMoneyDTO depositeMoneyDTO) throws AccountException {

        DepositeMoney save = depositeMoneyRepo.save(toEnity(depositeMoneyDTO));
        Account account = accountRepo.findById(depositeMoneyDTO.getAccount()).get();
        account.setBalance(account.getBalance()+depositeMoneyDTO.getDepositeAmount());
        accountRepo.save(account);
        return toDTO(save);
    }

    @Override
    public List<DepositeMoneyDTO> getListByActId(int id) throws AccountException {

        List<DepositeMoneyDTO> depositeMoneyDTOS = entiyListToDto(depositeMoneyRepo.findByAccount_AccntId(id));
        if (depositeMoneyDTOS.size()<=0){
            throw new AccountException("There is no deposit happen to account ID: "+id);
        }

        return depositeMoneyDTOS;
    }

    @Override
    public List<DepositeMoneyDTO> getListByUserId(int id) throws AccountException {
        List<DepositeMoneyDTO> depositeMoneyDTOS = entiyListToDto(depositeMoneyRepo.findByAccount_User_Id(id));
        if (depositeMoneyDTOS.size()<=0){
            throw new AccountException("There is no deposit happen to any account with user ID:"+id);
        }

        return depositeMoneyDTOS;
    }


    private DepositeMoney toEnity(DepositeMoneyDTO depositeMoneyDTO) throws AccountException {

        try {
//            return new DepositeMoney("DPST001", depositeMoneyDTO.getDepositeAmount(),
//                    accountRepo.findById(depositeMoneyDTO.getAccount()).get());

            return new DepositeMoney(incrementId(depositeMoneyRepo.findFirstByOrderByDateDesc().getDepositeID()), depositeMoneyDTO.getDepositeAmount(),
                    accountRepo.findById(depositeMoneyDTO.getAccount()).get());

        }catch (Exception e){
            throw new AccountException("Invalid Account ID");
        }

    }

    private DepositeMoneyDTO toDTO(DepositeMoney depositeMoney){
        return new DepositeMoneyDTO(depositeMoney.getDepositeID(), depositeMoney.getDepositeAmount(),depositeMoney.getDate(), depositeMoney.getAccount().getAccntId());
    }

    private String incrementId(String originalId) {

        String numericPart = originalId.substring(4);


        int incrementedValue = Integer.parseInt(numericPart) + 1;


        String incrementedId = String.format("DPST%03d", incrementedValue);

        return incrementedId;
    }

    private List<DepositeMoneyDTO> entiyListToDto(List<DepositeMoney> depositeMonies){

        ArrayList<DepositeMoneyDTO> depositeMoneyDTOS=new ArrayList<>();
        for (DepositeMoney depositeMoney:depositeMonies){

            depositeMoneyDTOS.add(new DepositeMoneyDTO(depositeMoney.getDepositeID(),
                 depositeMoney.getDepositeAmount(),depositeMoney.getDate(), depositeMoney.getAccount().getAccntId()));

        }
        return depositeMoneyDTOS;
    }
}
