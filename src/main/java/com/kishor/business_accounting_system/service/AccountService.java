package com.kishor.business_accounting_system.service;

import com.kishor.business_accounting_system.entity.Account;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    Account createAccount(Account account);

    List<Account> getAllAccounts();

    Account getAccountById(Long id);

}