package com.bh.tha.respositories.customers;

import com.bh.tha.domain.customers.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customer, Long> {

    @Query("select c from Customer c LEFT JOIN FETCH c.accounts")
    List<Customer> getAllAndFetchAccounts();
}
