package com.bh.tha.services.accounts;

import com.bh.tha.domain.accounts.Account;
import com.bh.tha.dto.AccountCreationDTO;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountsServiceIntegrationTests {

    @Autowired
    private AccountsService accountsService;

    @Test
    public void testAccountCreation() {
        //Given
        Long customerId = 1L;
        Double initialCredit = 1.59D;
        AccountCreationDTO accountCreationDTO = new AccountCreationDTO();
        accountCreationDTO.setCustomerId(1L);
        accountCreationDTO.setInitialCredit(1.59D);

        //WHen
        try {
            Account account = accountsService.createAccount(accountCreationDTO);
            Optional<Account> fetchedAccount = accountsService.findByIdAndFetchTransactions(account.getId());
            assertTrue(fetchedAccount.isPresent());
            assertEquals(customerId, fetchedAccount.get().getCustomerId());
            assertEquals(initialCredit, fetchedAccount.get().getTransactions().stream().findFirst().get().getAmount());
        } catch (NotFoundException notFoundException) {
            fail("There was an error retrieving the customer from the database");
        }
    }
}
