package com.kishor.business_accounting_system.repository;

import com.kishor.business_accounting_system.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository 
        extends JpaRepository<Account, Long> {

}