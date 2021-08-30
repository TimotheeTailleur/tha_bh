package com.bh.tha.respositories.customers;

import com.bh.tha.domain.customers.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c LEFT JOIN FETCH c.accounts")
    List<Customer> getAll();
}
