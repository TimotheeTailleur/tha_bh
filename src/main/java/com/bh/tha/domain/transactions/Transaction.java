package com.bh.tha.domain.transactions;

import com.bh.tha.domain.accounts.Account;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TRANSACTION_DATE")
    private OffsetDateTime date;

    @Column(name = "AMOUNT")
    private Float amount;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
