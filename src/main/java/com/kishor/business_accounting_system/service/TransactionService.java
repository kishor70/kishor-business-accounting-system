package com.kishor.business_accounting_system.service;

import java.util.List;

import com.kishor.business_accounting_system.dto.TransactionCreateDto;
import com.kishor.business_accounting_system.dto.TransactionResponseDto;
import com.kishor.business_accounting_system.dto.TransactionUpdateDto;
import com.kishor.business_accounting_system.entity.Transaction;

public interface TransactionService {

    Transaction saveTransaction(TransactionCreateDto dto);

    List<TransactionResponseDto> getAllTransactions();

    Transaction getTransactionById(Long id);

    Transaction updateTransaction(Long id, TransactionUpdateDto dto);

    void deleteTransaction(Long id);
}