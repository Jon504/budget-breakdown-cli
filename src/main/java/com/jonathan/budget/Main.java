package com.jonathan.budget;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Month;

import com.jonathan.budget.BudgetManager;
import com.jonathan.budget.Expense;



public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        BudgetManager manager = new BudgetManager();
        boolean running = true;

        while (running) {

            System.out.println("\n=====Budget Breakdown Menu=====");
            System.out.println("1) Add Expense");
            System.out.println("2) List Expenses");
            System.out.println("3) View Total Spending");
            System.out.println("4) View Total by Category");
            System.out.println("5) Delete Expense");
            System.out.println("6) Save Expenses to CSV");
            System.out.println("7) Load Expenses from CSV");
            System.out.println("8) View Catagory Percentages");
            System.out.println("9) View Monthly Summary");
            System.out.println("10) View Weekly Summary");
            System.out.println("11) Search by Name");
            System.out.println("12) Search by Category");
            System.out.println("13) Search by Date Range");
            System.out.println("14) Sort by Date (Newest -> Oldest");
            System.out.println("15) Sort by Date (Oldest -> Newest)");
            System.out.println("16) Sort by Amount (High -> Low)");
            System.out.println("17) Sort by Amount (Low -> High)");
            System.out.println("18) Sort by Name (A -> Z)");
            System.out.println("19) Sort by Category (A -> Z)");
            System.out.println("20) Generate Total Expense Report (.txt)");
            System.out.println("21) Generate Category Report (.txt)");
            System.out.println("22) Generate Monthly Report (.txt)");
            System.out.println("23) Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                //adding an expense
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine().trim();
                    category = category.substring(0,1).toUpperCase() + category.substring(1).toLowerCase();


                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String dateInput = scanner.nextLine();
                    LocalDate date = LocalDate.parse(dateInput);

                    Expense e = new Expense(name, amount, category, date);
                    
                    //adding user's expense into the manager
                    manager.addExpense(e);
                    System.out.println("Expense added successfully!");
                    
                    break;

                //listing all expenses    
                case 2:
                    manager.listExpenses();
                    System.out.println();
                    break;
                //to view total spending
                case 3:
                    manager.showTotalSpent();
                    break;
                //to view total spending by category
                case 4:
                    System.out.print("enter a category: ");
                    String categoryInput = scanner.nextLine().trim();
                    category = categoryInput.substring(0,1).toUpperCase() + categoryInput.substring(1).toLowerCase();

                    manager.showTotalByCategory(category);
                    break;

                //to delete an expense using the user;s input
                case 5:
                    System.out.print("Enter the number of the expense to delete: ");
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine();

                    manager.deleteExpense(deleteIndex - 1);
                    break;

                case 6:
                     FileManager fm = new FileManager();
                    fm.saveToCSV(manager.getExpenses(), "expenses.csv");
                    break;

                case 7:
                    FileManager fileManager = new FileManager();
                    List<Expense> loaded = fileManager.loadFromCSV("expenses.csv");

                    // Clear current expenses and replace with loaded ones
                    manager.setExpenses(loaded);

                    System.out.println("Expenses loaded successfully!");
                    break;

                //view catagory percentages
                  case 8:
                    manager.showCategoryPercentages();
                    break;

                //view monthly summary
                case 9:
                    System.out.print("Enter year (e.g. 2025): ");
                    int year = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter month (1-12): ");
                    int monthNum = scanner.nextInt();
                    scanner.nextLine();

                    Month month = Month.of(monthNum);
                    manager.showMonthlySummary(year, month);
                    break;

                   case 10:
                    System.out.print("Enter week start date (YYYY-MM-DD): ");
                    String weekStr = scanner.nextLine();
                    LocalDate weekStart = LocalDate.parse(weekStr);
                    manager.showWeeklySummary(weekStart);
                    break;

                   //search by name
                   case 11:
                    System.out.print("Enter keyword to search by name: ");
                    String keyword = scanner.nextLine();
                    List<Expense> nameResults = manager.searchByName(keyword);
                    manager.showSearchResults(nameResults);
                    break;

                    //seach by category
                    case 12:
                    System.out.print("Enter category: ");
                    String cat = scanner.nextLine().trim();
                    List<Expense> catResults = manager.searchByCategory(cat);
                    manager.showSearchResults(catResults);
                    break;

                    //seach by date range
                    case 13:
                    System.out.print("Enter start date (YYYY-MM-DD): ");
                    LocalDate startDate = LocalDate.parse(scanner.nextLine());

                    System.out.print("Enter end date (YYYY-MM-DD): ");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine());

                    List<Expense> dateResults = manager.searchByDateRange(startDate, endDate);
                    manager.showSearchResults(dateResults);
                    break;

                    //Sort by Date newset to oldest
                    case 14:
                    manager.showSortedResults(manager.sortByDateNewest());
                    break;

                    //sort by date oldest to newest
                    case 15:
                    manager.showSortedResults(manager.sortByDateOldest());
                    break;

                    //sort by amount high to low
                   case 16:
                    manager.showSortedResults(manager.sortByAmountHigh());
                    break;

                    //sort by amount low to high
                  case 17:
                    manager.showSortedResults(manager.sortByAmountLow());
                    break;

                    //sort by name
                    case 18:
                    manager.showSortedResults(manager.sortByName());
                    break;

                    //sort by category
                  case 19:
                    manager.showSortedResults(manager.sortByCategory());
                    break;

                    case 20:
                    ReportGenerator rg = new ReportGenerator(manager);
                    rg.generateTotalReport();
                    break;

                   case 21:
                    ReportGenerator rgCat = new ReportGenerator(manager);
                    rgCat.generateCategoryReport();
                    break;

                    case 22:
                    System.out.print("Enter year (e.g., 2025): ");
                    int year1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter month (1-12): ");
                    int monthNum1 = scanner.nextInt();
                    scanner.nextLine();
                    Month m = Month.of(monthNum1);
                    ReportGenerator rgMonth = new ReportGenerator(manager);
                    rgMonth.generateMonthlyReport(year1, m);
                    break;



                //exit the program
                case 23:
                    System.out.println("Exiting program...");
                    running = false;
                    break;

                    
                // for invalid input    
                default:
                    System.out.println("Invalid option. Try again.");
                    break;


            }

        }

    }
}
