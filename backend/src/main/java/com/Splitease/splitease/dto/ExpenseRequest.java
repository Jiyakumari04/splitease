package com.Splitease.splitease.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ExpenseRequest {
    private Long paidBy;
    private BigDecimal amount;
    private String description;
    private String category;
    private Map<Long, BigDecimal> splits;

}
