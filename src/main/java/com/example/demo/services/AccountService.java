package com.example.demo.services;

import com.example.demo.models.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AccountService")
public interface AccountService {
    List<Account> findAll();

    Account findById(Long id);

    Account save(Account account);

    void deleteById(Long id);

    void delete(Account account);
}
