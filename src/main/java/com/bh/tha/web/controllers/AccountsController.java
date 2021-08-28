package com.bh.tha.web.controllers;

import com.bh.tha.api.AccountsApi;
import com.bh.tha.dto.Account;
import com.bh.tha.dto.AccountCreationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class AccountsController implements AccountsApi {

    @Override
    public ResponseEntity<Account> createAccount(@Valid AccountCreationDTO accountCreationDTO) {
        return null;
    }

    @Override
    public ResponseEntity<List<Account>> getAccounts(@NotNull @Min(1L) @Valid Long customerId) {
        return null;
    }
}
