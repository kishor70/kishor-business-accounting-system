package com.kishor.business_accounting_system.service;

import java.util.List;

import com.kishor.business_accounting_system.entity.Customer;

public interface CustomerService {

	Customer saveCustomer(Customer customer);

	List<Customer> getAllCustomers();

	Customer getCustomerById(Long id);
}