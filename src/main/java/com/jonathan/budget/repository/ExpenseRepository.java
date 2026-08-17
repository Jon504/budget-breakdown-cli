package com.jonathan.budget.repository;

import com.jonathan.budget.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategoryIgnoreCase(String category);

    List<Expense> findByNameContainingIgnoreCase(String name);
}