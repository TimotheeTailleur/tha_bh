package com.bh.tha.web.controllers;

import com.bh.tha.api.CustomersApi;
import com.bh.tha.dto.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
public class CustomersController implements CustomersApi {

    @Override
    public ResponseEntity<Customer> getCustomer(@Min(1L) Long id) {
        return null;
    }
}
