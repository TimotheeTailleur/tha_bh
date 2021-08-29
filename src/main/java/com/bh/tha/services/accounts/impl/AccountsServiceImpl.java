package com.bh.tha.services.accounts.impl;

import com.bh.tha.domain.accounts.Account;
import com.bh.tha.domain.customers.Customer;
import com.bh.tha.dto.AccountCreationDTO;
import com.bh.tha.dto.TransactionCreationDTO;
import com.bh.tha.respositories.accounts.AccountsRepository;
import com.bh.tha.respositories.customers.CustomersRepository;
import com.bh.tha.services.accounts.AccountsService;
import com.bh.tha.services.transactions.TransactionsService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountsServiceImpl implements AccountsService {

    private CustomersRepository customersRepository;

    private AccountsRepository accountsRepository;

    private TransactionsService transactionsService;

    @Autowired
    public AccountsServiceImpl(CustomersRepository customersRepository, AccountsRepository accountsRepository, TransactionsService transactionsService) {
        this.customersRepository = customersRepository;
        this.accountsRepository = accountsRepository;
        this.transactionsService = transactionsService;
    }

    @Override
    @Transactional
    public Account createAccount(AccountCreationDTO dto) throws NotFoundException {
        if (dto.getCustomerId() == null) {
            throw new IllegalArgumentException("Bad request");
        }

        if (!customersRepository.existsById(dto.getCustomerId())) {
            throw new NotFoundException(String.format("Customer with id %s not found", dto.getCustomerId()));
        }

        Account account = new Account();
        account.setCustomerId(dto.getCustomerId());
        account = accountsRepository.saveAndFlush(account);

        if (dto.getInitialCredit() != null && !dto.getInitialCredit().equals(0D)) {
            TransactionCreationDTO transactionCreationDTO = new TransactionCreationDTO();
            transactionCreationDTO.setAccountId(account.getId());
            transactionCreationDTO.setAmount(dto.getInitialCredit());
            try {
                transactionsService.createTransaction(transactionCreationDTO);
            } catch (NotFoundException notFoundException) {
                notFoundException.printStackTrace();
            }
        }
        return accountsRepository.getById(account.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getAccountsForCustomer(Long customerId) throws NotFoundException {
        if (customerId == null) {
            throw new IllegalArgumentException("Bad request");
        }

        Optional<Customer> customer = customersRepository.findById(customerId);

        if (!customer.isPresent()) {
            throw new NotFoundException(String.format("Could not find account with id : %s", customerId));
        }

        List<Account> accounts = customer.get().getAccounts();
        accounts.forEach(Account::getTransactions);

        return accounts;
    }

    @Override
    public List<Account> getAll() {
        return accountsRepository.getAll();
    }
}
