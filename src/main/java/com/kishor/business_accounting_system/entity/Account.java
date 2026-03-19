package com.kishor.business_accounting_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Table(name = "accounts")
public class Account {
	
	public Account() {
    }
	

    public Account(Long id, String accountName, String type, Double balance) {
		super();
		this.id = id;
		this.accountName = accountName;
		this.type = type;
		this.balance = balance;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //1

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "type")
    private String type;

    @Column(name = "balance")
    private Double balance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	
}