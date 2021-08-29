package com.bh.tha.mappers.customers;

import com.bh.tha.dto.Customer;
import com.bh.tha.mappers.accounts.AccountMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {AccountMapper.class})
public interface CustomerMapper {

    Customer toDto(com.bh.tha.domain.customers.Customer customer);

    com.bh.tha.domain.customers.Customer toEntity(Customer customer);
}
