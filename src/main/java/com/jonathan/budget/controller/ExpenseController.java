package com.jonathan.budget.controller;

import com.jonathan.budget.Expense;
import com.jonathan.budget.service.ExpenseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @PostMapping
    public Expense createExpense(@Valid @RequestBody Expense expense) {
        return expenseService.createExpense(expense);
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
    return expenseService.getExpenseById(id);
}

@PutMapping("/{id}")
public Expense updateExpense(
        @PathVariable Long id,
        @Valid @RequestBody Expense expense) {

    return expenseService.updateExpense(id, expense);
}

@DeleteMapping("/{id}")
public void deleteExpense(@PathVariable Long id) {
    expenseService.deleteExpense(id);
}

@GetMapping("/total")
public double getTotalSpending() {
    return expenseService.getTotalSpending();
}

@GetMapping("/category/{category}")
public List<Expense> getExpensesByCategory(@PathVariable String category) {
    return expenseService.getExpensesByCategory(category);
}

@GetMapping("/search")
public List<Expense> searchExpensesByName(@RequestParam String name) {
    return expenseService.searchExpensesByName(name);
}

}