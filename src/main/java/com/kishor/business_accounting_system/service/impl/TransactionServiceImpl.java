package com.kishor.business_accounting_system.service.impl;

import com.kishor.business_accounting_system.dto.TransactionCreateDto;
import com.kishor.business_accounting_system.dto.TransactionResponseDto;
import com.kishor.business_accounting_system.dto.TransactionUpdateDto;
import com.kishor.business_accounting_system.entity.*;
import com.kishor.business_accounting_system.repository.*;
import com.kishor.business_accounting_system.service.TransactionService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final MahajanRepository mahajanRepository;
    private final ModelMapper modelMapper;

    public TransactionServiceImpl(
            TransactionRepository transactionRepository,
            AccountRepository accountRepository,
            CustomerRepository customerRepository,
            MahajanRepository mahajanRepository,
            ModelMapper modelMapper) {

        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.mahajanRepository = mahajanRepository;
        this.modelMapper = modelMapper;
    }

    // ================= CREATE =================
    @Override
    @Transactional
    public TransactionResponseDto saveTransaction(TransactionCreateDto dto) {

        Transaction t = new Transaction();

        t.setType(dto.getType());
        t.setAmount(dto.getAmount());
        t.setDate(dto.getDate());
        t.setDescription(dto.getDescription());

        Account account = null;
        Customer customer = null;
        Mahajan mahajan = null;

        if (dto.getAccountId() != null) {
            account = accountRepository.findById(dto.getAccountId())
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            t.setAccount(account);
        }

        if (dto.getCustomerId() != null) {
            customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            t.setCustomer(customer);
        }

        if (dto.getMahajanId() != null) {
            mahajan = mahajanRepository.findById(dto.getMahajanId())
                    .orElseThrow(() -> new RuntimeException("Mahajan not found"));
            t.setMahajan(mahajan);
        }

        applyTransaction(t.getType(), t.getAmount(), account, customer, mahajan);

        return mapToDto(transactionRepository.save(t));
    }

    // ================= GET ALL =================
    @Override
    public Page<TransactionResponseDto> getAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable)
                .map(this::mapToDto);
    }

    // ================= GET BY ID =================
    @Override
    public TransactionResponseDto getTransactionById(Long id) {
        Transaction t = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        return mapToDto(t);
    }

    // ================= UPDATE =================
    @Override
    @Transactional
    public TransactionResponseDto updateTransaction(Long id, TransactionUpdateDto dto) {

        Transaction t = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        // 🔥 Reverse old transaction
        reverseTransaction(t);

        t.setType(dto.getType());
        t.setAmount(dto.getAmount());
        t.setDate(dto.getDate());
        t.setDescription(dto.getDescription());

        Account account = null;
        Customer customer = null;
        Mahajan mahajan = null;

        if (dto.getAccountId() != null) {
            account = accountRepository.findById(dto.getAccountId())
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            t.setAccount(account);
        } else {
            t.setAccount(null);
        }

        if (dto.getCustomerId() != null) {
            customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            t.setCustomer(customer);
            t.setMahajan(null);
        }

        if (dto.getMahajanId() != null) {
            mahajan = mahajanRepository.findById(dto.getMahajanId())
                    .orElseThrow(() -> new RuntimeException("Mahajan not found"));
            t.setMahajan(mahajan);
            t.setCustomer(null);
        }

        applyTransaction(t.getType(), t.getAmount(), account, customer, mahajan);

        return mapToDto(transactionRepository.save(t));
    }

    // ================= DELETE =================
    @Override
    @Transactional
    public void deleteTransaction(Long id) {

        Transaction t = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        reverseTransaction(t);

        transactionRepository.delete(t);
    }

    // ================= APPLY BUSINESS LOGIC =================
    private void applyTransaction(
            TransactionType type,
            Double amount,
            Account account,
            Customer customer,
            Mahajan mahajan
    ) {

        switch (type) {

            case SALE:
                if (customer == null)
                    throw new RuntimeException("Customer required for SALE");

                customer.setBalance(customer.getBalance() + amount);
                customerRepository.save(customer);

                if (account != null) {
                    account.setBalance(account.getBalance() + amount);
                    accountRepository.save(account);
                }
                break;

            case PURCHASE:
                if (mahajan == null)
                    throw new RuntimeException("Mahajan required for PURCHASE");

                mahajan.setBalance(mahajan.getBalance() + amount);
                mahajanRepository.save(mahajan);

                if (account != null) {
                    account.setBalance(account.getBalance() - amount);
                    accountRepository.save(account);
                }
                break;

            case RECEIVE:
                if (customer != null) {
                    customer.setBalance(customer.getBalance() - amount);
                    customerRepository.save(customer);
                }

                if (mahajan != null) {
                    mahajan.setBalance(mahajan.getBalance() - amount);
                    mahajanRepository.save(mahajan);
                }

                if (account != null) {
                    account.setBalance(account.getBalance() + amount);
                    accountRepository.save(account);
                }
                break;

            case PAY:
                if (mahajan != null) {
                    mahajan.setBalance(mahajan.getBalance() - amount);
                    mahajanRepository.save(mahajan);
                }

                if (account != null) {
                    account.setBalance(account.getBalance() - amount);
                    accountRepository.save(account);
                }
                break;

            case EXPENSE:
                if (customer != null || mahajan != null)
                    throw new RuntimeException("Expense cannot have customer or mahajan");

                if (account != null) {
                    account.setBalance(account.getBalance() - amount);
                    accountRepository.save(account);
                }
                break;
        }
    }

    // ================= REVERSE =================
    private void reverseTransaction(Transaction t) {

        applyTransaction(
                t.getType(),
                -t.getAmount(),
                t.getAccount(),
                t.getCustomer(),
                t.getMahajan()
        );
    }

    // ================= DTO MAPPING =================
    private TransactionResponseDto mapToDto(Transaction t) {

        TransactionResponseDto dto =
                modelMapper.map(t, TransactionResponseDto.class);

        if (t.getAccount() != null) {
            dto.setId(t.getAccount().getId());
            dto.setAccountName(t.getAccount().getName());
        }

        if (t.getCustomer() != null) {
            dto.setId(t.getCustomer().getId());
            dto.setCustomerName(t.getCustomer().getName());
        }

        if (t.getMahajan() != null) {
            dto.setId(t.getMahajan().getId());
            dto.setMahajanName(t.getMahajan().getName());
        }

        return dto;
    }
}