package com.kishor.business_accounting_system.service.impl;

import com.kishor.business_accounting_system.dto.TransactionCreateDto;
import com.kishor.business_accounting_system.dto.TransactionUpdateDto;
import com.kishor.business_accounting_system.entity.*;
import com.kishor.business_accounting_system.repository.*;
import com.kishor.business_accounting_system.service.TransactionService;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final MahajanRepository mahajanRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AccountRepository accountRepository,
                                  CustomerRepository customerRepository,
                                  MahajanRepository mahajanRepository) {

        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.mahajanRepository = mahajanRepository;
    }

    // CREATE TRANSACTION
    @Override
    @Transactional
    public Transaction saveTransaction(TransactionCreateDto dto) {

    	TransactionCreateDto transaction = new TransactionCreateDto();

        transaction.setType(dto.getType());
        transaction.setAmount(dto.getAmount());
        transaction.setDate(dto.getDate());
        transaction.setDescription(dto.getDescription());

        // ACCOUNT (optional)
        Account account = null;

        if(dto.getAccountId() != null){
            account = accountRepository.findById(dto.getAccountId())
                    .orElseThrow(() -> new RuntimeException("Account not found"));

            transaction.setAccount(account);
        }

        // ================= SALE =================
        if(dto.getType() == TransactionType.SALE){

            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            // customer owes money
            customer.setBalance(customer.getBalance() + dto.getAmount());

            // if customer paid immediately
            if(account != null){
            	Double balance = account.getBalance() == null ? 0.0 : account.getBalance();

                account.setBalance(balance + dto.getAmount());

                accountRepository.save(account);
            }
            
            customerRepository.save(customer);
            transaction.setCustomer(customer);
        }

        // ================= PURCHASE =================
        else if(dto.getType() == TransactionType.PURCHASE){

            Mahajan mahajan = mahajanRepository.findById(dto.getMahajanId())
                    .orElseThrow(() -> new RuntimeException("Mahajan not found"));

            // we owe mahajan money
            mahajan.setBalance(mahajan.getBalance() + dto.getAmount());

            // if we paid immediately
            if(account != null){
                account.setBalance(account.getBalance() - dto.getAmount());
            }

            mahajanRepository.save(mahajan);
            transaction.setMahajan(mahajan);
        }

        // ================= CUSTOMER PAYMENT =================
        else if(dto.getType() == TransactionType.CUSTOMER_PAYMENT){

            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            // reduce customer debt
            customer.setBalance(customer.getBalance() - dto.getAmount());

            if(account != null){
                account.setBalance(account.getBalance() + dto.getAmount());
            }

            customerRepository.save(customer);
            transaction.setCustomer(customer);
        }

        // ================= MAHAJAN PAYMENT =================
        else if(dto.getType() == TransactionType.MAHAJAN_PAYMENT){

            Mahajan mahajan = mahajanRepository.findById(dto.getMahajanId())
                    .orElseThrow(() -> new RuntimeException("Mahajan not found"));

            // reduce mahajan debt
            mahajan.setBalance(mahajan.getBalance() - dto.getAmount());

            if(account != null){
                account.setBalance(account.getBalance() - dto.getAmount());
            }

            mahajanRepository.save(mahajan);
            transaction.setMahajan(mahajan);
        }

        // save account only if used
        if(account != null){
            accountRepository.save(account);
        }

        return transactionRepository.save(transaction);
    }
    // GET ALL
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // GET BY ID
    @Override
    public Transaction getTransactionById(Long id) {

        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    // UPDATE
    @Override
    public Transaction updateTransaction(Long id, TransactionUpdateDto dto) {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        transaction.setType(dto.getType());
        transaction.setAmount(dto.getAmount());
        transaction.setDate(dto.getDate());
        transaction.setDescription(dto.getDescription());

        if (dto.getAccountId() != null) {

            Account account = accountRepository.findById(dto.getAccountId())
                    .orElseThrow(() -> new RuntimeException("Account not found"));

            transaction.setAccount(account);
        }

        if (dto.getCustomerId() != null) {

            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            transaction.setCustomer(customer);
            transaction.setMahajan(null);
        }

        if (dto.getMahajanId() != null) {

            Mahajan mahajan = mahajanRepository.findById(dto.getMahajanId())
                    .orElseThrow(() -> new RuntimeException("Mahajan not found"));

            transaction.setMahajan(mahajan);
            transaction.setCustomer(null);
        }

        return transactionRepository.save(transaction);
    }

    // DELETE
    @Override
    public void deleteTransaction(Long id) {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        transactionRepository.delete(transaction);
    }
}