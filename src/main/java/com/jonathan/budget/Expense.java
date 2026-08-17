package com.jonathan.budget;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Positive
    private double amount;

    @NotBlank
    private String category;

    @NotNull
    private LocalDate date;

    protected Expense() {
    }

    public Expense(String name, double amount, String category, LocalDate date) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setName(String name) {
    this.name = name;
}

public void setAmount(double amount) {
    this.amount = amount;
}

public void setCategory(String category) {
    this.category = category;
}

public void setDate(LocalDate date) {
    this.date = date;
}

    @Override
    public String toString() {
        return date + " | " + name + " | " + category
                + " | $" + String.format("%.2f", amount);
    }
}