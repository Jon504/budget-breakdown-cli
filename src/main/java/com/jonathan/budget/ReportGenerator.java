package com.jonathan.budget;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Month;
import java.time.LocalDate;
import java.util.Map;
import java.util.List;
import java.io.File;

public class ReportGenerator {

    private BudgetManager manager;

    public ReportGenerator(BudgetManager manager) {
        this.manager = manager;
    }

    // total report
        public void generateTotalReport() {
            List<Expense> expenses = manager.getExpenses();
            if (expenses.isEmpty()) {
                System.out.println("No expenses to generate report.");
                return;
            }
        
            double totalSpent = manager.getTotalSpent();
            Map<String, Double> categoryTotals = manager.getCategoryTotals();
            Map<String, Double> categoryPercents = manager.getCategoryPercentages();
        
            // Find largest and smallest expenses
            Expense largest = expenses.get(0);
            Expense smallest = expenses.get(0);
        
            for (Expense e : expenses) {
                if (e.getAmount() > largest.getAmount()) largest = e;
                if (e.getAmount() < smallest.getAmount()) smallest = e;
            }
        
            // Most expensive category
            String maxCategory = null;
            double maxCategoryAmount = 0;
        
            for (String category : categoryTotals.keySet()) {
                if (categoryTotals.get(category) > maxCategoryAmount) {
                    maxCategoryAmount = categoryTotals.get(category);
                    maxCategory = category;
                }
            }
        
            StringBuilder report = new StringBuilder();
            report.append("========== TOTAL EXPENSE REPORT ==========\n");
            report.append("Total expenses recorded: ").append(expenses.size()).append("\n");
            report.append(String.format("Total spending: $%.2f\n\n", totalSpent));
        
            report.append("----- Category Breakdown -----\n");
        
            for (String category : categoryTotals.keySet()) {
                double amt = categoryTotals.get(category);
                double percent = categoryPercents.get(category);
        
                String line = String.format("%-12s", category);
                line += " ................ ";
                line += String.format("$%.2f (%.0f%%)", amt, percent);
                report.append(line).append("\n");
            }
        
            report.append("\nMost expensive category: ").append(maxCategory)
                  .append(" ($").append(String.format("%.2f", maxCategoryAmount)).append(")\n");
        
            report.append("Largest single expense: $").append(String.format("%.2f", largest.getAmount()))
                  .append(" (").append(largest.getDate()).append(", \"").append(largest.getName()).append("\")\n");
        
            report.append("Smallest single expense: $").append(String.format("%.2f", smallest.getAmount()))
                  .append(" (").append(smallest.getDate()).append(", \"").append(smallest.getName()).append("\")\n");
        
            report.append("------------------------------------------\n");
        
            // ensure reports folder exists
            java.io.File folder = new java.io.File("reports");
            if (!folder.exists()) folder.mkdir();
        
            writeToFile("reports/total_report.txt", report.toString());
        }
        
    

    // MONTHLY REPORT
    public void generateMonthlyReport(int year, Month month) {
        // TODO - implement
    }

    // WEEKLY REPORT
    public void generateWeeklyReport(LocalDate startDate) {
        // TODO - implement
    }

    // CATEGORY REPORT
    public void generateCategoryReport() {
        // TODO - implement
    }

    // Helper to write text file
    private void writeToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
            System.out.println("Report saved to: " + filename);
        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
    }
}