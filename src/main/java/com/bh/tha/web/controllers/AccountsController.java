package com.bh.tha.web.controllers;

import com.bh.tha.api.AccountsApi;
import com.bh.tha.dto.Account;
import com.bh.tha.dto.AccountCreationDTO;
import com.bh.tha.dto.DetailedAccount;
import com.bh.tha.mappers.accounts.AccountMapper;
import com.bh.tha.services.accounts.AccountsService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;


import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class AccountsController implements AccountsApi {

    private AccountsService accountsService;

    private AccountMapper accountMapper;

    @Autowired
    public AccountsController(AccountsService accountsService, AccountMapper accountMapper) {
        this.accountsService = accountsService;
        this.accountMapper = accountMapper;
    }

    @Override
    public ResponseEntity<Account> createAccount(@Valid AccountCreationDTO accountCreationDTO) {
        try {
            Account createdAccount = accountMapper.toDto(accountsService.createAccount(accountCreationDTO));
            return ResponseEntity.ok(createdAccount);
        } catch(NotFoundException e3) {
            throw new ResponseStatusException(NOT_FOUND, e3.getMessage());
        }catch (IllegalArgumentException e1) {
            throw new ResponseStatusException(BAD_REQUEST, e1.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<DetailedAccount>> findAccounts(@Min(1L) @Valid Long customerId) {
        try {
            List<DetailedAccount> accountList = accountMapper.toDetailedDtoList(accountsService.findAccounts(customerId));
            return ResponseEntity.ok(accountList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
