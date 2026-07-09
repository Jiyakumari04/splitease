package com.Splitease.splitease.service;

import com.Splitease.splitease.dto.BalanceResponse;
import com.Splitease.splitease.model.Expense;
import com.Splitease.splitease.model.ExpenseSplit;
import com.Splitease.splitease.model.Settlement;
import com.Splitease.splitease.repository.ExpenseRepository;
import com.Splitease.splitease.repository.SettlementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final ExpenseRepository expenseRepository;
    private final SettlementRepository settlementRepository;

    public List<BalanceResponse> simplifyDebts(Long groupId){
        Map<Long, BigDecimal> netBalance = computeNetBalances(groupId);
        PriorityQueue<Map.Entry<Long, BigDecimal>> creditors = new PriorityQueue<>((a, b) -> b.getValue().compareTo(a.getValue()));
        PriorityQueue<Map.Entry<Long, BigDecimal>> debtors= new PriorityQueue<>((a, b) -> a.getValue().compareTo(b.getValue()));

        for(var entry : netBalance.entrySet() ){
            if(entry.getValue().compareTo(BigDecimal.ZERO) > 0) creditors.add(entry);
            else if(entry.getValue().compareTo(BigDecimal.ZERO) < 0) debtors.add(entry);
        }
        List<BalanceResponse> transactions = new ArrayList<>();
        while(!creditors.isEmpty() && !debtors.isEmpty()){
            var creditor= creditors.poll();
            var debtor= debtors.poll();

            BigDecimal settleAmount= creditor.getValue().min(debtor.getValue().abs());
            transactions.add(new BalanceResponse(debtor.getKey(), creditor.getKey(), settleAmount));

            BigDecimal remainingCredit = creditor.getValue().subtract(settleAmount);
            BigDecimal remainingDebt = debtor.getValue().add(settleAmount);

            if (remainingCredit.compareTo(BigDecimal.ZERO) > 0)
                creditors.add(Map.entry(creditor.getKey(), remainingCredit));
            if (remainingDebt.compareTo(BigDecimal.ZERO) < 0)
                debtors.add(Map.entry(debtor.getKey(), remainingDebt));
        }
        return transactions;
    }
    private Map<Long, BigDecimal> computeNetBalances(Long groupId) {
        Map<Long, BigDecimal> net = new HashMap<>();
        List<Expense> expenses = expenseRepository.findByGroupId(groupId);
        for (Expense e : expenses) {
            net.merge(e.getPaidBy().getId(), e.getAmount(), BigDecimal::add);
            for (ExpenseSplit split : e.getSplits()) {
                net.merge(split.getUser().getId(), split.getAmountOwed().negate(), BigDecimal::add);
            }
        }
        List<Settlement> settlements = settlementRepository.findByGroupId(groupId);
        for (Settlement s : settlements) {
            net.merge(s.getFromUser().getId(), s.getAmount(), BigDecimal::add);
            net.merge(s.getToUser().getId(), s.getAmount().negate(), BigDecimal::add);
        }
        return net;
    }
}
//dept simplifications lives here