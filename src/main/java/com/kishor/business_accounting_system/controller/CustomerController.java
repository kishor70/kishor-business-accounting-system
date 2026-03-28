package com.kishor.business_accounting_system.controller;

import com.kishor.business_accounting_system.entity.Customer;
import com.kishor.business_accounting_system.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer saved = customerService.saveCustomer(customer);

        return ResponseEntity
                .status(201)
                .header("info", "Customer created successfully")
                .body(saved);
    }

    // ✅ READ ALL
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> list = customerService.getAllCustomers();

        if(list.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.ok(list);
    }

    // ✅ READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        try {
            Customer customer = customerService.getCustomerById(id);

            return ResponseEntity
                    .ok()
                    .header("info", "Customer fetched successfully")
                    .body(customer);

        } catch (RuntimeException e){
            return ResponseEntity
                    .status(404)
                    .header("error", "Customer not found")
                    .build();
        }
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,
                                                   @RequestBody Customer customer){
        try {
            Customer updated = customerService.updateCustomer(id, customer);

            return ResponseEntity
                    .ok()
                    .header("info", "Customer updated successfully")
                    .body(updated);

        } catch (RuntimeException e){
            return ResponseEntity
                    .status(404)
                    .header("error", "Customer not found for update")
                    .build();
        }
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        try {
            customerService.deleteCustomer(id);

            return ResponseEntity
                    .noContent()
                    .header("info", "Customer deleted successfully")
                    .build();

        } catch (RuntimeException e){
            return ResponseEntity
                    .status(404)
                    .header("error", "Customer not found for delete")
                    .build();
        }
    }
}