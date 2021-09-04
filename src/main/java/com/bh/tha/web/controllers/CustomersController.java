package com.bh.tha.web.controllers;

import com.bh.tha.api.CustomersApi;
import com.bh.tha.dto.Customer;
import com.bh.tha.mappers.customers.CustomerMapper;
import com.bh.tha.services.customers.CustomersService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class CustomersController implements CustomersApi {

    private CustomerMapper customerMapper;

    private CustomersService customersService;

    @Autowired
    public CustomersController(CustomerMapper customerMapper, CustomersService customersService) {
        this.customerMapper = customerMapper;
        this.customersService = customersService;
    }

    @Override
    public ResponseEntity<Customer> getCustomer(@Min(1L) Long id) {
        try {
            Customer customer = customerMapper.toDto(customersService.getCustomerById(id));
            return ResponseEntity.ok(customer);
        } catch (NotFoundException notFoundException) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e1) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerMapper.toDtoList(customersService.getAll()));
    }
}
