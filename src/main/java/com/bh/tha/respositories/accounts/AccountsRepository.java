package com.bh.tha.respositories.accounts;

import com.bh.tha.domain.accounts.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository extends CrudRepository<Account, Long> {

    @Query("select a from Account a left join fetch a.transactions")
    List<Account> getAll();

    @Query("select a from Account a left join fetch a.transactions where a.id = :id")
    Optional<Account> findByIdAndFetchTransactions(@Param(value = "id") Long id);
}
