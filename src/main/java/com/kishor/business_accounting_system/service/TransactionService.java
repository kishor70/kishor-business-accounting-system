package com.kishor.business_accounting_system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kishor.business_accounting_system.dto.TransactionCreateDto;
import com.kishor.business_accounting_system.dto.TransactionResponseDto;
import com.kishor.business_accounting_system.dto.TransactionUpdateDto;

public interface TransactionService {

    TransactionResponseDto saveTransaction(TransactionCreateDto dto);

    Page<TransactionResponseDto> getAllTransactions(Pageable pageable);

    TransactionResponseDto getTransactionById(Long id);

    TransactionResponseDto updateTransaction(Long id, TransactionUpdateDto dto);

    void deleteTransaction(Long id);
}