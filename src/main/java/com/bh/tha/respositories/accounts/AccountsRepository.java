package com.bh.tha.respositories.accounts;

import com.bh.tha.domain.accounts.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountsRepository extends CrudRepository<Account, Long> {

    @Query("select a from Account a left join fetch a.transactions")
    List<Account> getAll();

}
