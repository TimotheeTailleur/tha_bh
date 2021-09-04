package com.bh.tha.services.transactions;

import com.bh.tha.domain.transactions.Transaction;
import com.bh.tha.dto.TransactionCreationDTO;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
public class TransactionsServiceIntegrationTests {

    @Autowired
    private TransactionsService transactionsService;

    @Test
    public void testTransactionCreation() {
        //Given
        Long accountId = 1L;
        Double amount = 256.450D;
        TransactionCreationDTO transactionCreationDTO = new TransactionCreationDTO();
        transactionCreationDTO.setAmount(amount);
        transactionCreationDTO.setAccountId(accountId);

        try {
            //When
            Transaction transaction = transactionsService.createTransaction(transactionCreationDTO);

            //Then
            Assertions.assertEquals(accountId, transaction.getAccountId());
            Assertions.assertEquals(amount, transaction.getAmount());
        } catch (NotFoundException notFoundException) {
            fail("There was a problem recovering the account from the database");
        }
    }
}
