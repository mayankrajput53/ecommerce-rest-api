package com.example.security.backend.controller;

import com.example.security.backend.exception.RecordNotFoundException;
import com.example.security.backend.model.Customer;
import com.example.security.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

//    Autowire the CustomerService

    @Autowired
    private CustomerService customerService;

//    Add mapping for GET / customers

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {

        List<Customer> list = customerService.getAllCustomers();

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long customerId) throws RecordNotFoundException {

        Customer customer = customerService.getCustomerById(customerId);

        return new ResponseEntity<>(customer, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> createOrUpdateEmployee(@RequestBody Customer theCustomer) throws RecordNotFoundException{

        theCustomer.setId((long) 0);

        Customer customer = customerService.createOrUpdateEmployee(theCustomer);

        return new ResponseEntity<>(customer, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteCustomerById(@PathVariable("id") Long id) throws RecordNotFoundException {

        customerService.deleteCustomerById(id);

        return HttpStatus.FORBIDDEN;
    }

}
