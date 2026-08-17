package com.jonathan.budget.service;

import com.jonathan.budget.Expense;
import com.jonathan.budget.exception.ExpenseNotFoundException;
import com.jonathan.budget.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseServiceTest {

    private ExpenseRepository expenseRepository;
    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        expenseRepository = Mockito.mock(ExpenseRepository.class);
        expenseService = new ExpenseService(expenseRepository);
    }

    @Test
    void getAllExpensesReturnsRepositoryData() {
        Expense expense = new Expense(
                "Coffee",
                5.50,
                "Food",
                LocalDate.of(2026, 8, 16)
        );

        Mockito.when(expenseRepository.findAll())
                .thenReturn(List.of(expense));

        List<Expense> result = expenseService.getAllExpenses();

        assertEquals(1, result.size());
        assertEquals("Coffee", result.get(0).getName());
    }

    @Test
    void getTotalSpendingReturnsCorrectSum() {
        Expense coffee = new Expense(
                "Coffee",
                5.50,
                "Food",
                LocalDate.of(2026, 8, 16)
        );

        Expense gas = new Expense(
                "Gas",
                40.00,
                "Transportation",
                LocalDate.of(2026, 8, 16)
        );

        Mockito.when(expenseRepository.findAll())
                .thenReturn(List.of(coffee, gas));

        double total = expenseService.getTotalSpending();

        assertEquals(45.50, total, 0.001);
    }

    @Test
    void getExpenseByIdThrowsWhenExpenseDoesNotExist() {
        Mockito.when(expenseRepository.findById(9999L))
                .thenReturn(Optional.empty());

        assertThrows(
                ExpenseNotFoundException.class,
                () -> expenseService.getExpenseById(9999L)
        );
    }
}