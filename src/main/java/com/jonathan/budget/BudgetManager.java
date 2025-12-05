package com.jonathan.budget;
import com.jonathan.budget.Expense;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


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
    


    
}
