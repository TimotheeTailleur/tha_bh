package com.bh.tha.services.accounts;

import com.bh.tha.domain.accounts.Account;
import com.bh.tha.dto.AccountCreationDTO;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface AccountsService {

    Account createAccount(AccountCreationDTO dto) throws NotFoundException;

    List<Account> findAccounts(Long customerId) throws NotFoundException;

    List<Account> getAll();

    Optional<Account> findByIdAndFetchTransactions(Long id);
}
