package com.kishor.business_accounting_system.repository;

import com.kishor.business_accounting_system.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    List<Transaction> findByCustomerId(Long customerId);

    List<Transaction> findByMahajanId(Long mahajanId);
    
    List<Transaction> findByAccountId(Long accountId);
}