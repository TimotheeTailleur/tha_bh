package com.bh.tha.mappers;

import com.bh.tha.domain.transactions.Transaction;
import com.bh.tha.mappers.transactions.TransactionMapper;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-29T15:16:15+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public com.bh.tha.dto.Transaction toDto(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        com.bh.tha.dto.Transaction transaction1 = new com.bh.tha.dto.Transaction();

        transaction1.setDate( transaction.getDate() );
        transaction1.setAmount( transaction.getAmount() );

        return transaction1;
    }

    @Override
    public Transaction toEntity(com.bh.tha.dto.Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        Transaction transaction1 = new Transaction();

        transaction1.setDate( transaction.getDate() );
        transaction1.setAmount( transaction.getAmount() );

        return transaction1;
    }
}
