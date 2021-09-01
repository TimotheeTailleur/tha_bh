package com.bh.tha.services.transactions;


import com.bh.tha.domain.accounts.Account;
import com.bh.tha.dto.TransactionCreationDTO;
import com.bh.tha.respositories.accounts.AccountsRepository;
import com.bh.tha.services.transactions.impl.TransactionsServiceImpl;
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
class TransactionsServiceUnitTests {

    @InjectMocks
    private TransactionsServiceImpl transactionsService;

    @Mock
    private AccountsRepository accountsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreationWithoutAmount() {
        //Given
        TransactionCreationDTO transactionCreationDTO = new TransactionCreationDTO();
        transactionCreationDTO.setAmount(null);

        //When
        assertThrows(IllegalArgumentException.class, () -> transactionsService.createTransaction(transactionCreationDTO));
    }

    @Test
    public void testCreationWithoutCustomerId() {
        //Given
        TransactionCreationDTO transactionCreationDTO = new TransactionCreationDTO();
        transactionCreationDTO.setAmount(1.0D);
        transactionCreationDTO.setAccountId(null);

        //When
        assertThrows(IllegalArgumentException.class, () -> transactionsService.createTransaction(transactionCreationDTO));
    }

    @Test
    void getTransactionsForAccountNotFound() {
        //Given
        Long accountId = 1L;

        Optional<Account> optionalAccount = Optional.empty();

        //When
        Mockito.when(accountsRepository.findById(accountId)).thenReturn(optionalAccount);
        assertThrows(NotFoundException.class, () -> transactionsService.getTransactionsForAccount(accountId));
    }
}