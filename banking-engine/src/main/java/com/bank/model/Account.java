package com.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class Account {
    private final String accountNumber;
    private final String accountName;
    protected BigDecimal balance;
    private final List<Transaction> transactionHistory;

    public Account(String accountNumber, String accountName, BigDecimal initialDeposit) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be empty.");
        }
        if (accountName == null || accountName.isBlank()) {
            throw new IllegalArgumentException("Account holder name cannot be empty.");
        }
        if (initialDeposit == null || initialDeposit.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative or null.");
        }
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
        this.accountName = accountName;
        this.transactionHistory = new ArrayList<>();

        if (initialDeposit.compareTo(BigDecimal.ZERO) > 0) {
            Transaction tx = new Transaction(UUID.randomUUID().toString(), TransactionType.DEPOSIT, initialDeposit, this.balance, LocalDateTime.now());
            this.transactionHistory.add(tx);
        }

    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount should be greater than zero.");
        }
        this.balance = this.balance.add(amount);
        Transaction tx = new Transaction(UUID.randomUUID().toString(), TransactionType.DEPOSIT, amount, this.balance, LocalDateTime.now());
        this.transactionHistory.add(tx);
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public abstract void withdraw(BigDecimal amount);

    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(this.transactionHistory);
    }
}
