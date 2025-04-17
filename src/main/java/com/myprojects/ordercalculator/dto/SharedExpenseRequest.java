package com.myprojects.ordercalculator.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SharedExpenseRequest {
    private String name;
    private BigDecimal amount;
}