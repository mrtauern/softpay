package com.example.demo.controllers;

import com.example.demo.models.Input;
import com.example.demo.models.Output;
import com.example.demo.models.Transaction;
import com.example.demo.services.AccountService;
import com.example.demo.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestController
public class TransactionController {
    public TransactionController(){

    }
    Logger log = Logger.getLogger(TransactionController.class.getName());

    @Autowired
    TransactionService transactionService;

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> allTransactions(){
        try {
            List<Transaction> transactions = transactionService.findAll();
            if (transactions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (NoSuchElementException e){
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<Transaction> transaction(@PathVariable Long id){
        try {
            return new ResponseEntity<>(transactionService.findById(id), HttpStatus.OK);
        } catch (NoSuchElementException e){
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/transaction")
    @ResponseBody
    public ResponseEntity<Transaction> addATransaction(@RequestBody Transaction transaction){
        log.info(transaction.toString());
        try {
            return new ResponseEntity<>(transactionService.save(transaction), HttpStatus.CREATED);
        } catch (NoSuchElementException e){
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<Transaction> editTransaction(@RequestBody Transaction transaction, @PathVariable Long id){
        try {
            Transaction _transaction = transactionService.findById(id);
            transaction.setId(_transaction.getId());
            return new ResponseEntity<>(transactionService.save(transaction), HttpStatus.ACCEPTED);
        } catch (NoSuchElementException e){
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id){
        try {
            transactionService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (NoSuchElementException e){
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/input")
    public ResponseEntity<Output> input(@RequestBody Input input){
        log.info("Input called");

        try {
            return new ResponseEntity<>(transactionService.input(input), HttpStatus.ACCEPTED);
        } catch (NoSuchElementException e){
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
