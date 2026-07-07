package com.Splitease.splitease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="expense_splits")
@Data @NoArgsConstructor @AllArgsConstructor
public class ExpenseSplit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="expense_id")
    private Expense expense;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private BigDecimal amountOwed;
}


