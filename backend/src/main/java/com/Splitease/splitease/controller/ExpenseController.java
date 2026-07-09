package com.Splitease.splitease.controller;

import com.Splitease.splitease.dto.BalanceResponse;
import com.Splitease.splitease.dto.ExpenseRequest;
import com.Splitease.splitease.service.BalanceService;
import com.Splitease.splitease.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups/{groupId}/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;
    private final BalanceService balanceService;

    @PostMapping
    public ResponseEntity<?> addExpense(@PathVariable Long groupId,
                                        @RequestBody ExpenseRequest req) {
        return ResponseEntity.ok(expenseService.addExpense(groupId, req));
    }

    @GetMapping("/balances")
    public ResponseEntity<List<BalanceResponse>> getBalances(@PathVariable Long groupId) {
        return ResponseEntity.ok(balanceService.simplifyDebts(groupId));
    }
}