package com.jonathan.budget.repository;

import com.jonathan.budget.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}