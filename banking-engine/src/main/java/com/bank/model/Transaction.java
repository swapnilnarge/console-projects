package com.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(
        String id,
        TransactionType type,
        BigDecimal amount,
        BigDecimal resultingBalance,
        LocalDateTime timestamp
) {
}
