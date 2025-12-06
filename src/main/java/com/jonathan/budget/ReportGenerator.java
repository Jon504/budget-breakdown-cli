package com.jonathan.budget;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Month;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.File;

public class ReportGenerator {

    private BudgetManager manager;

    public ReportGenerator(BudgetManager manager) {
        this.manager = manager;
    }

    // to generate total report
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

        //To generate category reports
        public void generateCategoryReport() {
            Map<String, Double> totals = manager.getCategoryTotals();
            Map<String, Double> percents = manager.getCategoryPercentages();
            double grandTotal = manager.getTotalSpent();
        
            if (totals.isEmpty()) {
                System.out.println("No expenses to generate report.");
                return;
            }
        
            StringBuilder report = new StringBuilder();
            report.append("========== CATEGORY REPORT ==========\n\n");
        
            for (String category : totals.keySet()) {
                double amount = totals.get(category);
                double percent = percents.get(category);
        
                String line = String.format("%-12s", category);
                line += " ................ ";
                line += String.format("$%.2f (%.0f%%)", amount, percent);
        
                report.append(line).append("\n");
            }
        
            report.append("------------------------------------------\n");
            report.append(String.format("Total: $%.2f\n", grandTotal));
        
            java.io.File folder = new java.io.File("reports");
            if (!folder.exists()) folder.mkdir();
        
            writeToFile("reports/category_report.txt", report.toString());
        }

        //to generate monthly report
        public void generateMonthlyReport(int year, Month month) {
            // Build a map of totals for the given month
            Map<String, Double> totals = new HashMap<>();
            double grandTotal = 0.0;

            for (Expense e : manager.getExpenses()) {
                LocalDate date = e.getDate();
                if (date.getYear() == year && date.getMonth() == month) {
                    String category = e.getCategory();
                    double amount = e.getAmount();
                    totals.put(category, totals.getOrDefault(category, 0.0) + amount);
                    grandTotal += amount;
                }
            }

            StringBuilder report = new StringBuilder();
            report.append("========== ").append(month).append(" ").append(year).append(" SUMMARY ==========\n");

            if (totals.isEmpty()) {
                report.append("No expenses recorded in this month.\n");
            } else {
                for (String category : totals.keySet().stream().sorted().toList()) {
                    double amount = totals.get(category);

                    String line = String.format("%-12s", category);
                    line += " ................ ";
                    line += String.format("$%.2f", amount);

                    report.append(line).append("\n");
                }

                report.append("------------------------------------------\n");
                report.append(String.format("Total: $%.2f\n", grandTotal));
            }

            // ensure reports folder exists
            java.io.File folder = new java.io.File("reports");
            if (!folder.exists())
                folder.mkdir();

            String filename = "reports/monthly_" + year + "_" + month.getValue() + ".txt";
            writeToFile(filename, report.toString());
        }

        public void generateWeeklyReport(LocalDate weekStart) {
            LocalDate weekEnd = weekStart.plusDays(6);
        
            Map<String, Double> totals = new HashMap<>();
            double grandTotal = 0.0;
        
            for (Expense e : manager.getExpenses()) {
                LocalDate date = e.getDate();
        
                boolean inRange = 
                    (date.isAfter(weekStart.minusDays(1)) && date.isBefore(weekEnd.plusDays(1)));
        
                if (inRange) {
                    String category = e.getCategory();
                    double amount = e.getAmount();
        
                    totals.put(category, totals.getOrDefault(category, 0.0) + amount);
                    grandTotal += amount;
                }
            }
        
            StringBuilder report = new StringBuilder();
            report.append("========== WEEK OF ").append(weekStart).append(" ==========\n");
        
            if (totals.isEmpty()) {
                report.append("No expenses recorded this week.\n");
            } else {
                for (String category : totals.keySet().stream().sorted().toList()) {
                    double amount = totals.get(category);
        
                    String line = String.format("%-12s", category);
                    line += " ................ ";
                    line += String.format("$%.2f", amount);
        
                    report.append(line).append("\n");
                }
        
                report.append("------------------------------------------\n");
                report.append(String.format("Total: $%.2f\n", grandTotal));
            }
        
            java.io.File folder = new java.io.File("reports");
            if (!folder.exists()) folder.mkdir();
        
            String filename = "reports/weekly_" + weekStart + ".txt";
            writeToFile(filename, report.toString());
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