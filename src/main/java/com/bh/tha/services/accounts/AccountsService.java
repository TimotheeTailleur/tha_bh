package com.bh.tha.services.accounts;

import com.bh.tha.domain.accounts.Account;
import com.bh.tha.dto.AccountCreationDTO;
import javassist.NotFoundException;

import java.util.List;

public interface AccountsService {

    Account createAccount(AccountCreationDTO dto) throws NotFoundException;

    List<Account> getAccountsForCustomer(Long customerId) throws NotFoundException;

    List<Account> getAll();
}
