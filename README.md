# Budget Breakdown CLI (Java)

A Java-based command-line application for tracking, analyzing, and reporting personal expenses.
Designed with a clean multi-class architecture, persistent storage, and financial reporting features.

This project demonstrates object-oriented design, data processing with collections, file I/O, and real-world backend-style logic.

## Features 

-Add, list, and delete expenses (name, amount, category, date)
-View total spending and category-based totals
-View category percentage breakdowns
-Monthly and weekly spending summaries
-Search expenses by name, category, or date range
-Sort expenses by date, amount, name, or category
-Save and load expenses using CSV files
-Generate professional financial reports (TXT):
-Total expense report
-Category report
-Monthly report
-Weekly report
-Clean, formatted CLI output using String.format

## How to Run

1. Clone the repository:
   https://github.com/Jon504/budget-breakdown-cli.git

2. Navigate into the project folder:
   cd budget-breakdown-cli

3. Compile the project:
   javac src/main/java/com/jonathan/budget/*.java

4. Run the application:
   java com.jonathan.budget.Main

## Project Structure

src/main/java/com/jonathan/budget/
├── Main.java
├── Expense.java
├── BudgetManager.java
├── ReportGenerator.java

reports/
├── total_report.txt
├── category_report.txt
├── monthly_YYYY_MM.txt
├── weekly_YYYY_MM_DD.txt

## Technologies Used

-Java 17+
-Java Collections Framework
-File I/O (CSV and TXT export)
-LocalDate for date-based filtering
-Git & GitHub (feature branches, pull requests)

## Author 

Jonathan Anderson
GitHub: https://github.com/Jon504
LinkedIn: https://linkedin.com/in/jonathan26
