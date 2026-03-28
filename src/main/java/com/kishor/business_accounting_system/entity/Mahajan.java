package com.kishor.business_accounting_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Table(name = "mahajans")
public class Mahajan {
	
	public Mahajan() {}
	

	public Mahajan(Long id, String name, String phone, String address, Double balance, String panNumber,
			String gstNumber) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.balance = balance;
		this.panNumber = panNumber;
		this.gstNumber = gstNumber;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //1

    private String name; //SK Traders

    private String phone; //706754367

    private String address; //Patwatoli

    private Double balance; //50000
    
    private String panNumber;
    
    private String gstNumber;
    
    public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

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