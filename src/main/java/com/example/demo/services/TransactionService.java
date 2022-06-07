package com.example.demo.services;

import com.example.demo.models.Input;
import com.example.demo.models.Output;
import com.example.demo.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TransactionService")
public interface TransactionService {
    List<Transaction> findAll();

    Transaction findById(Long id);

    Transaction save(Transaction transaction);

    void deleteById(Long id);

    void delete(Transaction transaction);

    Output input(Input input);

    boolean amountCheck(Input input);
}
