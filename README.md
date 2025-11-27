#Budget Breakdown CLI Tool (Java)
-Built by Jonathan Anderson
A Java command-line application that allows users to track expenses, view totals, catagorize spending  and manage budget data. This project demonstrates OOP, data structures, user input handling, and clean software architecture.

##Features 
✔ Add expenses (name, amount, category, date)  
✔ List all expenses  
✔ View total spending  
✔ View total spending by category  
✔ Delete expenses  
✔ Console-based user menu system  
✔ Uses ArrayList for storage  
✔ OOP structure with multiple classes  
✔ Clean output formatting with String.format
Upcoming features:
- Save and load expenses from CSV
- Percentage breakdown by category
- Monthly and weekly summaries
- Report generator

## How to Run

1. Clone the repository:
   git clone [https://github.com/<Jon504>/budget-breakdown-cli.git](https://github.com/Jon504/budget-breakdown-cli.git)

2. Navigate into the project folder:
   cd budget-breakdown-cli

3. Compile the project:
   javac src/main/java/com/jonathan/budget/*.java

4. Run the application:
   java com.jonathan.budget.Main

## Project Structure

src/
└── main/
    └── java/
        └── com/
            └── jonathan/
                └── budget/
                    ├── Main.java
                    ├── BudgetManager.java
                    └── Expense.java

## Technologies Used

- Java 17+  
- VS Code  
- Java Collections (ArrayList)  
- LocalDate for date handling  

## Future Improvements

- Implement a CSV FileManager for saving/loading expenses  
- Add a ReportGenerator for formatted summaries  
- Add percentage breakdowns for each category  
- Add sorting by date, amount, or category  
- Add search functionality  
