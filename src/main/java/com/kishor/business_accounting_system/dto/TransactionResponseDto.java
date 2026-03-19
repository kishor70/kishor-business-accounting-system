package com.kishor.business_accounting_system.dto;


import java.time.LocalDate;

import com.kishor.business_accounting_system.entity.TransactionType;


public class TransactionResponseDto {

    private Long id;

    private TransactionType type;

    private Double amount;

    private LocalDate date;

    private String description;

    private String accountName;

    private String customerName;

    private String mahajanName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMahajanName() {
		return mahajanName;
	}

	public void setMahajanName(String mahajanName) {
		this.mahajanName = mahajanName;
	}

	public TransactionResponseDto(Long id, TransactionType type, Double amount, LocalDate date, String description,
			String accountName, String customerName, String mahajanName) {
		super();
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.accountName = accountName;
		this.customerName = customerName;
		this.mahajanName = mahajanName;
	}
    
}