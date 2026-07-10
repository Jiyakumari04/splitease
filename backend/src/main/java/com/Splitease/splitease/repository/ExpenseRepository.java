package com.Splitease.splitease.repository;

import com.Splitease.splitease.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByGroup_Id(Long groupId);
}
