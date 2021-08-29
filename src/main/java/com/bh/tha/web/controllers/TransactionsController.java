package com.bh.tha.web.controllers;

import com.bh.tha.api.TransactionsApi;
import com.bh.tha.dto.Transaction;
import com.bh.tha.dto.TransactionCreationDTO;
import com.bh.tha.mappers.transactions.TransactionMapper;
import com.bh.tha.services.transactions.TransactionsService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class TransactionsController implements TransactionsApi {

    private TransactionMapper transactionMapper;

    private TransactionsService transactionsService;

    @Autowired
    public TransactionsController(TransactionMapper transactionMapper, TransactionsService transactionsService) {
        this.transactionMapper = transactionMapper;
        this.transactionsService = transactionsService;
    }

    @Override
    public ResponseEntity<Transaction> createTransaction(@Valid TransactionCreationDTO transactionCreationDTO) {
        try {
            Transaction createdTransaction = transactionMapper.toDto(transactionsService.createTransaction(transactionCreationDTO));
            return ResponseEntity.ok(createdTransaction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Transaction>> getTransactions(@NotNull @Min(1L) @Valid Long accountId) {
        try {
            List<Transaction> transactionList = transactionMapper.toDtoList(transactionsService.getTransactionsForAccount(accountId));
            return ResponseEntity.ok(transactionList);
        } catch (NotFoundException e1) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }
}
