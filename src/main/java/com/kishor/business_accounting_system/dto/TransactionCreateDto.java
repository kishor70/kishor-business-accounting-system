package com.kishor.business_accounting_system.dto;

import java.time.LocalDate;

import com.kishor.business_accounting_system.entity.TransactionType;

import lombok.Data;


public class TransactionCreateDto {

    public TransactionCreateDto(TransactionType type, Double amount, LocalDate date, String description, Long accountId,
			Long customerId, Long mahajanId) {
		super();
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.accountId = accountId;
		this.customerId = customerId;
		this.mahajanId = mahajanId;
	}
    
    
	private TransactionType type;

    private Double amount;

    private LocalDate date;

    private String description;

    private Long accountId;

    private Long customerId;

    private Long mahajanId;

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getMahajanId() {
		return mahajanId;
	}

	public void setMahajanId(Long mahajanId) {
		this.mahajanId = mahajanId;
	}


}