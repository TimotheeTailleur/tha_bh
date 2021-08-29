package com.bh.tha.mappers.accounts;

import com.bh.tha.dto.Account;
import com.bh.tha.mappers.transactions.TransactionMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {TransactionMapper.class})
public interface AccountMapper {

    Account toDto(com.bh.tha.domain.accounts.Account account);

    com.bh.tha.domain.accounts.Account toEntity(Account account);
}
