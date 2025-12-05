package com.jonathan.budget;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
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
            System.out.println("8) Exit");
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

                
                //exit the program
                case 8:
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
