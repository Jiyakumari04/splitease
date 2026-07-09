package com.Splitease.splitease.dto;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class SettlementRequest {
    private long fromUserId;
    private Long toUserId;
    private BigDecimal amount;
}

//what about the JwtAuthFilter in config folder, GroupControllr in controller folder, AuthRequest, BalanceResponse, ExpenseRequest in dto folder, ExpenseRepository ExpenseSplitRepository GroupRepository UserRepository in repository folder. these all are empty and you didint mention these files and codemin html folder why
