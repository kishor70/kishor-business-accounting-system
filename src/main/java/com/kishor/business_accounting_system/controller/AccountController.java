package com.kishor.business_accounting_system.controller;

import com.kishor.business_accounting_system.entity.Account;
import com.kishor.business_accounting_system.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    // CREATE ACCOUNT
    @PostMapping
    public Account createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    // GET ALL ACCOUNTS
    @GetMapping("/allaccounts")
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    // GET ACCOUNT BY ID
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }
}