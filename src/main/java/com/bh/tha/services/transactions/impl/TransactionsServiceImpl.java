package com.bh.tha.services.transactions.impl;

import com.bh.tha.domain.accounts.Account;
import com.bh.tha.domain.transactions.Transaction;
import com.bh.tha.dto.TransactionCreationDTO;
import com.bh.tha.respositories.accounts.AccountsRepository;
import com.bh.tha.respositories.transactions.TransactionsRepository;
import com.bh.tha.services.transactions.TransactionsService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private AccountsRepository accountsRepository;

    private TransactionsRepository transactionsRepository;

    @Autowired
    public TransactionsServiceImpl(AccountsRepository accountsRepository, TransactionsRepository transactionsRepository) {
        this.accountsRepository = accountsRepository;
        this.transactionsRepository = transactionsRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Transaction createTransaction(TransactionCreationDTO dto) {
        if (dto.getAmount() == null || dto.getAccountId() == null) {
            throw new IllegalArgumentException("Bad request");
        }

        if (!accountsRepository.existsById(dto.getAccountId())) {
            throw new IllegalArgumentException(String.format("Could not find account with id : %s", dto.getAccountId()));
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setDate(OffsetDateTime.now());
        transaction.setAccountId(dto.getAccountId());
        return transactionsRepository.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsForAccount(Long accountId) throws NotFoundException {
        if (accountId == null) {
            throw new IllegalArgumentException("Bad request");
        }
        Optional<Account> account = accountsRepository.findById(accountId);

        if (!account.isPresent()) {
            throw new NotFoundException(String.format("Could not find account with id : %s", accountId));
        }

        return account.get().getTransactions();
    }

    @Override
    public List<Transaction> getALl() {
        return transactionsRepository.findAll();
    }
}
