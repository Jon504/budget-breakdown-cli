package com.jonathan.budget.service;

import com.jonathan.budget.Expense;
import com.jonathan.budget.exception.ExpenseNotFoundException;
import com.jonathan.budget.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import com.jonathan.budget.exception.ExpenseNotFoundException;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense createExpense(Expense expense) {
    return expenseRepository.save(expense);
    }

    public Expense getExpenseById(Long id) {
    return expenseRepository.findById(id)
            .orElseThrow(() -> new ExpenseNotFoundException(id));
    }

    public Expense updateExpense(Long id, Expense updatedExpense) {
    Expense existingExpense = getExpenseById(id);

    existingExpense.setName(updatedExpense.getName());
    existingExpense.setAmount(updatedExpense.getAmount());
    existingExpense.setCategory(updatedExpense.getCategory());
    existingExpense.setDate(updatedExpense.getDate());

    return expenseRepository.save(existingExpense);
}

public void deleteExpense(Long id) {
    Expense existingExpense = getExpenseById(id);
    expenseRepository.delete(existingExpense);
}

public double getTotalSpending() {
    return expenseRepository.findAll()
            .stream()
            .mapToDouble(Expense::getAmount)
            .sum();
}

public List<Expense> getExpensesByCategory(String category) {
    return expenseRepository.findByCategoryIgnoreCase(category);
}

public List<Expense> searchExpensesByName(String name) {
    return expenseRepository.findByNameContainingIgnoreCase(name);
}
}