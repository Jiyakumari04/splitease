package com.Splitease.splitease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="expenses")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name="paid_by")
    private User paidBy;

    private BigDecimal amount;
    private String description;
    private String category;

    @OneToMany(mappedBy = "expense", cascade= CascadeType.ALL)
    private List<ExpenseSplit> splits;
}
