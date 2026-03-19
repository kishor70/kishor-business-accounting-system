package com.kishor.business_accounting_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Table(name = "customers")
public class Customer {
	
	public Customer() {}

	
    public Customer(Long id, String name, String phone, String address, Double balance) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.balance = balance;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //1

    private String name; //Satar

    private String phone; //74837387468

    private String address; //kolkata

    private Double balance; //50000

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
    
}