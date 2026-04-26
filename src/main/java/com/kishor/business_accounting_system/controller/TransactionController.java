package com.kishor.business_accounting_system.controller;

import com.kishor.business_accounting_system.dto.TransactionCreateDto;
import com.kishor.business_accounting_system.dto.TransactionResponseDto;
import com.kishor.business_accounting_system.dto.TransactionUpdateDto;
import com.kishor.business_accounting_system.service.TransactionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<TransactionResponseDto> createTransaction(
            @RequestBody TransactionCreateDto dto) {
        return ResponseEntity.ok(transactionService.saveTransaction(dto));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<Page<TransactionResponseDto>> getAllTransactions(Pageable pageable) {
        return ResponseEntity.ok(transactionService.getAllTransactions(pageable));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> updateTransaction(
            @PathVariable Long id,
            @RequestBody TransactionUpdateDto dto) {

        return ResponseEntity.ok(transactionService.updateTransaction(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {

        transactionService.deleteTransaction(id);

        return ResponseEntity.ok("Transaction deleted successfully");
    }
}