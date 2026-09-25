package com.bank.model;

import java.time.LocalDateTime;

public record Transaction(
        String id,
        TransactionType type,
        double amount,
        double resultingBalance,
        LocalDateTime timestamp
) {
}
