package com.Splitease.splitease.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BalanceResponse {
    private Long fromUser;
    private Long toUser;
    private BigDecimal amount;
}
