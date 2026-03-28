package com.kishor.business_accounting_system.service;

import com.kishor.business_accounting_system.entity.Customer;
import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);
}