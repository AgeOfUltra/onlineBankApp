package com.app.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    private int aid;
    private int userId;
    private String accountNumber;
    private String accountType;
    private double balance;
}
