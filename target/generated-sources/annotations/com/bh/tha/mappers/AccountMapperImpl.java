package com.bh.tha.mappers;

import com.bh.tha.domain.accounts.Account;
import com.bh.tha.domain.transactions.Transaction;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import com.bh.tha.mappers.accounts.AccountMapper;
import com.bh.tha.mappers.transactions.TransactionMapper;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-29T15:16:14+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
public class AccountMapperImpl implements AccountMapper {

    private final TransactionMapper transactionMapper = Mappers.getMapper( TransactionMapper.class );

    @Override
    public com.bh.tha.dto.Account toDto(Account account) {
        if ( account == null ) {
            return null;
        }

        com.bh.tha.dto.Account account1 = new com.bh.tha.dto.Account();

        if ( account.getId() != null ) {
            account1.setId( account.getId().intValue() );
        }
        account1.setTransactions( transactionListToTransactionList( account.getTransactions() ) );

        return account1;
    }

    @Override
    public Account toEntity(com.bh.tha.dto.Account account) {
        if ( account == null ) {
            return null;
        }

        Account account1 = new Account();

        if ( account.getId() != null ) {
            account1.setId( account.getId().longValue() );
        }
        account1.setTransactions( transactionListToTransactionList1( account.getTransactions() ) );

        return account1;
    }

    protected List<com.bh.tha.dto.Transaction> transactionListToTransactionList(List<Transaction> list) {
        if ( list == null ) {
            return null;
        }

        List<com.bh.tha.dto.Transaction> list1 = new ArrayList<com.bh.tha.dto.Transaction>( list.size() );
        for ( Transaction transaction : list ) {
            list1.add( transactionMapper.toDto( transaction ) );
        }

        return list1;
    }

    protected List<Transaction> transactionListToTransactionList1(List<com.bh.tha.dto.Transaction> list) {
        if ( list == null ) {
            return null;
        }

        List<Transaction> list1 = new ArrayList<Transaction>( list.size() );
        for ( com.bh.tha.dto.Transaction transaction : list ) {
            list1.add( transactionMapper.toEntity( transaction ) );
        }

        return list1;
    }
}
