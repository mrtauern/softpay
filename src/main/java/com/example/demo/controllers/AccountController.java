package com.example.demo.controllers;

import com.example.demo.models.Account;
import com.example.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestController
public class AccountController {
    public AccountController(){

    }
    Logger log = Logger.getLogger(AccountController.class.getName());

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> allAccounts(){
        try {
            List<Account> accounts = accountService.findAll();
            if (accounts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        } catch (NoSuchElementException e){
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> account(@PathVariable Long id){
        try {
            return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
        } catch (NoSuchElementException e){
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/account")
    @ResponseBody
    public ResponseEntity<Account> addAccount(@RequestBody Account account){
        log.info(account.toString());
        try {
            return new ResponseEntity<>(accountService.save(account), HttpStatus.CREATED);
        } catch (NoSuchElementException e){
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<Account> editAccount(@RequestBody Account account, @PathVariable Long id){
        try {
            Account _account = accountService.findById(id);
            account.setId(_account.getId());
            return new ResponseEntity<>(accountService.save(account), HttpStatus.ACCEPTED);
        } catch (NoSuchElementException e){
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        try {
            accountService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (NoSuchElementException e){
            log.info(e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
