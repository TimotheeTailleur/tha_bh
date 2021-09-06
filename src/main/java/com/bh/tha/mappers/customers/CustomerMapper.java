package com.bh.tha.mappers.customers;

import com.bh.tha.dto.Account;
import com.bh.tha.dto.Customer;
import com.bh.tha.mappers.accounts.AccountMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface CustomerMapper {

    Customer toDto(com.bh.tha.domain.customers.Customer customer);

    com.bh.tha.domain.customers.Customer toEntity(Customer customer);

    List<Customer> toDtoList(List<com.bh.tha.domain.customers.Customer> customerList);

    @AfterMapping
    default void populateBalance(@MappingTarget Customer customer) {
        Double customerBalance = 0.0D;
        if (!CollectionUtils.isEmpty(customer.getAccounts())) {
            for (Account account : customer.getAccounts()) {
                customerBalance+=account.getBalance();
            }
        }
        customer.setBalance(customerBalance);
    }
}
