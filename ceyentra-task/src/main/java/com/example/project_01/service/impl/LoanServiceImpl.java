package com.example.project_01.service.impl;

import com.example.project_01.dto.LoanDTO;
import com.example.project_01.entity.Loan;
import com.example.project_01.ex.LoanExeption;
import com.example.project_01.ex.LoanSettleExeption;
import com.example.project_01.repo.AccountRepo;
import com.example.project_01.repo.LoanRepo;
import com.example.project_01.service.LoanService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LoanServiceImpl implements LoanService {

    LoanRepo loanRepo;

    AccountRepo accountRepo;



    public LoanServiceImpl(LoanRepo loanRepo, AccountRepo accountRepo) {
        this.loanRepo = loanRepo;
        this.accountRepo = accountRepo;
    }

    @Override
    public LoanDTO addLoan(LoanDTO loanDTO) {

        loanDTO.setLoanID(incrementId(loanRepo.findFirstByOrderByLoanIDDesc().getLoanID()));

        double interest = loanDTO.getAmount() * 10 / 100*loanDTO.getPeriod();
        loanDTO.setInterest(interest);
        double totWithInterest = interest + loanDTO.getAmount();
        loanDTO.setAmountWithInterest(totWithInterest);
        loanDTO.setRemaingAmount(totWithInterest);

        double mountyInstament=totWithInterest/(loanDTO.getPeriod()*12);
        loanDTO.setMonthlySettlement(mountyInstament);

        return toLoanDto(loanRepo.save(toEntity(loanDTO)));
    }

    @Override
    public List<LoanDTO> loanByAct(int id) {
        List<Loan> byAccountAccntId = loanRepo.findByAccount_AccntId(id);
        return toDtoList(byAccountAccntId);
    }

    @Override
    public List<LoanDTO> loanByUser(int id) throws LoanExeption {

        List<Loan> byAccountUserId = loanRepo.findByAccount_User_Id(id);
        if (byAccountUserId.size()<=0){
            throw new LoanExeption("No loan settle deaitls in to customer Id with "+id);
        }
        return toDtoList(byAccountUserId);
    }


    private Loan toEntity(LoanDTO loanDTO){





        return new Loan(loanDTO.getLoanID(), loanDTO.getLoanTypes(),loanDTO.getPeriod(),loanDTO.getAmount()
                ,loanDTO.getInterest(),loanDTO.getAmountWithInterest(),loanDTO.getRemaingAmount()
                ,loanDTO.getMonthlySettlement(),accountRepo.findById(loanDTO.getAccount()).get());

    }

    private LoanDTO toLoanDto(Loan loan){
        return new LoanDTO(loan.getLoanID(), loan.getLoanTypes(),loan.getPeriod(),loan.getAmount()
                ,loan.getInterest(),loan.getAmountWithInterest(),loan.getRemaingAmount()
                ,loan.getMonthlySettlement(),loan.getAccount().getAccntId());
    }

    private String incrementId(String originalId) {

        String numericPart = originalId.substring(2);


        int incrementedValue = Integer.parseInt(numericPart) + 1;


        String incrementedId = String.format("LN%03d", incrementedValue);

        return incrementedId;
    }

    private List<LoanDTO> toDtoList(List<Loan> loans){
        ArrayList<LoanDTO> dtoList=new ArrayList();
        for (Loan loan:loans) {
            dtoList.add(new LoanDTO(loan.getLoanID(), loan.getLoanTypes(),loan.getPeriod(),loan.getAmount()
                    ,loan.getInterest(),loan.getAmountWithInterest(),loan.getRemaingAmount()
                    ,loan.getMonthlySettlement(),loan.getAccount().getAccntId()));
        }
        return dtoList;
    }
}
