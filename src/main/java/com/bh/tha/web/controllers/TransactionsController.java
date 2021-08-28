package com.bh.tha.web.controllers;

import com.bh.tha.api.TransactionsApi;
import com.bh.tha.dto.Transaction;
import com.bh.tha.dto.TransactionCreationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class TransactionsController implements TransactionsApi {

    @Override
    public ResponseEntity<Transaction> createTransaction(@Valid TransactionCreationDTO transactionCreationDTO) {
        return null;
    }

    @Override
    public ResponseEntity<List<Transaction>> getTransactions(@NotNull @Min(1L) @Valid Long accountId) {
        return null;
    }
}
