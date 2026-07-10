package com.Splitease.splitease.dto;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class SettlementRequest {
    private long fromUserId;
    private Long toUserId;
    private BigDecimal amount;
}

