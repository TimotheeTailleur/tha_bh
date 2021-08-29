package com.bh.tha.mappers.transactions;

import com.bh.tha.dto.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TransactionMapper {

    Transaction toDto(com.bh.tha.domain.transactions.Transaction transaction);

    com.bh.tha.domain.transactions.Transaction toEntity(Transaction transaction);

    List<Transaction> toDtoList(List<com.bh.tha.domain.transactions.Transaction> transactionList);
}
