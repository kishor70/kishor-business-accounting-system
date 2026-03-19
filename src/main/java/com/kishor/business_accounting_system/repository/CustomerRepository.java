package com.kishor.business_accounting_system.repository;

import com.kishor.business_accounting_system.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository 
        extends JpaRepository<Customer, Long> {

}