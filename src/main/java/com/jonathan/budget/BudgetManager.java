package com.jonathan.budget;
import com.jonathan.budget.Expense;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;



public class BudgetManager {
    //internal list that stores all expenses !very important
    private ArrayList<Expense> expenses;

    public BudgetManager() {
        expenses = new ArrayList<>();
    }

    //list of logic methods

    //add an expense to the list
    public void addExpense(Expense expense){
        expenses.add(expense);
    }
    
    //list all expenses
    public void listExpenses(){

        if (expenses.isEmpty()){
            System.out.println("no recorded expenses yet.");
        }
        else{
            expenses.sort(Comparator.comparing(Expense::getDate)); 
            for (int i = 0; i < expenses.size(); i++){
            Expense e = expenses.get(i);
            System.out.println((i + 1) + ") " + e.toString());
        }
    }

    }

    //get total spending
    public double getTotalSpent(){
        double total = 0;
        for (Expense e : expenses){
            total += e.getAmount();
            
        }
        return total;
    }

    //view total spending 
    public void showTotalSpent() {
        double total = getTotalSpent();
        System.out.println("Total spent: $"+ String.format("%.2f", total));
    }

    //to calculate total spending for each catagory
    public double getTotalByCategory(String category) {
        double total = 0;

        for (Expense e : expenses){
            if (e.getCategory().equalsIgnoreCase(category)){
                total += e.getAmount();
            }
        }
        return total;
    }

    //method to print the category's total.
    public void showTotalByCategory(String category) {
        double total = getTotalByCategory(category);
        System.out.println("Total spent on " + category + ": $" + String.format("%.2f", total));
    }

    //method to delete an expense
    public void deleteExpense(int index) {
        if (expenses.isEmpty()){
            System.out.println("No expenses to delete.");
            return;
        }
        if(index >= 0 && index < expenses.size()){
            expenses.remove(index);
            System.out.println("Expense deleted.");
        }
        else{
            System.out.println("Invalid expense number.");
        }
    }

    public List<Expense> getExpenses() {
    return expenses;
    }

    public void setExpenses(List<Expense> newExpenses) {
        this.expenses = new ArrayList<>(newExpenses);
    }

    //Category percentage breakdown function
    //get totals for all categories
    public Map<String, Double> getCategoryTotals() {
        Map<String, Double> totals = new HashMap<>();

        for( Expense e: expenses){
            String category = e.getCategory();

            totals.put(category, totals.getOrDefault(category, 0.0) + e.getAmount());
        }
        return totals;
    }

    //logic to get percentages of all categories
    public Map<String, Double> getCategoryPercentages() {
        Map<String, Double> totals = getCategoryTotals();
        Map<String, Double> percentages = new HashMap<>();
    
        double grandTotal = getTotalSpent();
        if (grandTotal == 0) return percentages;
    
        for (Map.Entry<String, Double> entry : totals.entrySet()) {
            double percent = (entry.getValue() / grandTotal) * 100;
            percentages.put(entry.getKey(), percent);
        }
    
        return percentages;
    }

    //display category percentages
    public void showCategoryPercentages() {
        Map<String, Double> totals = getCategoryTotals();
        Map<String, Double> percentages = getCategoryPercentages();
        double grandTotal = getTotalSpent();
    
        System.out.println("\n===== Category Breakdown =====");
        
        for (String category : totals.keySet()) {
            double amount = totals.get(category);
            double percent = percentages.get(category);
    
            String line = String.format("%-12s", category); // category padded left
            line += " ................ "; // dot spacing
            line += String.format("$%.2f (%.0f%%)", amount, percent);
    
            System.out.println(line);
        }
    
        System.out.println("-------------------------------------");
        System.out.printf("Total: $%.2f%n", grandTotal);
    }
    //logic used to find and display user's monthly summary
    public void showMonthlySummary(int year, Month month) {
        Map<String, Double> totals = new HashMap<>();
        double grandTotal = 0.0;

        for (Expense e : expenses) {
            LocalDate date = e.getDate();
            if (date.getYear() == year && date.getMonth() == month) {
                String category = e.getCategory();
                double amount = e.getAmount();
                totals.put(category, totals.getOrDefault(category, 0.0) + amount);
                grandTotal += amount;
            }
        }

        System.out.println("\n===== " + month + " " + year + " Summary =====");

        if (totals.isEmpty()) {
            System.out.println("No expenses recorded in this month.");
            return;
        }

        for (String category : totals.keySet().stream().sorted().collect(Collectors.toList())) {
            double amount = totals.get(category);
            String line = String.format("%-12s", category);
            line += " ................ ";
            line += String.format("$%.2f", amount);
            System.out.println(line);
        }

        System.out.println("-------------------------------------");
        System.out.printf("Total: $%.2f%n", grandTotal);
    }

    //logic used to find and display an weekly summary from an given input
    public void showWeeklySummary(LocalDate weekStart) {
        LocalDate weekEnd = weekStart.plusDays(6);
        Map<String, Double> totals = new HashMap<>();
        double grandTotal = 0.0;

        for (Expense e : expenses) {
            LocalDate date = e.getDate();
            boolean inRange = (date.isAfter(weekStart.minusDays(1)) && date.isBefore(weekEnd.plusDays(1)));

            if (inRange) {
                String category = e.getCategory();
                double amount = e.getAmount();
                totals.put(category, totals.getOrDefault(category, 0.0) + amount);
                grandTotal += amount;
            }
        }

        System.out.println("\n===== Week of " + weekStart + " =====");
        if (totals.isEmpty()) {
            System.out.println("No expenses recorded in this week.");
            return;
        }

        for (String category : totals.keySet().stream().sorted().collect(Collectors.toList())) {
            double amount = totals.get(category);
            String line = String.format("%-12s", category);
            line += " ................ ";
            line += String.format("$%.2f", amount);
            System.out.println(line);
        }

        System.out.println("-------------------------------------");
        System.out.printf("Total: $%.2f%n", grandTotal);
    }

    //helper method to format search results.
    public void showSwearchResults(List<Expense> results){
        if(results.isEmpty()){
            System.out.println("No matching expenses found.");
            return;
        }

        System.out.println("\n===== Search Results =====");

        for (Expense e: results) {
            String name = e.getName();
            String category = e.getCategory();
            double amount = e.getAmount();
            LocalDate date = e.getDate();

            String line = String.format("%s  ",date);

            line += String.format("%-15s", name);
            line += " ............... ";
            line += String.format("%-12s", category);
            line += " ............... ";
            line += String.format("$%.2f", amount);

            System.out.println(line);

        }
    }
        //search by name function
        public List<Expense> searchByName(String keyword){
            List<Expense> results = new ArrayList<>();

            for (Expense e : expenses){
                if (e.getName().toLowerCase().contains(keyword.toLowerCase())){
                    results.add(e);
                }
            }
            return results;
        }

        //search by category function
        public List<Expense> searchByCategory(String category) {
            List<Expense> results = new ArrayList<>();
        
            for (Expense e : expenses) {
                if (e.getCategory().equalsIgnoreCase(category)) {
                    results.add(e);
                }
            }
            return results;
        }

        //seacrch by dateRange function
        public List<Expense> searchByDateRange(LocalDate start, LocalDate end) {
            List<Expense> results = new ArrayList<>();
            
            for (Expense e : expenses) {
                LocalDate date = e.getDate();
        
                boolean inRange =
                    (date.isAfter(start.minusDays(1)) && date.isBefore(end.plusDays(1)));
                if (inRange) {
                    results.add(e);
                }
            }
            return results;
        }
        
        







    }

    


    
    


    

