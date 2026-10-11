package com.bank.model;

import com.bank.exception.InsufficientFundsException;

import java.math.BigDecimal;

public class SavingsAccount extends Account {
    private final BigDecimal minimumBalance;

    public SavingsAccount(String accountNumber, String accountName, BigDecimal initialDeposit, BigDecimal minimumBalance) {
        super(accountNumber, accountName, initialDeposit);
        if (minimumBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("minimum balance cannot be negative.");
        }
        if (initialDeposit.compareTo(minimumBalance) < 0) {
            throw new IllegalArgumentException("initial deposit cannot be less than minimum balance.");
        }
        this.minimumBalance = minimumBalance;

    }


    @Override
    protected void validateWithdrawal(BigDecimal amount) {
        BigDecimal balanceAfterWithdrawal = getBalance().subtract(amount);
        if (balanceAfterWithdrawal.compareTo(minimumBalance) < 0) {
            throw new InsufficientFundsException("Withdrawal denied: Balance cannot drop below minimum balance of ₹" + minimumBalance);
        }

    }
}
