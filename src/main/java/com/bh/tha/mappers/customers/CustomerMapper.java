package com.bh.tha.mappers.customers;

import com.bh.tha.dto.Account;
import com.bh.tha.dto.Customer;
import com.bh.tha.dto.Transaction;
import com.bh.tha.mappers.accounts.AccountMapper;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface CustomerMapper {

    Customer toDto(com.bh.tha.domain.customers.Customer customer);

    com.bh.tha.domain.customers.Customer toEntity(Customer customer);

    @AfterMapping
    default void populateBalance(@MappingTarget Customer customer) {
        Double customerBalance = 0.0D;
        if (!CollectionUtils.isEmpty(customer.getAccounts())) {
            for (Account account : customer.getAccounts()) {
                for (Transaction transaction : account.getTransactions()) {
                    customerBalance+=transaction.getAmount();
                }
            }
        }
        customer.setBalance(customerBalance);
    }
}
