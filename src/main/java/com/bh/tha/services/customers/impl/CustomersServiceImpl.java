package com.bh.tha.services.customers.impl;

import com.bh.tha.domain.accounts.Account;
import com.bh.tha.domain.customers.Customer;
import com.bh.tha.respositories.customers.CustomersRepository;
import com.bh.tha.services.customers.CustomersService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CustomersServiceImpl implements CustomersService {

    private CustomersRepository customersRepository;

    @Autowired
    public CustomersServiceImpl(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @Override
    public Customer getCustomerById(Long customerId) throws NotFoundException {

        if (customerId == null) {
            throw new IllegalArgumentException("Bad Request");
        }

        Optional<Customer> customer = customersRepository.findById(customerId);

        if (!customer.isPresent()) {
            throw new NotFoundException(String.format("Customer with id %s not found", customerId));
        }

        return customer.get();
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = customersRepository.getAllAndFetchAccounts();
        if (!CollectionUtils.isEmpty(customers)) {
            for (Customer customer : customers) {
                if (!CollectionUtils.isEmpty(customer.getAccounts())) {
                    customer.getAccounts().forEach(Account::getTransactions);
                }
            }
        }
        return customers;
    }
}
