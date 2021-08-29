package com.bh.tha.services.customers;

import com.bh.tha.domain.customers.Customer;
import javassist.NotFoundException;

import java.util.List;

public interface CustomersService {

    Customer getCustomerById(Long customerId) throws NotFoundException;

    List<Customer> getAll();
}
