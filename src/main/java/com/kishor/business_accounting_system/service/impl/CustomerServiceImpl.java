package com.kishor.business_accounting_system.service.impl;

import com.kishor.business_accounting_system.entity.Customer;
import com.kishor.business_accounting_system.repository.CustomerRepository;
import com.kishor.business_accounting_system.service.CustomerService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

   
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }


    public Customer getCustomerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existing = getCustomerById(id);

        existing.setName(customer.getName());
        existing.setPhone(customer.getPhone());
        existing.setAddress(customer.getAddress());
        existing.setBalance(customer.getBalance());
        existing.setPanNumber(customer.getPanNumber());
        existing.setGstNumber(customer.getGstNumber());

        return repository.save(existing);
    }

    public void deleteCustomer(Long id) {
        repository.deleteById(id);
    }
}