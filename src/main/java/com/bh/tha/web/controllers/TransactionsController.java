package com.bh.tha.web.controllers;

import com.bh.tha.api.TransactionsApi;
import com.bh.tha.dto.DetailedTransaction;
import com.bh.tha.dto.Transaction;
import com.bh.tha.dto.TransactionCreationDTO;
import com.bh.tha.mappers.transactions.TransactionMapper;
import com.bh.tha.services.transactions.TransactionsService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

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
        } catch (NotFoundException e1) {
            throw new ResponseStatusException(NOT_FOUND, e1.getMessage());
        } catch (IllegalArgumentException e2) {
            throw new ResponseStatusException(BAD_REQUEST, e2.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<DetailedTransaction>> findTransactions(@Min(1L) @Valid Long accountId) {
        try {
            List<DetailedTransaction> transactionList = transactionMapper.toDetailedDtoList(transactionsService.findTransactions(accountId));
            return ResponseEntity.ok(transactionList);
        } catch (NotFoundException e1) {
            throw new ResponseStatusException(NOT_FOUND, e1.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
