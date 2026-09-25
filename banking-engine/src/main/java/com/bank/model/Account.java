package com.bank.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class Account {
    private final String accountNumber;
    private final String accountName;
    protected double balance;
    private final List<Transaction> transactionHistory;

    public Account(String accountNumber, String accountName, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Intial balance cannot be negative");
        }
        this.balance = initialDeposit;

        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be empty.");
        }
        this.accountNumber = accountNumber;

        if (accountName == null || accountName.isBlank()) {
            throw new IllegalArgumentException("Account holder name cannot be empty.");
        }
        this.accountName = accountName;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount should be greater than zero.");
        }
        this.balance += amount;
        Transaction tx = new Transaction(UUID.randomUUID().toString(), TransactionType.DEPOSIT, amount, this.balance, LocalDateTime.now());
        this.transactionHistory.add(tx);
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract void withdraw(double amount);

    public List<Transaction> getTransactionHistory(){
        return Collections.unmodifiableList(this.transactionHistory);
    }
}
