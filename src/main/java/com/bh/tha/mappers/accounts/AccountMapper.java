package com.bh.tha.mappers.accounts;

import com.bh.tha.dto.Account;
import com.bh.tha.dto.DetailedAccount;
import com.bh.tha.mappers.customers.CustomerMapper;
import com.bh.tha.mappers.transactions.TransactionMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, TransactionMapper.class})
public interface AccountMapper {

    Account toDto(com.bh.tha.domain.accounts.Account account);

    DetailedAccount toDetailedDto(com.bh.tha.domain.accounts.Account account);

    com.bh.tha.domain.accounts.Account toEntity(Account account);

    List<Account> toDtoList(List<com.bh.tha.domain.accounts.Account> accountList);

    List<DetailedAccount> toDetailedDtoList(List<com.bh.tha.domain.accounts.Account> accountList);
}
