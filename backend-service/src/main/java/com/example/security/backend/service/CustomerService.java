package com.example.security.backend.service;

import com.example.security.backend.exception.RecordNotFoundException;
import com.example.security.backend.model.Customer;
import com.example.security.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {

        List<Customer> customerList = customerRepository.findAll();

        if(customerList.size() > 0) {
            return customerList;
        }
        else {
            return new ArrayList<>();
        }
    }

    public Customer getCustomerById(Long id) throws RecordNotFoundException {

        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()) {
            return customer.get();
        }
        else {
            throw new RecordNotFoundException("No customer record exist for given id");
        }
    }

    public Customer createOrUpdateEmployee(Customer entity) throws RecordNotFoundException
    {
        Optional<Customer> employee = customerRepository.findById(entity.getId());

        if(employee.isPresent())
        {
            Customer newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());

            newEntity = customerRepository.save(newEntity);

            return newEntity;
        } else {
            entity = customerRepository.save(entity);

            return entity;
        }
    }

    public void deleteCustomerById(Long id) throws RecordNotFoundException
    {
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent())
        {
            customerRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No customer record exist for given id");
        }
    }

}
