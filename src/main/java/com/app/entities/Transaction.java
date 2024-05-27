package com.app.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transaction {
    private int transactionId;
    private int accountId;
    private TransactionType transactionType;
    private double amount;
    private Date transactionDate;
    private String description;
}
