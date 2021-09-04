package com.bh.tha.services.accounts;

import com.bh.tha.domain.customers.Customer;
import com.bh.tha.dto.AccountCreationDTO;
import com.bh.tha.respositories.customers.CustomersRepository;
import com.bh.tha.services.accounts.impl.AccountsServiceImpl;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AccountsServiceUnitTests {

    @InjectMocks
    private AccountsServiceImpl accountsService;

    @Mock
    private CustomersRepository customersRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreationFailureWithoutCustomerId() {
        AccountCreationDTO accountCreationDTO = new AccountCreationDTO();
        accountCreationDTO.setCustomerId(null);

        assertThrows(IllegalArgumentException.class, () -> accountsService.createAccount(accountCreationDTO));
    }

    @Test
    public void testCreationCustomerNotFound() {
        AccountCreationDTO accountCreationDTO = new AccountCreationDTO();
        accountCreationDTO.setCustomerId(1L);

        Mockito.when(customersRepository.existsById(1L)).thenReturn(false);
        assertThrows(NotFoundException.class, () -> accountsService.createAccount(accountCreationDTO));
    }

    @Test
    public void testGetAccountsForCustomerNotFound() {
        //Given
        Long customerId = 1L;

        Optional<Customer> emptyCustomerOptional = Optional.empty();

        //When
        Mockito.when(customersRepository.findById(customerId)).thenReturn(emptyCustomerOptional);
        assertThrows(NotFoundException.class, () -> accountsService.findAccounts(customerId));
    }
}