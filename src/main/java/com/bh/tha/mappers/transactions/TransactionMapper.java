package com.bh.tha.mappers.transactions;

import com.bh.tha.dto.Transaction;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionMapper {

    Transaction toDto(com.bh.tha.domain.transactions.Transaction transaction);

    com.bh.tha.domain.transactions.Transaction toEntity(Transaction transaction);
}
