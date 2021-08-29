package com.bh.tha.mappers;

import com.bh.tha.domain.accounts.Account;
import com.bh.tha.domain.customers.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import com.bh.tha.mappers.accounts.AccountMapper;
import com.bh.tha.mappers.customers.CustomerMapper;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-29T15:16:14+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
public class CustomerMapperImpl implements CustomerMapper {

    private final AccountMapper accountMapper = Mappers.getMapper( AccountMapper.class );

    @Override
    public com.bh.tha.dto.Customer toDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        com.bh.tha.dto.Customer customer1 = new com.bh.tha.dto.Customer();

        customer1.setName( customer.getName() );
        customer1.setSurname( customer.getSurname() );
        customer1.setAccounts( accountListToAccountList( customer.getAccounts() ) );

        return customer1;
    }

    @Override
    public Customer toEntity(com.bh.tha.dto.Customer customer) {
        if ( customer == null ) {
            return null;
        }

        Customer customer1 = new Customer();

        customer1.setName( customer.getName() );
        customer1.setSurname( customer.getSurname() );
        customer1.setAccounts( accountListToAccountList1( customer.getAccounts() ) );

        return customer1;
    }

    protected List<com.bh.tha.dto.Account> accountListToAccountList(List<Account> list) {
        if ( list == null ) {
            return null;
        }

        List<com.bh.tha.dto.Account> list1 = new ArrayList<com.bh.tha.dto.Account>( list.size() );
        for ( Account account : list ) {
            list1.add( accountMapper.toDto( account ) );
        }

        return list1;
    }

    protected List<Account> accountListToAccountList1(List<com.bh.tha.dto.Account> list) {
        if ( list == null ) {
            return null;
        }

        List<Account> list1 = new ArrayList<Account>( list.size() );
        for ( com.bh.tha.dto.Account account : list ) {
            list1.add( accountMapper.toEntity( account ) );
        }

        return list1;
    }
}
