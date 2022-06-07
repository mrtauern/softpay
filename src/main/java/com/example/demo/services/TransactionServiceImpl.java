package com.example.demo.services;

import com.example.demo.models.Account;
import com.example.demo.models.Input;
import com.example.demo.models.Output;
import com.example.demo.models.Transaction;
import com.example.demo.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service("TransactionService")
public class TransactionServiceImpl implements TransactionService {

    Logger log = Logger.getLogger(TransactionServiceImpl.class.getName());

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Override
    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(Long id){
        return transactionRepository.findById(id).get();
    }

    @Override
    public Transaction save(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(Long id){
        transactionRepository.deleteById(id);
    }

    @Override
    public void delete(Transaction transaction){
        transactionRepository.delete(transaction);
    }

    @Override
    public Output input(Input input){
        Account account = null;
        boolean amountCheck = amountCheck(input);
        List<Account> accounts = accountService.findAll();

        log.info("Card number: "+input.getCard_number());

        for (Account a: accounts) {
            if (a.getCard_number().equals(input.getCard_number())){
                account = a;
                break;
            }
        }

        if(account == null){
            return new Output(true, "Account doesn't exist", input.getThreat_score());
        }

        save(new Transaction(input.getAmount(), input.getCurrency(), input.getTerminal_id(), input.getThreat_score(), amountCheck, account.getId()));

        if(amountCheck == false){
            return new Output(true, "Amount too large given the thread score for this terminal", input.getThreat_score());
        }

        return new Output(false, "No rejection", input.getThreat_score());
    }

    @Override
    public boolean amountCheck(Input input){
        if(input.getThreat_score() < 20){
            return false;
        } else if (input.getThreat_score() < 40 && input.getAmount() > 2000) {
            return false;
        } else if (input.getThreat_score() < 60 && input.getAmount() > 4000) {
            return false;
        } else if (input.getThreat_score() < 80 && input.getAmount() > 8000) {
            return false;
        }

        return true;
    }
}
