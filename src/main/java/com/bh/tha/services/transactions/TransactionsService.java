package com.bh.tha.services.transactions;

import com.bh.tha.domain.transactions.Transaction;
import com.bh.tha.dto.TransactionCreationDTO;
import javassist.NotFoundException;

import java.util.List;

public interface TransactionsService {

    Transaction createTransaction(TransactionCreationDTO dto) throws NotFoundException;

    List<Transaction> getTransactionsForAccount(Long accountId) throws NotFoundException;

    List<Transaction> getALl();
}
