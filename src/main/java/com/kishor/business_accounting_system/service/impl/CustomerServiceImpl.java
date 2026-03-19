package com.kishor.business_accounting_system.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.kishor.business_accounting_system.entity.Customer;
import com.kishor.business_accounting_system.repository.CustomerRepository;
import com.kishor.business_accounting_system.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerrepository;
	
	public CustomerServiceImpl(CustomerRepository customerrepository) {
		this.customerrepository = customerrepository;
	}


	@Override
	public Customer saveCustomer(Customer customer) {
		
		return customerrepository.save(customer); 
	}

	@Override
	public List<Customer> getAllCustomers() {
		
		return customerrepository.findAll();
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerrepository.findById(id).orElseThrow(null);
	}
	
}