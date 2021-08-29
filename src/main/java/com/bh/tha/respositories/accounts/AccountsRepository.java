package com.bh.tha.respositories.accounts;

import com.bh.tha.domain.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {

    @Query("select a from Account a left join fetch a.transactions")
    List<Account> getAll();

}
