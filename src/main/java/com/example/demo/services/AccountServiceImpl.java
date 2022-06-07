package com.example.demo.services;

import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AccountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id){
        return accountRepository.findById(id).get();
    }

    @Override
    public Account save(Account account){
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Long id){
        accountRepository.deleteById(id);
    }

    @Override
    public void delete(Account account){
        accountRepository.delete(account);
    }
}
