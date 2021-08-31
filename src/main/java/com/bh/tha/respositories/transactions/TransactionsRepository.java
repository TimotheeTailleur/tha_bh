package com.bh.tha.respositories.transactions;

import com.bh.tha.domain.transactions.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionsRepository extends CrudRepository<Transaction, Long> {
}
