package com.jonathan.budget;
import java.util.List;

public class FileManager {

    //Save list of expenses to CSV file
    public void saveToCSV(List<Expense> expenses, String filename){

    }

    //Load expenses from CSV file
    public List<Expense> loadFromCSV(String filename){
        //ToDO implement loading logic
        return null;
    }

    // Convert one expense to CSV line
    private String formatExpense(Expense e) {
        // TODO: return a string like: date,name,category,amount
        return null;
    }

    // Parse CSV line into Expense object
    private Expense parseExpense(String line) {
        // TODO: parse CSV line into an Expense object
        return null;
    }
    
}
